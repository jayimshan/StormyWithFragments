package com.affogatostudios.stormywithfragments.model;

import com.affogatostudios.stormywithfragments.R;

public class Forecast {
    public static final String TAG = Forecast.class.getSimpleName();

    private Currently currently;
    private Hour[] hourlyForecast;
    private Day[] dailyForecast;

    public Forecast() {
    }

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    public Hour[] getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(Hour[] hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

    public Day[] getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(Day[] dailyForecast) {
        this.dailyForecast = dailyForecast;
    }

    public static int getIconId(String iconString) {
        int iconId = R.drawable.clear_day;

        switch (iconString) {
            case "clear-day":
                iconId = R.drawable.clear_day;
                break;
            case "clear-night":
                iconId = R.drawable.clear_night;
                break;
            case "rain":
                iconId = R.drawable.rain;
                break;
            case "snow":
                iconId = R.drawable.snow;
                break;
            case "sleet":
                iconId = R.drawable.sleet;
                break;
            case "wind":
                iconId = R.drawable.wind;
                break;
            case "fog":
                iconId = R.drawable.fog;
                break;
            case "cloudy":
                iconId = R.drawable.cloudy;
                break;
            case "partly-cloudy-day":
                iconId = R.drawable.partly_cloudy;
                break;
            case "partly-cloudy-night":
                iconId = R.drawable.cloudy_night;
                break;
        }
        return iconId;
    }
}
