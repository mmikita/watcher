package com.example.watcher.weatherCall;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.watcher.CitiesCodes;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherRequest {

    private static String REQUEST_LINK = "http://dataservice.accuweather.com/currentconditions/v1/[cityCode]?details=true&apikey=FOkxhU3RkooJPFPAefMCDKSv6f30d2mq";
    private JSONObject response;


    public void getWeather(String city, Context context) {
        CitiesCodes citiesCodes = CitiesCodes.valueOf(city);

        String linkToRequest = REQUEST_LINK.replace("[cityCode]", citiesCodes.getCityCode());


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, linkToRequest, (String) null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("OnResponse: ", response.toString());

                        getResponse(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response error: ", error.toString());

                    }
                });

        MyRequestQueue.getInstance(context).addToRequestQueue(jsonArrayRequest);

    }

    private void getResponse(JSONArray response) {

        Log.i("Odpowiedz: ", response.toString());
    }

    private String getTemperature() {

        return null;
    }

    private String getHumidity() {
        return null;
    }
}
