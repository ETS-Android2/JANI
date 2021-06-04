package com.sticknology.jani.ui.create.planCreation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.Workout;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

public class WorkoutTemplateAdapter extends RecyclerView.Adapter<WorkoutTemplateAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mName;
        public TextView mType;
        public TextView mDescriptor;


        public ViewHolder(View itemView){

            super(itemView);
            mName = itemView.findViewById(R.id.wov_name);
            mType = itemView.findViewById(R.id.wov_type);
            mDescriptor = itemView.findViewById(R.id.wov_description);
        }
    }

    private List<Workout> mWorkouts;

    public WorkoutTemplateAdapter(List<Workout> workouts){
        mWorkouts = workouts;
    }

    @Override
    public WorkoutTemplateAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View workoutView = inflater.inflate(R.layout.item_workouttemplate_view, parent, false);

        // Return a new holder instance
        WorkoutTemplateAdapter.ViewHolder viewHolder = new WorkoutTemplateAdapter.ViewHolder(workoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WorkoutTemplateAdapter.ViewHolder holder, int position) {

        TextView nameView = holder.mName;
        TextView typeView = holder.mType;
        TextView descriptorView = holder.mDescriptor;
        nameView.setText(mWorkouts.get(position).getWorkoutName());
        typeView.setText(mWorkouts.get(position).getWorkoutType());
        descriptorView.setText(mWorkouts.get(position).getWorkoutDescriptor());
    }

    @Override
    public int getItemCount() {
        return mWorkouts.size();
    }
}
