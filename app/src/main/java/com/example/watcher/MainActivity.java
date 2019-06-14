package com.example.watcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.watcher.time.CurrentTimeGetter;
import com.example.watcher.weatherCall.WeatherRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    private Spinner allCities;
    private String currentCity;
    private boolean reformatted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reformatted=false;
        allCities = findViewById(R.id.allCities);
        allCities.setAdapter(new ArrayAdapter<CitiesCodes>(this, R.layout.spinner_item, CitiesCodes.values()));
        allCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                currentCity = allCities.getSelectedItem().toString();
                chooseCity();
                if(!reformatted) {
                    reformatEntries();
                    reformatted=true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }

    public void reformatEntries() {
        List<CitiesCodes> enumValues = Arrays.asList(CitiesCodes.values());
        List<String> citiesNames = new ArrayList<String>();
        for (CitiesCodes city : enumValues) {
            String cityName = city.name().replace("_", " ");
            citiesNames.add(cityName);
        }
        allCities.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, citiesNames));
    }

    public void chooseCity() {
        String city = currentCity.replace(" ", "_");
        changeObservedWeather(city);
        changeTime(city);
        changeVideo(city);
    }



    private void changeObservedWeather(String city) {
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.getWeather(city, this);
    }

    private void changeVideo(String city) {
        WebView video = findViewById(R.id.video);
        video.setWebChromeClient(new WebChromeClient());
        video.setWebViewClient(new WebViewClient());
        video.getSettings().setJavaScriptEnabled(true);
        video.getSettings().setLoadWithOverviewMode(true);
        video.getSettings().setUseWideViewPort(true);
        String iframe = CitiesCodes.valueOf(city).getVideo();
        video.loadData(iframe, "text/html", null);


    }

    private void changeTime(String city) {
        TextView selectedCity = findViewById(R.id.time);
        CurrentTimeGetter.getInstance().getTime(city, selectedCity);
    }


}
