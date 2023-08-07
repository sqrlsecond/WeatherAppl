package ru.makarovda.weatherappl;

import android.app.Application;

public class WeatherApplication extends Application {

    private AppComponent appComponent_;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent_ = DaggerAppComponent.builder()
                .context(this)
                .build();
    }

    public AppComponent getAppComponent()
    {
        return appComponent_;
    }

}
