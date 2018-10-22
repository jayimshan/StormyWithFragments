package com.affogatostudios.stormywithfragments.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.affogatostudios.stormywithfragments.R;
import com.affogatostudios.stormywithfragments.model.Currently;
import com.affogatostudios.stormywithfragments.model.Day;
import com.affogatostudios.stormywithfragments.model.Forecast;
import com.affogatostudios.stormywithfragments.model.Hour;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements CurrentlyFragment.OnRefreshForecastButtonSelected,
        CurrentlyFragment.OnDetailsButtonSelected {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String KEY_CURRENTLY_BUNDLE = "key_currently_bundle";
    public static final String KEY_HOURLY_FORECAST = "key_hourly_forecast";
    public static final String KEY_DAILY_FORECAST = "key_daily_forecast";
    public static final String KEY_HOURLY_BUNDLE = "key_hourly_bundle";
    public static final String KEY_DAILY_BUNDLE = "key_daily_bundle";
    public static final String KEY_BUNDLE = "key_dualpane_bundle";
    public static final int KEY_FRAGMENT_CURRENT = 0;
    public static final int KEY_FRAGMENT_VIEWPAGER = 1;
    public static final int KEY_FRAGMENT_DUALPANE = 2;

    private Forecast forecast;
    private Currently displayWeather;

    final double latitude = 37.8267;
    final double longitude = -122.4233;
    private boolean isTablet;

    private List<Hour> hours;
    private List<Day> days;

    CurrentlyFragment currentlyFragment;
    ViewPagerFragment viewPagerFragment;

    private int fragment = KEY_FRAGMENT_CURRENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forecast = new Forecast();

        isTablet = getResources().getBoolean(R.bool.is_tablet);

        currentlyFragment = (CurrentlyFragment) getSupportFragmentManager().findFragmentByTag(CurrentlyFragment.KEY_CURRENTLY_FRAGMENT);
        
        getForecast(latitude, longitude);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putSerializable(KEY_DAILY_FORECAST, (Serializable) hours);
        outState.putSerializable(KEY_HOURLY_FORECAST, (Serializable) days);
        super.onSaveInstanceState(outState);
    }

    private void getForecast(double latitude, double longitude) {

        String apiKey = "907a59f5549c9cb81373e2cd889c8292";
        String forecastUrl = "https://api.darksky.net/forecast/" + apiKey + "/" + latitude + "," + longitude;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(forecastUrl).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.d(TAG, jsonData);
                    if (response.isSuccessful()) {
                        forecast = parseForecastData(jsonData);

                        Currently currently = forecast.getCurrently();

                        displayWeather = new Currently(
                                currently.getLocationLabel(),
                                currently.getIcon(),
                                currently.getTime(),
                                currently.getTemperature(),
                                currently.getHumidty(),
                                currently.getPrecipChance(),
                                currently.getSummary(),
                                currently.getTimeZone()
                        );
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hours = Arrays.asList(forecast.getHourlyForecast());
                                days = Arrays.asList(forecast.getDailyForecast());
                                startCurrentlyFragment(currentlyFragment);
                            }
                        });
                    } else {
                        alertUserAboutError();
                    }
                } catch (IOException e) {
                    Log.d(TAG, "IO Exception caught: ", e);
                } catch (JSONException e) {

                }
            }
        });
    }

    private void startCurrentlyFragment(CurrentlyFragment savedFragment) {

        fragment = KEY_FRAGMENT_CURRENT;
        if (savedFragment == null) {
            CurrentlyFragment currentlyFragment = new CurrentlyFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putInt(CurrentlyFragment.KEY_ICON, displayWeather.getIconId());
            bundle.putString(CurrentlyFragment.KEY_TIME, displayWeather.getFormattedTime());
            bundle.putInt(CurrentlyFragment.KEY_TEMPERATURE, displayWeather.getTemperature());
            bundle.putDouble(CurrentlyFragment.KEY_HUMIDITY, displayWeather.getHumidty());
            bundle.putDouble(CurrentlyFragment.KEY_PRECIP_CHANCE, displayWeather.getPrecipChance());
            bundle.putString(CurrentlyFragment.KEY_SUMMARY, displayWeather.getSummary());
            bundle.putBoolean(CurrentlyFragment.KEY_IS_TABLET, isTablet);
            bundle.putBoolean(CurrentlyFragment.KEY_IS_HOT, displayWeather.isHot());
            currentlyFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeHolder, currentlyFragment, CurrentlyFragment.KEY_CURRENTLY_FRAGMENT);
            fragmentTransaction.commit();
        }
    }

    private Forecast parseForecastData(String jsonData) throws JSONException {
        Forecast forecast = new Forecast();
        forecast.setCurrently(getCurrentDetails(jsonData));
        forecast.setHourlyForecast(getHourlyForecast(jsonData));
        forecast.setDailyForecast(getDailyForecast(jsonData));
        return forecast;
    }

    private Day[] getDailyForecast(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timeZone = forecast.getString("timezone");
        JSONObject daily = forecast.getJSONObject("daily");
        JSONArray data = daily.getJSONArray("data");

        Day[] days = new Day[data.length()];
        for (int i = 0; i < days.length; i++) {
            JSONObject jsonDay = data.getJSONObject(i);
            Day day = new Day();
            day.setTime(jsonDay.getLong("time"));
            day.setTemperatureHigh(jsonDay.getLong("temperatureHigh"));
            day.setTemperatureLow(jsonDay.getLong("temperatureLow"));
            day.setIcon(jsonDay.getString("icon"));
            day.setTimeZone(timeZone);
            days[i] = day;
        }

        return days;
    }

    private Hour[] getHourlyForecast(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timeZone = forecast.getString("timezone");
        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray data = hourly.getJSONArray("data");

        Hour[] hours = new Hour[data.length()];
        for (int i = 0; i < hours.length; i++) {
            JSONObject jsonHour = data.getJSONObject(i);
            Hour hour = new Hour();
            hour.setSummary(jsonHour.getString("summary"));
            hour.setIcon(jsonHour.getString("icon"));
            hour.setTemperature(jsonHour.getDouble("temperature"));
            hour.setTime(jsonHour.getLong("time"));
            hour.setTimeZone(timeZone);

            hours[i] = hour;
        }

        return hours;
    }

    private Currently getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);

        String timeZone = forecast.getString("timezone");

        JSONObject currently = forecast.getJSONObject("currently");

        Currently currentForecast = new Currently();
        currentForecast.setHumidty(currently.getDouble("humidity"));
        currentForecast.setTime(currently.getLong("time"));
        currentForecast.setIcon(currently.getString("icon"));
        currentForecast.setLocationLabel("Alcatrez Island, CA");
        currentForecast.setPrecipChance(currently.getDouble("precipProbability"));
        currentForecast.setSummary(currently.getString("summary"));
        currentForecast.setTemperature(currently.getDouble("temperature"));
        currentForecast.setTimeZone(timeZone);
        return currentForecast;
    }

    private void alertUserAboutError() {
        Log.d(TAG, "ERROR");
    }

    // Does not work with Fragment subclasses. Must use onClickListener programmatically.
    @Override
    public void onRefreshClicked() {
        Toast.makeText(this, "Refreshing data...", Toast.LENGTH_SHORT).show();
        getForecast(latitude, longitude);
    }

    @Override
    public void onDetailsClicked() {

        fragment = KEY_FRAGMENT_VIEWPAGER;
        if (!isTablet) {

            // hours = Arrays.asList(forecast.getHourlyForecast());
            // days = Arrays.asList(forecast.getDailyForecast());
            Bundle bundle = new Bundle();
            bundle.putSerializable(KEY_HOURLY_FORECAST, (Serializable) hours);
            // hourlyBundle.putBoolean(HourlyFragment.KEY_IS_HOT, isAverageHot(calculateAverageTemperatureHour(hours)));
            bundle.putSerializable(KEY_DAILY_FORECAST, (Serializable) days);
            // dailyBundle.putBoolean(DailyFragment.KEY_IS_HOT, isAverageHot(calculateAverageTemperatureDays(days)));

            ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
            viewPagerFragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.placeHolder, viewPagerFragment, ViewPagerFragment.KEY_VIEWPAGER_FRAGMENT);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public int calculateAverageTemperatureHour(List<Hour> list) {
        int average = 0;
        for (int i = 0; i < list.size(); i++) {
            average += list.get(i).getFormattedTemperature();
        }
        return average / list.size();
    }

    public int calculateAverageTemperatureDays(List<Day> list) {
        int average = 0;
        for (int i = 0; i < list.size(); i++) {
            average += list.get(i).getFormattedTemperatureLow();
        }
        return average / list.size();
    }

    public boolean isAverageHot(int averageTemperature) {
        return averageTemperature > 40 ? true : false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
