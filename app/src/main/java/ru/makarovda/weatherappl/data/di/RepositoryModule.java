package ru.makarovda.weatherappl.data.di;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import ru.makarovda.weatherappl.data.Repository;
import ru.makarovda.weatherappl.domain.IRepository;


@Module
public abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract IRepository getRepository(Repository repository);

}
