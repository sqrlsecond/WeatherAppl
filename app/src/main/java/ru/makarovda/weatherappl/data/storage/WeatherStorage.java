package ru.makarovda.weatherappl.data.storage;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import ru.makarovda.weatherappl.domain.WeatherData;

@Entity(tableName = "weather")
public class WeatherStorage {

    // Название города
    @PrimaryKey
    @NonNull
    public String city;

    // Температура в градусах Цельсия
    @SerializedName("temperature")
    public float temperature;

    // Ощущаемая температура в градусах Цельсия
    @SerializedName("feelsLikeTemperature")
    public float feelsLikeTemperature;

    // Скорость ветра в км/ч
    @SerializedName("windSpeed")
    public float windSpeed;

    // Состояние (ясно, дождь и т.д.)
    @SerializedName("condition")
    public String condition;

    public WeatherStorage() {
        city = "";
        temperature = 0;
        feelsLikeTemperature = 0;
        windSpeed = 0;
        condition = "";
    }

    public WeatherStorage(@NonNull String city, float temperature, float feelsLikeTemperature, float windSpeed, String condition) {
        this.city = city;
        this.temperature = temperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.windSpeed = windSpeed;
        this.condition = condition;
    }

    public WeatherData toWeatherDomain()
    {
        return new WeatherData(
                temperature,
                feelsLikeTemperature,
                windSpeed,
                condition,
                city
        );
    }


}
