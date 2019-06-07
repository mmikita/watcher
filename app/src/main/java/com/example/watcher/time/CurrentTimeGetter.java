package com.example.watcher.time;

import android.os.Handler;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CurrentTimeGetter {

    private static CurrentTimeGetter INSTANCE = null;
    private String currentTime;


    private CurrentTimeGetter() {
    }

    public static CurrentTimeGetter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CurrentTimeGetter();
        }
        return (INSTANCE);
    }

    public void getTime(String City, final TextView selectedCity) {
        final Handler handler = new Handler();


        handler.post(new Runnable() {

            @Override
            public void run() {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                Date date = new Date();
                handler.postDelayed(this, 1000);
                selectedCity.setText(formatter.format(date).toString());
            }
        });


    }


}
