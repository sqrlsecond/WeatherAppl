package ru.makarovda.weatherappl.domain;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface IRepository {

    Observable<WeatherData> getWeatherFlowable();

    /**
     * Запрос в сеть
     * @param cityName - название города
     */
    void requestWeather(String cityName);

    /**
     * Прочитать данные из локальной БД
     */
    void readWeather();

}
