package com.sticknology.jani.ui.create.planCreation;

import android.app.Activity;
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
import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.data.Types;
import com.sticknology.jani.data.Workout;
import com.sticknology.jani.dataProcessing.InterpretTrainingPlan;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

public class WorkoutTemplateAdapter extends RecyclerView.Adapter<WorkoutTemplateAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mName;
        public TextView mType;
        public TextView mDescriptor;
        public Button mAddButton;
        public Context mContext;


        public ViewHolder(View itemView){

            super(itemView);
            mName = itemView.findViewById(R.id.wov_name);
            mType = itemView.findViewById(R.id.wov_type);
            mDescriptor = itemView.findViewById(R.id.wov_description);
            mAddButton = itemView.findViewById(R.id.wov_button_add);
            mContext = itemView.getContext();
        }
    }

    private List<Workout> mWorkouts;
    private int mDayIndex;

    public WorkoutTemplateAdapter(List<Workout> workouts, int day){
        mWorkouts = workouts;
        mDayIndex = day;
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

        Button addButton = holder.mAddButton;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("THIS IS WEEKPOSITION: " + WByWFragment.weekPosition);

                Log.e("week", "Testing logcat");
                PlanCreationActivity.mTrainingPlan.getTrainingDay(WByWFragment.weekPosition, mDayIndex).addWorkout(mWorkouts.get(position));

                if(PlanCreationActivity.mTrainingPlan.getTrainingDay(WByWFragment.weekPosition, mDayIndex).getTrainingDayWorkouts()
                        .get(0).getWorkoutName().equals(":;:")){

                    PlanCreationActivity.mTrainingPlan.getTrainingDay(WByWFragment.weekPosition,mDayIndex).removeWorkout(0);
                }

                PlanCreationActivity.currentTabSet = PlanCreationActivity.TABSET.VIEW;

                PlanCreateInterFragment planCreateInterFragment = PlanCreateInterFragment.newInstance(0);
                PlanCreationActivity planCreationActivity = (PlanCreationActivity) holder.mContext;
                planCreationActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, planCreateInterFragment, null)
                        .commit();
                PlanCreateInterFragment.viewPager2.setCurrentItem(1, false);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mWorkouts.size();
    }
}
