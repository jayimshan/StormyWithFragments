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
import com.affogatostudios.stormywithfragments.model.Day;
import com.affogatostudios.stormywithfragments.model.Hour;

import java.io.Serializable;
import java.util.List;

public class DualPaneFragment extends Fragment {
    public static final String KEY_DUALPANE_FRAGMENT = "key_dualpane_fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        List<Hour> hours = (List<Hour>) getArguments().getSerializable(MainActivity.KEY_HOURLY_FORECAST);
        List<Day> days = (List<Day>) getArguments().getSerializable(MainActivity.KEY_DAILY_FORECAST);
        View view = inflater.inflate(R.layout.fragment_dualpane, container, false);

        FragmentManager fragmentManager = getChildFragmentManager();
        HourlyFragment hourlyFragment = new HourlyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.KEY_HOURLY_FORECAST, (Serializable) hours);
        hourlyFragment.setArguments(bundle);
        fragmentManager.beginTransaction().add(R.id.leftPlaceHolder, hourlyFragment, HourlyFragment.KEY_HOURLY_FRAGMENT).commit();

        DailyFragment dailyFragment = new DailyFragment();
        bundle = new Bundle();
        bundle.putSerializable(MainActivity.KEY_DAILY_FORECAST, (Serializable) days);
        dailyFragment.setArguments(bundle);
        fragmentManager.beginTransaction().add(R.id.rightPlaceHolder, dailyFragment, DailyFragment.KEY_DAILY_FRAGMENT).commit();

        return view;
    }
}
