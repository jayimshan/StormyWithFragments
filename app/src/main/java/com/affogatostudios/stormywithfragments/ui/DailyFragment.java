package com.affogatostudios.stormywithfragments.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.affogatostudios.stormywithfragments.R;
import com.affogatostudios.stormywithfragments.adapters.DailyAdapter;
import com.affogatostudios.stormywithfragments.model.Day;

import java.util.List;

public class DailyFragment extends Fragment {
    public static final String KEY_DAILY_FRAGMENT = "key_daily_fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        List<Day> days = (List<Day>) getArguments().getSerializable(MainActivity.KEY_DAILY_FORECAST);
        View view = inflater.inflate(R.layout.fragment_daily, container, false);

        RecyclerView dailyRecyclerView = view.findViewById(R.id.dailyRecyclerView);

        dailyRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        DailyAdapter adapter = new DailyAdapter(days);
        dailyRecyclerView.setAdapter(adapter);
        dailyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
