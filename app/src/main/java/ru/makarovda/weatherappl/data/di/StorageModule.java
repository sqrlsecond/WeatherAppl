package ru.makarovda.weatherappl.data.di;

import android.content.Context;

import androidx.room.Room;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import ru.makarovda.weatherappl.data.storage.WeatherDao;
import ru.makarovda.weatherappl.data.storage.WeatherDatabase;

@Module
public class StorageModule
{
    @Provides
    @Singleton
    public WeatherDatabase getDatabase(Context context)
    {
        return Room.databaseBuilder(
                context,
                WeatherDatabase.class, "weatherDatabase"
        ).build();
    }

    @Provides
    @Singleton
    public WeatherDao getWeatherDao(Context context)
    {
        return getDatabase(context).weatherDao();
    }

}
