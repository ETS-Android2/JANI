package com.sticknology.jani.ui.create.planCreation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sticknology.jani.R;
import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.data.Workout;
import com.sticknology.jani.dataProcessing.InterpretTrainingPlan;
import com.sticknology.jani.dataProcessing.InterpretWorkout;
import com.sticknology.jani.dataProcessing.StandardReadWrite;

public class WorkoutCreationFragment extends Fragment {

    public Spinner workoutTypeSpinner;

    private ArrayAdapter<CharSequence> dataAdapter;
    private TrainingPlan mTrainingPlan;

    public static WorkoutCreationFragment newInstance(String plan) {

        WorkoutCreationFragment f = new WorkoutCreationFragment();
        Bundle b = new Bundle();
        b.putString("plan", plan);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String mPlan = bundle.getString("plan");
        mTrainingPlan = new InterpretTrainingPlan().getTrainingPlanFromString(mPlan);
        return inflater.inflate(R.layout.fragment_workoutcreation, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //Set up spinner for workout type
        workoutTypeSpinner = getView().findViewById(R.id.wc_wc_workouttype);
        dataAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.workouttype_array, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workoutTypeSpinner.setAdapter(dataAdapter);

        Button saveWorkout = getView().findViewById(R.id.wc_wc_buttonsave);
        saveWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Switch templateSwitch = getView().findViewById(R.id.wc_wc_switchtemplate);
                TextView workoutName = getView().findViewById(R.id.wc_wc_workoutname);
                TextView workoutDescript = getView().findViewById(R.id.wc_wc_workoutdescript);
                Workout workout = new Workout(workoutName.getText().toString(),
                        workoutTypeSpinner.getSelectedItem().toString(), workoutDescript.getText().toString());

                if(templateSwitch.isChecked()) {
                    StandardReadWrite standardReadWrite = new StandardReadWrite();
                    InterpretWorkout interpretWorkout = new InterpretWorkout();
                    String workoutString = interpretWorkout.getStringWorkout(workout);
                    workoutString = workoutString.replace("\n", " ");
                    standardReadWrite.appendText(workoutString,"workout_templates.txt", getContext(), Context.MODE_APPEND);
                }

                //Add Workout to Training Plan
                mTrainingPlan.getTrainingDay(0,0).addWorkout(workout);
                if(mTrainingPlan.getTrainingDay(0, 0).getTrainingDayWorkouts()
                        .get(0).getWorkoutName().equals(":;:")){

                    mTrainingPlan.getTrainingDay(0,0).removeWorkout(0);
                }

                System.out.println("SAVING NEW WORKOUT");

                //Navigation back to overview
                //TODO: Make navigation back to weekbyweek
                String trainingPlanString = new InterpretTrainingPlan().getStringFromTrainingPlan(mTrainingPlan);
                PlanCreationActivity.currentTabSet = PlanCreationActivity.TABSET.VIEW;
                PlanCreateInterFragment planCreateInterFragment = PlanCreateInterFragment.newInstance(trainingPlanString);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, planCreateInterFragment, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
