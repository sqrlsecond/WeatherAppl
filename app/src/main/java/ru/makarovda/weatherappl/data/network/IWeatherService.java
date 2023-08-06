package ru.makarovda.weatherappl.data.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWeatherService {

    @GET("./current.json?key=47110b068bdf4751a20153753230206")
    Single<WeatherResponse> getCurrentWeather(@Query(value = "q", encoded = true) String location);

}
