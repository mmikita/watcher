package com.example.watcher.weatherCall;

public enum CitiesCodes {
    Białystok("2-275110_1_AL"),
    Tokio("226396");
    private String cityCode;

    CitiesCodes(String s) {
        this.cityCode = s;
    }

    public String getCityCode() {
        return cityCode;
    }
}
