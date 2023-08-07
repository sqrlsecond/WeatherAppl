package ru.makarovda.weatherappl.domain;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface IRepository {

    Observable<WeatherData> getWeatherFlowable();

    void requestWeather(String cityName);

}
