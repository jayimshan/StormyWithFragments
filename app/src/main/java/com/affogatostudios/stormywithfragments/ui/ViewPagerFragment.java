package com.affogatostudios.stormywithfragments.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.affogatostudios.stormywithfragments.R;
import com.affogatostudios.stormywithfragments.adapters.HourlyAdapter;
import com.affogatostudios.stormywithfragments.model.Day;
import com.affogatostudios.stormywithfragments.model.Hour;

import java.io.Serializable;
import java.util.List;

public class ViewPagerFragment extends Fragment {
    public static final String KEY_VIEWPAGER_FRAGMENT = "key_viewpager_fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
        if (savedInstanceState != null && savedInstanceState.getSerializable(MainActivity.KEY_HOURLY_FORECAST) != null) {
            hours = (List<Hour>) savedInstanceState.getSerializable(MainActivity.KEY_HOURLY_FORECAST);
        } else {
            hours = (List<Hour>) getArguments().getSerializable(MainActivity.KEY_HOURLY_FORECAST);
        }
        if (savedInstanceState != null && savedInstanceState.getSerializable(MainActivity.KEY_DAILY_FORECAST) != null) {
            days = (List<Day>) savedInstanceState.getSerializable(MainActivity.KEY_DAILY_FORECAST);
        } else {
            days = (List<Day>) getArguments().getSerializable(MainActivity.KEY_DAILY_FORECAST);
        }
        */
        List<Hour> hours = (List<Hour>) getArguments().getSerializable(MainActivity.KEY_HOURLY_FORECAST);
        List<Day> days = (List<Day>) getArguments().getSerializable(MainActivity.KEY_DAILY_FORECAST);

        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        final HourlyFragment hourlyFragment = new HourlyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.KEY_HOURLY_FORECAST, (Serializable) hours);
        hourlyFragment.setArguments(bundle);
        final DailyFragment dailyFragment = new DailyFragment();
        bundle = new Bundle();
        bundle.putSerializable(MainActivity.KEY_DAILY_FORECAST, (Serializable) days);
        dailyFragment.setArguments(bundle);

        ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return i == 0 ? hourlyFragment : dailyFragment;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "Hourly" : "Daily";
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        // tabLayout.setBackgroundResource(R.color.colorPrimaryDark);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        // outState.putSerializable(MainActivity.KEY_HOURLY_FORECAST, (Serializable) hours);
        // outState.putSerializable(MainActivity.KEY_DAILY_FORECAST, (Serializable) days);
        super.onSaveInstanceState(outState);
    }
}
