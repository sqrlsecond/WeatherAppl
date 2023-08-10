package ru.makarovda.weatherappl.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.subjects.ReplaySubject;
import ru.makarovda.weatherappl.WeatherApplication;
import ru.makarovda.weatherappl.domain.IRepository;
import ru.makarovda.weatherappl.domain.WeatherData;

public class MainViewModel extends AndroidViewModel {

    private final ReplaySubject<WeatherData> replaySubject;

    private final IRepository repository;

    private Disposable disposable;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = ((WeatherApplication) application).getAppComponent().getRepository();
        replaySubject = ReplaySubject.create();
        repository.getWeatherFlowable().subscribe(new Observer<WeatherData>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(WeatherData weatherData) {
                replaySubject.onNext(weatherData);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        repository.readWeather();
    }

    public Observable<WeatherData> getWeatherFlowable()
    {
        return replaySubject;
    }

    @Override
    protected void onCleared()
    {
        disposable.dispose();
        super.onCleared();
    }

    public void requestWeather(String cityName)
    {
        repository.requestWeather(cityName);
    }

}
