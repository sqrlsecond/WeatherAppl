package ru.makarovda.weatherappl.data;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.ReplaySubject;
import ru.makarovda.weatherappl.data.network.IWeatherService;
import ru.makarovda.weatherappl.data.network.WeatherResponse;
import ru.makarovda.weatherappl.data.storage.WeatherDao;
import ru.makarovda.weatherappl.data.storage.WeatherStorage;
import ru.makarovda.weatherappl.domain.IRepository;
import ru.makarovda.weatherappl.domain.WeatherData;

public class Repository implements IRepository {

    private IWeatherService weatherService_;
    private WeatherDao weatherDao_;

    private BehaviorSubject<WeatherData> weatherDataFlow_;

    @Inject
    public Repository(IWeatherService weatherService, WeatherDao weatherDao)
    {
        weatherService_ = weatherService;
        weatherDao_  = weatherDao;
        weatherDataFlow_ = BehaviorSubject.create();
    }

    @Override
    public Observable<WeatherData> getWeatherFlowable() {
        return weatherDataFlow_;
    }

    /**
     * Запрос в сеть
     * @param cityName - название города
     */
    @Override
    public void requestWeather(String cityName) {

        //Запрос в базу данных
        weatherDao_.getWeather(cityName).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<WeatherStorage>() {
            @Override
            public void onSuccess(WeatherStorage value) {
                weatherDataFlow_.onNext(value.toWeatherDomain());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("WeatResp", e.toString());
            }
        });

        //Запрос в сеть
        weatherService_.getCurrentWeather(cityName).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<WeatherResponse>() {
            @Override
            public void onSuccess(WeatherResponse value) {
                //Сохраняется только результат последнего запроса
                weatherDao_.clearTable();
                weatherDao_.insert(new WeatherStorage(
                    value.getCity(),
                    value.getTemperature(),
                    value.getFeelsLikeTemperature(),
                    value.getWindSpeed(),
                    value.getCondition()
                ));
                weatherDataFlow_.onNext(value.toWeatherDomain());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("WeatResp", e.toString());
            }
        });

    }

    /**
     * Прочитать данные из локальной БД
     */
    @Override
    public void readWeather() {
        weatherDao_.getWeather().subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<WeatherStorage>() {
            @Override
            public void onSuccess(WeatherStorage value) {
                weatherDataFlow_.onNext(value.toWeatherDomain());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("WeatResp", e.toString());
            }
        });
    }
}
