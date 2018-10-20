package com.affogatostudios.stormywithfragments.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Hour implements Serializable {
    private long time;
    private String summary;
    private double temperature;
    private String icon;
    private String timeZone;

    public Hour() {
    }

    public Hour(long time, String summary, double temperature, String icon, String timeZone) {
        this.time = time;
        this.summary = summary;
        this.temperature = temperature;
        this.icon = icon;
        this.timeZone = timeZone;
    }

    public long getTime() {
        return time;
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("h a");
        formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date dateTime = new Date(time * 1000);
        return formatter.format(dateTime);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getFormattedTemperature() {
        return (int) Math.round(temperature);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getIcon() {
        return Forecast.getIconId(icon);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}