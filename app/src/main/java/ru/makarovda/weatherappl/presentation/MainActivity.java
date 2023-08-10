package ru.makarovda.weatherappl.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.security.Key;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.makarovda.weatherappl.R;
import ru.makarovda.weatherappl.WeatherApplication;
import ru.makarovda.weatherappl.domain.IRepository;
import ru.makarovda.weatherappl.domain.WeatherData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherApplication app = (WeatherApplication)getApplication();
        IRepository repository = app.getAppComponent().getRepository();
        TextView temperatureTV = findViewById(R.id.temperature_textView);
        TextView feelsLikeTempTV = findViewById(R.id.feelsLike_textView);
        TextView conditionTV = findViewById(R.id.condition_textView);
        TextView windSpeedTV = findViewById(R.id.windSpeed_textView);
        EditText cityNameET = findViewById(R.id.city_name_editText);
        cityNameET.setOnKeyListener(
                new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if(keyCode == KeyEvent.KEYCODE_ENTER) {
                            repository.requestWeather(((EditText)v).getText().toString());
                            return true;
                        }
                        return false;
                    }
                }
        );
        Resources res = getResources();

        repository.getWeatherFlowable().subscribeOn(Schedulers.io()).subscribe(
                new Observer<WeatherData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeatherData value) {
                        Log.d("WeatResp", value.getCondition());
                        temperatureTV.setText(res.getString(R.string.temperature_text, value.getTemperature()));
                        feelsLikeTempTV.setText(res.getString(R.string.feels_temperature_text, value.getFeelsLikeTemperature()));
                        conditionTV.setText(res.getString(R.string.condition_text, value.getCondition()));
                        windSpeedTV.setText(res.getString(R.string.wind_speed_text, value.getWindSpeed()));
                        cityNameET.setText(value.getCity());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );

        repository.readWeather();


    }
}