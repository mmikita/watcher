package com.example.watcher;

import android.os.Bundle;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.watcher.time.CurrentTimeGetter;
import com.example.watcher.weatherCall.WeatherRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        changeVideo(selectedCity);

    }

    private void changeCityText(String city) {
        TextView selectedCity = findViewById(R.id.selectedCity);
        selectedCity.setText(city);

    }

    private void changeObservedWeather(String city) {
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.getWeather(city, this);
    }

    private void changeVideo(String selectedCity) {
        WebView video = findViewById(R.id.video);
        video.setWebChromeClient(new WebChromeClient());
        video.setWebViewClient(new WebViewClient());
        video.getSettings().setJavaScriptEnabled(true);
        video.getSettings().setLoadWithOverviewMode(true);
        video.getSettings().setUseWideViewPort(true);
        String iframe = CitiesCodes.valueOf(selectedCity).getVideo();
        video.loadData(iframe, "text/html", null);


    }

    private void changeTime() {
        TextView selectedCity = findViewById(R.id.time);
        CurrentTimeGetter.getInstance().getTime(currentCity, selectedCity);
    }



}
