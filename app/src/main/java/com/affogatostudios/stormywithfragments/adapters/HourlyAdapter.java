package com.affogatostudios.stormywithfragments.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.affogatostudios.stormywithfragments.R;
import com.affogatostudios.stormywithfragments.model.Hour;

import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter {
    public static final String TAG = HourlyAdapter.class.getSimpleName();

    private List<Hour> hours;

    public HourlyAdapter(List<Hour> hours) {
        this.hours = hours;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hourly_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Hour hour = hours.get(i);
        // String summary = hour.getSummary();
        ((ViewHolder) viewHolder).bindView(hour);
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView timeLabel;
        public ImageView iconImageView;
        public TextView temperatureLabel;
        public TextView summaryLabel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeLabel = itemView.findViewById(R.id.timeLabel);
            iconImageView = itemView.findViewById(R.id.iconImageView);
            temperatureLabel = itemView.findViewById(R.id.temperatureLowLabel);
            summaryLabel = itemView.findViewById(R.id.summaryLabel);
        }

        public void bindView(Hour hour) {

            timeLabel.setText(hour.getFormattedTime());
            iconImageView.setImageResource(hour.getIcon());
            temperatureLabel.setText(hour.getFormattedTemperature() + "");
            summaryLabel.setText(hour.getSummary());
        }
    }
}
