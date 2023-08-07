package ru.makarovda.weatherappl.data.storage;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WeatherStorage.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();

}
