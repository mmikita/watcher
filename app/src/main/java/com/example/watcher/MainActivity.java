package com.example.watcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.watcher.time.CurrentTimeGetter;
import com.example.watcher.weatherCall.CitiesCodes;
import com.example.watcher.weatherCall.MyRequestQueue;
import com.example.watcher.weatherCall.WeatherRequest;

public class MainActivity extends AppCompatActivity {
    private Spinner allCities;
    private String currentCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allCities = findViewById(R.id.allCities);
        allCities.setAdapter(new ArrayAdapter<CitiesCodes>(this, android.R.layout.simple_spinner_item, CitiesCodes.values()));
        allCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                currentCity = allCities.getSelectedItem().toString();
                chooseCity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }

    public void chooseCity() {
        String selectedCity = currentCity;
        changeCityText(selectedCity);
        changeObservedWeather(selectedCity);
        changeTime();

    }

    private void changeCityText(String city) {
        TextView selectedCity = findViewById(R.id.selectedCity);
        selectedCity.setText(city);

    }

    private void changeObservedWeather(String city) {
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.getWeather(city, this);

    }

    private void changeVideo() {

    }

    private void changeTime() {
        TextView selectedCity = findViewById(R.id.time);
        CurrentTimeGetter.getInstance().getTime(currentCity, selectedCity);
    }


}
