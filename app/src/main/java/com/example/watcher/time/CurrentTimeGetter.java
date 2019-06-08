package com.example.watcher.time;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.watcher.weatherCall.CitiesCodes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CurrentTimeGetter {

    private static CurrentTimeGetter INSTANCE = null;
    private String currentTime;
    private String curentCountry;

    private CurrentTimeGetter() {
    }
    public static CurrentTimeGetter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CurrentTimeGetter();
        }
        return (INSTANCE);
    }

    public void getTime(String city, final TextView selectedCity) {
        final Handler handler = new Handler();
        curentCountry = CitiesCodes.valueOf(city).getCountry();
        Log.i("dupa_kacza", curentCountry+" "+CitiesCodes.valueOf(city).getCountry()+" "+city);
        handler.post(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                Date date = new Date();
                TimeZone timeZone = TimeZone.getTimeZone(curentCountry);
                formatter.setTimeZone(timeZone);
                String dateInString = formatter.format(date);
                Date timeZoneTime = null;
                try {
                    timeZoneTime = formatter.parse(dateInString);
                } catch (ParseException e) {
                    Log.e("Parse Error", "Error while parsing date format");
                }
                selectedCity.setText(formatter.format(timeZoneTime));
                handler.postDelayed(this, 1000);
            }
        });


    }


}
