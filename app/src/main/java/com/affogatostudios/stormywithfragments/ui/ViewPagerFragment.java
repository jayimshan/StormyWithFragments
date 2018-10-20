package com.affogatostudios.stormywithfragments.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.affogatostudios.stormywithfragments.R;
import com.affogatostudios.stormywithfragments.adapters.HourlyAdapter;

public class ViewPagerFragment extends Fragment {
    public static final String KEY_VIEWPAGER_FRAGMENT = "key_viewpager_fragment";
    private HourlyFragment hourlyFragment;
    private DailyFragment dailyFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        hourlyFragment = (HourlyFragment) getChildFragmentManager().findFragmentByTag(HourlyFragment.KEY_HOURLY_FRAGMENT);

        Bundle hourlyBundle = getArguments().getBundle("key_hourly_bundle");
        if (hourlyFragment == null) {
            hourlyFragment = new HourlyFragment();
            hourlyFragment.setArguments(hourlyBundle);
        }

        dailyFragment = (DailyFragment) getChildFragmentManager().findFragmentByTag(DailyFragment.KEY_DAILY_FRAGMENT);

        Bundle dailyBundle = getArguments().getBundle("key_daily_bundle");
        if (dailyFragment == null) {
            dailyFragment = new DailyFragment();
            dailyFragment.setArguments(dailyBundle);
        }
        /*
        Bundle bundle = getArguments().getBundle("key_hourly_bundle");
        final HourlyFragment hourlyFragment = new HourlyFragment();
        hourlyFragment.setArguments(bundle);
        bundle = getArguments().getBundle("key_daily_bundle");
        final DailyFragment dailyFragment = new DailyFragment();
        dailyFragment.setArguments(bundle);
        */

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
        super.onSaveInstanceState(outState);
    }
}
