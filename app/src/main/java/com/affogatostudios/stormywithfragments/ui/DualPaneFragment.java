package com.affogatostudios.stormywithfragments.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.affogatostudios.stormywithfragments.R;

public class DualPaneFragment extends Fragment {
    public static final String KEY_DUALPANE_FRAGMENT = "key_dualpane_fragment";

    private Bundle hourlyBundle;
    private Bundle dailyBundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dualpane, container, false);

        FragmentManager fragmentManager = getChildFragmentManager();
        HourlyFragment hourlyFragment = (HourlyFragment) fragmentManager.findFragmentByTag(HourlyFragment.KEY_HOURLY_FRAGMENT);
        if (hourlyFragment == null) {
            hourlyFragment = new HourlyFragment();
        }
        hourlyBundle = getArguments().getBundle(MainActivity.KEY_HOURLY_BUNDLE);
        hourlyFragment.setArguments(hourlyBundle);
        fragmentManager.beginTransaction().add(R.id.leftPlaceHolder, hourlyFragment, HourlyFragment.KEY_HOURLY_FRAGMENT).commit();

        DailyFragment dailyFragment = (DailyFragment) fragmentManager.findFragmentByTag(DailyFragment.KEY_DAILY_FRAGMENT);
        if (dailyFragment == null) {
            dailyFragment = new DailyFragment();
        }
        dailyBundle = getArguments().getBundle(MainActivity.KEY_DAILY_BUNDLE);
        dailyFragment.setArguments(dailyBundle);
        fragmentManager.beginTransaction().add(R.id.rightPlaceHolder, dailyFragment, DailyFragment.KEY_DAILY_FRAGMENT).commit();

        return view;
    }
}
