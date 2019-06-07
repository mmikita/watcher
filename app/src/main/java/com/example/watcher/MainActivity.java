package com.example.watcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.watcher.weatherCall.MyRequestQueue;
import com.example.watcher.weatherCall.WeatherRequest;

public class MainActivity extends AppCompatActivity {
    Spinner allCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allCities = findViewById(R.id.allCities);
        allCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                chooseCity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }

    public void chooseCity() {
        String selectedCity = allCities.getSelectedItem().toString();
        changeCityText(selectedCity);
        changeObservedWeather(selectedCity);

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

    private void changeChat() {

    }


}
