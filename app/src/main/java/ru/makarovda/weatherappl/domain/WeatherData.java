package ru.makarovda.weatherappl.domain;

public class WeatherData {

    // Температура в градусах Цельсия
    private float temperature_;

    // Ощущаемая температура в градусах Цельсия
    private float feelsLikeTemperature_;

    // Скорость ветра в км/ч
    private float windSpeed_;

    // Состояние (ясно, дождь и т.д.)
    private String condition_;

    public WeatherData(float temperature, float feelsLikeTemperature, float windSpeed, String condition)
    {
        temperature_ = temperature;
        feelsLikeTemperature_ = feelsLikeTemperature;
        windSpeed_ = windSpeed;
        condition_ = condition;
    }

    public float getTemperature()
    {
        return temperature_;
    }

    public float getFeelsLikeTemperature()
    {
        return feelsLikeTemperature_;
    }

    public float getWindSpeed()
    {
        return windSpeed_;
    }

    public String getCondition()
    {
        return condition_;
    }

}
