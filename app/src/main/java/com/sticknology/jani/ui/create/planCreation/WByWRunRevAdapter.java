package com.sticknology.jani.ui.create.planCreation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.Run;
import com.sticknology.jani.data.TrainingDay;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WByWRunRevAdapter extends RecyclerView.Adapter<WByWRunRevAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView){

            super(itemView);
        }
    }

    private List<Run> mTrainingDays;

    public WByWRunRevAdapter(List<Run> trainingDayList){
        mTrainingDays = trainingDayList;
    }

    @Override
    public WByWRunRevAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View runView = inflater.inflate(R.layout.item_day_rev, parent, false);

        // Return a new holder instance
        WByWRunRevAdapter.ViewHolder viewHolder = new WByWRunRevAdapter.ViewHolder(runView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WByWRunRevAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mTrainingDays.size();
    }
}
