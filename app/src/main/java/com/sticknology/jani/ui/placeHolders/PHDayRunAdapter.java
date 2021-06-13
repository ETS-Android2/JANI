package com.sticknology.jani.ui.placeHolders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.MainActivity;
import com.sticknology.jani.R;
import com.sticknology.jani.data.Run;
import com.sticknology.jani.data.TrainingPlan;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PHDayRunAdapter extends RecyclerView.Adapter<PHDayRunAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Spinner rcIntervalSpinner;
        public Button rcDeleteButton;
        public Context mContext;

        public ViewHolder(View itemView) {

            super(itemView);

            rcIntervalSpinner = itemView.findViewById(R.id.spinner_rc_intervalrev);
            rcDeleteButton = itemView.findViewById(R.id.rc_rev_button_delete);
            mContext = itemView.getContext();
        }
    }

    private ArrayList<Run> mRuns;

    public PHDayRunAdapter(ArrayList<Run> runs) {
        mRuns = runs;
    }

    @Override
    public PHDayRunAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View intervalView = inflater.inflate(R.layout.item_interval_runcreation, parent, false);

        // Return a new holder instance
        PHDayRunAdapter.ViewHolder viewHolder = new PHDayRunAdapter.ViewHolder(intervalView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PHDayRunAdapter.ViewHolder holder, int position) {

        TrainingPlan trainingPlan = MainActivity.aTrainingPlan;


        Button rcDeleteButton = holder.rcDeleteButton;
        rcDeleteButton.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mRuns.size();
    }


}

