package com.affogatostudios.stormywithfragments.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Currently {
    private String locationLabel;
    private String icon;
    private long time;
    private double temperature;
    private double humidty;
    private double precipChance;
    private String summary;
    private String timeZone;
    private boolean isHot;

    public Currently() {
    }

    public Currently(String locationLabel, String icon, long time, double temperature, double humidty, double precipChance, String summary, String timeZone) {
        this.locationLabel = locationLabel;
        this.icon = icon;
        this.time = time;
        this.temperature = temperature;
        this.humidty = humidty;
        this.precipChance = precipChance;
        this.summary = summary;
        this.timeZone = timeZone;
    }

    public String getLocationLabel() {
        return locationLabel;
    }

    public void setLocationLabel(String locationLabel) {
        this.locationLabel = locationLabel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIconId() {
        return Forecast.getIconId(icon);
    }

    public long getTime() {
        return time;
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date dateTime = new Date(time * 1000);
        return formatter.format(dateTime);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTemperature() {
        int temp = (int) Math.round(this.temperature);
        if (temp > 60) {
            isHot = true;
        } else {
            isHot = false;
        }
        return temp;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidty() {
        return humidty;
    }

    public void setHumidty(double humidty) {
        this.humidty = humidty;
    }

    public double getPrecipChance() {
        return Math.round(precipChance);
    }

    public void setPrecipChance(double precipChance) {
        this.precipChance = precipChance;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public boolean isHot() {
        return isHot;
    }
}
