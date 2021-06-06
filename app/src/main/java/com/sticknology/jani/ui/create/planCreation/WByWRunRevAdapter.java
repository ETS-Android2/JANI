package com.sticknology.jani.ui.create.planCreation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.ListCreation;
import com.sticknology.jani.data.Run;
import com.sticknology.jani.data.TrainingDay;
import com.sticknology.jani.data.Workout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WByWRunRevAdapter extends RecyclerView.Adapter<WByWRunRevAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mName;
        public TextView mDescript;
        public Button mRemove;

        public ViewHolder(View itemView){

            super(itemView);
            System.out.println("Inside viewholder");
            mName = itemView.findViewById(R.id.wbyw_rev_rev_name);
            mDescript = itemView.findViewById(R.id.wbyw_rev_rev_descript);
            mRemove = itemView.findViewById(R.id.wbyw_rev_rev_del);
        }
    }

    private List<Workout> mWorkouts;
    private int mDayPosition;

    public WByWRunRevAdapter(List<Workout> workoutList, int dayPosition){

        mWorkouts = workoutList;
        mDayPosition = dayPosition;
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

        Log.e("test", "this is workout name being considered " + mWorkouts.get(position).getWorkoutName());
        if(!mWorkouts.get(position).getWorkoutName().equals(":;:")) {
            Log.e("test", "getting inside if statement to manually inflate");
            holder.mName.setText(mWorkouts.get(position).getWorkoutName());
            holder.mDescript.setText(mWorkouts.get(position).getWorkoutType());

            holder.mName.setVisibility(View.VISIBLE);
            holder.mDescript.setVisibility(View.VISIBLE);
            holder.mRemove.setVisibility(View.VISIBLE);

            holder.mRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    WByWFragment.mTrainingWeek.getTrainingWeekDays().get(mDayPosition).removeWorkout(position);
                    WByWFragment.mTrainingWeek = new WByWDataHandler().fixTrainingWeek(WByWFragment.mTrainingWeek);
                    WByWFragment.mWByWRevAdapter.notifyDataSetChanged();
                }
            });
        } else {
            holder.mName.setVisibility(View.GONE);
            holder.mDescript.setVisibility(View.GONE);
            holder.mRemove.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mWorkouts.size();
    }
}
