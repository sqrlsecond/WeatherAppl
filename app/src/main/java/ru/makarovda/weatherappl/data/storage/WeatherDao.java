package ru.makarovda.weatherappl.data.storage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import io.reactivex.Single;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weather WHERE city = :city")
    Single<WeatherStorage> getWeather(String city);

    @Update
    void update(WeatherStorage weatherStorage);

    @Delete
    void delete(WeatherStorage weatherStorage);

    @Insert
    void insert(WeatherStorage weatherStorage);

    @Query("DELETE FROM weather")
    void clearTable();
}
