package ru.makarovda.weatherappl.data.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.makarovda.weatherappl.data.network.IWeatherService;

@Module
public class NetworkModule {

    final String baseUrl = "https://api.weatherapi.com/v1/";

    @Provides
    @Singleton
    public IWeatherService getIWeatherService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(IWeatherService.class);
    }
}
