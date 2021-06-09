package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.Workout;
import com.sticknology.jani.dataProcessing.InterpretWorkout;

import java.util.ArrayList;

public class WorkoutTemplateFragment extends Fragment {

    private int mDayIndex;

    public static WorkoutTemplateFragment newInstance(int day) {

        WorkoutTemplateFragment f = new WorkoutTemplateFragment();
        Bundle b = new Bundle();
        b.putInt("day", day);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        mDayIndex = bundle.getInt("day");

        return inflater.inflate(R.layout.fragment_workouttemplate, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        Button newWorkoutButton = getView().findViewById(R.id.wc_button_newworkout);
        newWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WorkoutCreationFragment workoutCreationFragment = WorkoutCreationFragment.newInstance(WByWFragment.weekPosition, mDayIndex);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, workoutCreationFragment, null)
                        .addToBackStack("")
                        .commit();
            }
        });

        //Get List of Workout Templates
        InterpretWorkout interpretWorkout = new InterpretWorkout();
        ArrayList<Workout> workoutList = interpretWorkout.getWorkoutTemplates(getContext());

        //Sets up recyclerview displaying list of workout template items
        RecyclerView templateRecyclerView = getView().findViewById(R.id.wc_rev_wov);
        WorkoutTemplateAdapter workoutTemplateAdapter = new WorkoutTemplateAdapter(workoutList, mDayIndex);
        templateRecyclerView.setAdapter(workoutTemplateAdapter);
        templateRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
