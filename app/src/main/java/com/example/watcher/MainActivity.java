package com.example.watcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.watcher.time.CurrentTimeGetter;
import com.example.watcher.weatherCall.WeatherRequest;

public class MainActivity extends AppCompatActivity {
    private Spinner allCities;
    private String currentCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allCities = findViewById(R.id.allCities);
        allCities.setAdapter(new ArrayAdapter<CitiesCodes>(this, android.R.layout.simple_spinner_dropdown_item, CitiesCodes.values()));
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
        changeVideo();

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
        WebView video = findViewById(R.id.video);
        String iframe = "<p align=\"center\"><iframe width=\"90%\" height=\"90%\" src=\"https://www.youtube.com/embed/5K8T3_7Fb9s\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></p>";
        video.getSettings().setJavaScriptEnabled(true);
        video.loadData(iframe, "text/html", null);
        video.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        video.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);


    }

    private void changeTime() {
        TextView selectedCity = findViewById(R.id.time);
        CurrentTimeGetter.getInstance().getTime(currentCity, selectedCity);
    }


}
