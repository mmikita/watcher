package com.example.watcher;

public enum CitiesCodes {
    Białystok("2-275110_1_AL", "Poland"),
    Tokio("226396", "Japan");
    private String cityCode;
    private String country;

    CitiesCodes(String code, String country) {
        this.cityCode = code;
        this.country = country;
    }

    public String getCityCode() {
        return cityCode;
    }
    public String getCountry() {
        return country;
    }

}
