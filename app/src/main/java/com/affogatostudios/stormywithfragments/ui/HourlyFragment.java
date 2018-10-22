package com.affogatostudios.stormywithfragments.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.affogatostudios.stormywithfragments.R;
import com.affogatostudios.stormywithfragments.adapters.HourlyAdapter;
import com.affogatostudios.stormywithfragments.model.Hour;

import java.io.Serializable;
import java.util.List;

public class HourlyFragment extends Fragment {
    public static final String KEY_HOURLY_FRAGMENT = "key_hourly_fragment";
    public static final String KEY_IS_HOT = "key_is_hot";
    public static final String TAG = HourlyFragment.class.getSimpleName();

    private boolean isHot = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        List<Hour> hours = (List<Hour>) getArguments().getSerializable(MainActivity.KEY_HOURLY_FORECAST);
        View view = inflater.inflate(R.layout.fragment_hourly, container, false);

        RecyclerView hourlyRecyclerView = view.findViewById(R.id.hourlyRecyclerView);
        /*
        RelativeLayout relativeLayout = view.findViewById(R.id.hourlyLayout);
        if (isHot) {
            relativeLayout.setBackgroundResource(R.drawable.background_gradient_hot);
        } else {
            relativeLayout.setBackgroundResource(R.drawable.background_gradient_cold);
        }
        */

        hourlyRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        HourlyAdapter adapter = new HourlyAdapter(hours);
        hourlyRecyclerView.setAdapter(adapter);
        hourlyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
