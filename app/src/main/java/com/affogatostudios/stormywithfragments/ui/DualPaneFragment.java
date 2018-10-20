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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dualpane, container, false);

        FragmentManager fragmentManager = getChildFragmentManager();
        HourlyFragment savedHourlyFragment = (HourlyFragment) fragmentManager.findFragmentByTag(HourlyFragment.KEY_HOURLY_FRAGMENT);

        if (savedHourlyFragment == null) {
            HourlyFragment hourlyFragment = new HourlyFragment();
            Bundle bundle = getArguments().getBundle("key_hourly_bundle");
            hourlyFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.leftPlaceHolder, hourlyFragment, HourlyFragment.KEY_HOURLY_FRAGMENT).commit();
        }

        DailyFragment savedDailyFragment = (DailyFragment) fragmentManager.findFragmentByTag(DailyFragment.KEY_DAILY_FRAGMENT);

        if (savedDailyFragment == null) {
            DailyFragment dailyFragment = new DailyFragment();
            Bundle bundle = getArguments().getBundle("key_daily_bundle");
            dailyFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.rightPlaceHolder, dailyFragment, DailyFragment.KEY_DAILY_FRAGMENT).commit();
        }

        return view;
    }
}
