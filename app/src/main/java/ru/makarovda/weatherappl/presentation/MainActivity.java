package ru.makarovda.weatherappl.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.makarovda.weatherappl.R;
import ru.makarovda.weatherappl.data.network.IWeatherService;
import ru.makarovda.weatherappl.data.network.WeatherResponse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        IWeatherService weather = retrofit.create(IWeatherService.class);

        weather.getCurrentWeather("London").subscribeOn(Schedulers.io()).subscribe(
                new SingleObserver<WeatherResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(WeatherResponse value) {
                        Log.d("WethRes", value.toWeatherDomain().getCondition());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("WethRes", e.toString());
                    }
                }
        );


    }
}