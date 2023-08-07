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

    // Название города
    private String city_;

    public WeatherData( float temperature,
                        float feelsLikeTemperature,
                        float windSpeed,
                        String condition,
                        String cityName)
    {
        temperature_ = temperature;
        feelsLikeTemperature_ = feelsLikeTemperature;
        windSpeed_ = windSpeed;
        condition_ = condition;
        city_ = cityName;
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

    public String getCity()
    {
        return city_;
    }

}
