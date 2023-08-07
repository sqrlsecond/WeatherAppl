package ru.makarovda.weatherappl;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import ru.makarovda.weatherappl.data.Repository;
import ru.makarovda.weatherappl.data.di.NetworkModule;
import ru.makarovda.weatherappl.data.di.RepositoryModule;
import ru.makarovda.weatherappl.data.di.StorageModule;
import ru.makarovda.weatherappl.domain.IRepository;

@Singleton
@Component(modules = {NetworkModule.class, StorageModule.class, RepositoryModule.class})
public interface AppComponent
{

    IRepository getRepository();

    @Component.Builder
    interface Builder
    {
        @BindsInstance
        Builder context(Context context);

        AppComponent build();

    }

}
