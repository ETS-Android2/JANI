package com.sticknology.jani.ui.create.planCreation;

import android.app.Activity;
import android.content.Context;
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
    private String mPlan;
    private int mWeekIndex;
    private int mDayIndex;

    public WorkoutTemplateAdapter(List<Workout> workouts, String plan, int week, int day){
        mWorkouts = workouts;
        mPlan = plan;
        mWeekIndex = week;
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

                PlanCreationActivity planCreationActivity = (PlanCreationActivity) holder.mContext;
                TrainingPlan trainingPlan = new InterpretTrainingPlan().getTrainingPlanFromString(mPlan);
                trainingPlan.getTrainingDay(mWeekIndex, mDayIndex).addWorkout(mWorkouts.get(position));

                if(trainingPlan.getTrainingDay(mWeekIndex, mDayIndex).getTrainingDayWorkouts()
                        .get(0).getWorkoutName().equals(":;:")){

                    trainingPlan.getTrainingDay(mWeekIndex,mDayIndex).removeWorkout(0);
                }

                String newPlan = new InterpretTrainingPlan().getStringFromTrainingPlan(trainingPlan);
                PlanCreationActivity.currentTabSet = PlanCreationActivity.TABSET.VIEW;
                PlanCreateInterFragment planCreateInterFragment = PlanCreateInterFragment.newInstance(newPlan, 0, 0);
                planCreationActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, planCreateInterFragment, null)
                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mWorkouts.size();
    }
}
