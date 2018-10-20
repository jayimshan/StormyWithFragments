package com.affogatostudios.stormywithfragments.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.affogatostudios.stormywithfragments.R;

public class CurrentlyFragment extends Fragment {
    public static final String KEY_CURRENTLY_FRAGMENT = "key_currently_fragment";
    public static final String KEY_LOCATION = "key_location";
    public static final String KEY_ICON = "key_icon";
    public static final String KEY_TIME = "key_time";
    public static final String KEY_TEMPERATURE = "key_temperature";
    public static final String KEY_HUMIDITY = "key_humidity";
    public static final String KEY_PRECIP_CHANCE = "key_precip_chance";
    public static final String KEY_SUMMARY = "key_summary";
    public static final String KEY_TIME_ZONE = "key_time_zone";
    public static final String KEY_IS_TABLET = "key_is_tablet";
    public static final String KEY_IS_HOT = "key_is_hot";

    private TextView temperatureValue;
    private TextView timeValue;
    private TextView locationValue;
    private TextView humidityValue;
    private TextView precipValue;
    private TextView summaryValue;

    private ImageView iconImageView;

    private Button detailsButton;

    private ImageButton refreshButton;

    private ConstraintLayout currentlyLayout;

    public interface OnHourlyForecastButtonSelected {
        void onButtonClicked(int fragment);
    }

    public interface OnRefreshForecastButtonSelected {
        void onRefreshClicked();
    }

    public interface OnDetailsButtonSelected {
        void onDetailsClicked();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final OnHourlyForecastButtonSelected listener = (OnHourlyForecastButtonSelected) getActivity();
        final OnRefreshForecastButtonSelected refreshListener = (OnRefreshForecastButtonSelected) getActivity();
        final OnDetailsButtonSelected detailListener = (OnDetailsButtonSelected) getActivity();
        View view = inflater.inflate(R.layout.fragment_currently, container, false);
        initViews(view);

        boolean isTablet = getArguments().getBoolean(KEY_IS_TABLET);

        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Details button clicked", Toast.LENGTH_SHORT).show();
                detailListener.onDetailsClicked();
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshListener.onRefreshClicked();
            }
        });

        setDisplayWeather();
        return view;
    }

    private void initViews(View view) {
        temperatureValue = view.findViewById(R.id.temperatureValue);
        timeValue = view.findViewById(R.id.timeValue);
        locationValue = view.findViewById(R.id.locationValue);
        humidityValue = view.findViewById(R.id.humidtyValue);
        precipValue = view.findViewById(R.id.precipValue);
        summaryValue = view.findViewById(R.id.summaryValue);
        iconImageView = view.findViewById(R.id.iconImageView);
        detailsButton = view.findViewById(R.id.detailsButton);
        refreshButton = view.findViewById(R.id.refreshButton);
        currentlyLayout = view.findViewById(R.id.currentlyLayout);
    }

    private void setDisplayWeather() {
        Drawable drawable = getResources().getDrawable(getArguments().getInt(KEY_ICON));
        iconImageView.setImageDrawable(drawable);
        int temperature = getArguments().getInt(KEY_TEMPERATURE);
        String time = getArguments().getString(KEY_TIME);
        double humidity = getArguments().getDouble(KEY_HUMIDITY);
        double precip = getArguments().getDouble(KEY_PRECIP_CHANCE);
        String summary = getArguments().getString(KEY_SUMMARY);
        iconImageView.setImageDrawable(drawable);
        temperatureValue.setText(temperature + "");
        timeValue.setText(time);
        locationValue.setText("Alcatrez Island, CA");
        humidityValue.setText(humidity + "");
        precipValue.setText(precip + "%");
        summaryValue.setText(summary);
        if (getArguments().getBoolean(KEY_IS_HOT)) {
            currentlyLayout.setBackgroundResource(R.drawable.background_gradient_hot);
        } else {
            currentlyLayout.setBackgroundResource(R.drawable.background_gradient_cold);
        }
    }
}
