package ru.makarovda.weatherappl.data.network;

import com.google.gson.annotations.SerializedName;

import ru.makarovda.weatherappl.domain.WeatherData;

public class WeatherResponse {

    public class Location
    {

        @SerializedName("name")
        private String name_;

        public Location(String name)
        {
            name_ = name;
        }
    }

    public class Current
    {
        private class Condition
        {
            @SerializedName("text")
            public String text_;

            Condition(String text) {
                text_ = text;
            }

            String getText()
            {
                return text_;
            }
        }

        // Температура в градусах Цельсия
        @SerializedName("temp_c")
        private float temperature_;

        // Ощущаемая температура в градусах Цельсия
        @SerializedName("feelslike_c")
        private float feelsLikeTemperature_;

        // Скорость ветра в км/ч
        @SerializedName("wind_kph")
        private float windSpeed_;

        // Состояние (ясно, дождь и т.д.)
        @SerializedName("condition")
        private Condition condition_;

        Current(float temperature, float feelsLikeTemperature, float windSpeed, Condition condition)
        {
            temperature_ = temperature;
            feelsLikeTemperature_ = feelsLikeTemperature;
            windSpeed_ = windSpeed;
            condition_= condition;
        }

    }

    @SerializedName("current")
    private Current current_;

    @SerializedName("location")
    private Location location_;

    public WeatherResponse(Current current)
    {
        current_ = current;
    }

    public WeatherData toWeatherDomain()
    {
        return new WeatherData(
                current_.temperature_,
                current_.feelsLikeTemperature_,
                current_.windSpeed_,
                current_.condition_.text_,
                location_.name_
        );
    }

}
