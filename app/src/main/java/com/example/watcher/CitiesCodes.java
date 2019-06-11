package com.example.watcher;

public enum CitiesCodes {
    Białystok("2-275110_1_AL", "Poland", "<p align=\"center\"><iframe style=\"margin-left: 150px; margin-top: 75px;\" align=\"center\"  width=\"90%\" height=\"90%\" src=\"http://82.139.167.140:3131/mjpg/video.mjpg\" frameborder=\"0\" allow=\"accelerometer; scrolling=\"no\" autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></p>"),
    Tokio("226396", "Japan", "<p align=\"center\"><iframe width=\"90%\" height=\"90%\" src=\"https://www.youtube.com/embed/pm-R3dvrUZg\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></p>"),
    Nowy_Jork("349727", "America/New_York", "<p align=\"center\"><iframe width=\"90%\" height=\"90%\" src=\"https://www.youtube.com/embed/3Wartp7Ggck\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></p>");
    private String cityCode;
    private String country;
    private String video;

    CitiesCodes(String code, String country, String video) {
        this.cityCode = code;
        this.country = country;
        this.video = video;
    }

    public String getCityCode() {
        return cityCode;
    }
    public String getCountry() {
        return country;
    }
    public String getVideo() {
        return video;
    }

}
