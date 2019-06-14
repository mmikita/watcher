package com.example.watcher.weatherCall;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.watcher.CitiesCodes;
import com.example.watcher.R;

import org.json.JSONArray;
import org.json.JSONException;

public class WeatherRequest {

    private static String REQUEST_LINK = "http://dataservice.accuweather.com/currentconditions/v1/[cityCode]?details=true&apikey=qptNqPrrSPj01KdIKeQsQA9BWCSOKLNW";
    private Context context;

    public void getWeather(String city, Context context) {
        this.context = context;
        CitiesCodes citiesCodes = CitiesCodes.valueOf(city);
        String linkToRequest = REQUEST_LINK.replace("[cityCode]", citiesCodes.getCityCode());


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, linkToRequest, (String) null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("OnResponse: ", response.toString());
                        setResponsetoView(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response error: ", error.toString());

                    }
                });

        MyRequestQueue.getInstance(context).addToRequestQueue(jsonArrayRequest);

    }


    public void setResponsetoView(JSONArray response) {
        TextView tempText = ((Activity) context).findViewById(R.id.temperature);
        TextView humText = ((Activity) context).findViewById(R.id.humidity);
        ImageView weatherImage = ((Activity) context).findViewById(R.id.imageView1);
        //set temperature
        try {
            String tempInC = response.getJSONObject(0).getJSONObject("Temperature").getJSONObject("Metric").getString("Value");
            tempText.setText(tempInC + " °C");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSON exception", e.getMessage());
        }

        //set humidity
        try {
            String hum = response.getJSONObject(0).getString("RelativeHumidity");
            humText.setText(hum + " %");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSON exception", e.getMessage());
        }


        //set image
        try {
            String icon = response.getJSONObject(0).getString("WeatherIcon");
            int id = context.getResources().getIdentifier("w" + icon+"s" , "drawable", context.getPackageName());
            weatherImage.setImageResource(id);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSON exception", e.getMessage());
        }


    }

}
