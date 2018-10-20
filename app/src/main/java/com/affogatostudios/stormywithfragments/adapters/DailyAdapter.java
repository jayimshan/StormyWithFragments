package com.affogatostudios.stormywithfragments.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.affogatostudios.stormywithfragments.R;
import com.affogatostudios.stormywithfragments.model.Day;

import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter {
    List<Day> days;

    public DailyAdapter(List<Day> days) {
        this.days = days;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.daily_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Day day = days.get(i);
        ((ViewHolder) viewHolder).bindView(day);
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView temperatureLowLabel;
        public TextView temperatureHighLabel;
        public ImageView iconImageView;
        public TextView dayLabel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            temperatureLowLabel = itemView.findViewById(R.id.temperatureLowLabel);
            temperatureHighLabel = itemView.findViewById(R.id.temperatureHighLabel);
            iconImageView = itemView.findViewById(R.id.iconImageView);
            dayLabel = itemView.findViewById(R.id.dayLabel);
        }

        public void bindView(Day day) {
            temperatureLowLabel.setText(day.getFormattedTemperatureLow() + "");
            temperatureHighLabel.setText(day.getFormattedTemperatureHigh() + "");
            iconImageView.setImageResource(day.getIcon());
            dayLabel.setText(day.getFormattedTime());
        }
    }
}
