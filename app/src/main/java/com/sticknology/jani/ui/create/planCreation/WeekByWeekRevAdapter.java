package com.sticknology.jani.ui.create.planCreation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.TrainingDay;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WeekByWeekRevAdapter extends RecyclerView.Adapter<WeekByWeekRevAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView){

            super(itemView);
        }
    }

    private List<TrainingDay> mTrainingDayList;
    public WeekByWeekRevAdapter(List<TrainingDay> dayList){
        mTrainingDayList = dayList;
    }

    @Override
    public WeekByWeekRevAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View intervalView = inflater.inflate(R.layout.item_day_wbw, parent, false);

        // Return a new holder instance
        WeekByWeekRevAdapter.ViewHolder viewHolder = new WeekByWeekRevAdapter.ViewHolder(intervalView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mTrainingDayList.size();
    }

}
