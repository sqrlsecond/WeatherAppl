package ru.makarovda.weatherappl.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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

    private TextView temperatureTV;
    private TextView feelsLikeTempTV;
    private TextView conditionTV;
    private TextView windSpeedTV;
    private EditText cityNameET;
    private Disposable disposable_;

    private MainViewModel mainVM_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainVM_ = new ViewModelProvider(this).get(MainViewModel.class);

        temperatureTV = findViewById(R.id.temperature_textView);
        feelsLikeTempTV = findViewById(R.id.feelsLike_textView);
        conditionTV = findViewById(R.id.condition_textView);
        windSpeedTV = findViewById(R.id.windSpeed_textView);
        cityNameET = findViewById(R.id.city_name_editText);
        cityNameET.setOnKeyListener(
                (v, keyCode, event) -> {
                    if((keyCode == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_UP)) {
                        mainVM_.requestWeather(((EditText)v).getText().toString());
                        return true;
                    }
                    return false;
                }
        );
    }

    @Override
    protected void onStart()
    {

        Resources res = getResources();

        mainVM_.getWeatherFlowable().subscribe(
                new Observer<WeatherData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable_ = d;
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
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable_.dispose();
    }


}