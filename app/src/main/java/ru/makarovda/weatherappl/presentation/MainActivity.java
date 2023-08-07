package ru.makarovda.weatherappl.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.makarovda.weatherappl.R;
import ru.makarovda.weatherappl.WeatherApplication;
import ru.makarovda.weatherappl.data.network.IWeatherService;
import ru.makarovda.weatherappl.data.network.WeatherResponse;
import ru.makarovda.weatherappl.domain.IRepository;
import ru.makarovda.weatherappl.domain.WeatherData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherApplication app = (WeatherApplication)getApplication();
        IRepository repository = app.getAppComponent().getRepository();

        repository.getWeatherFlowable().subscribeOn(Schedulers.io()).subscribe(
                new Observer<WeatherData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeatherData value) {
                        Log.d("WeatResp", value.getCondition().toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );

        repository.requestWeather("London");


    }
}