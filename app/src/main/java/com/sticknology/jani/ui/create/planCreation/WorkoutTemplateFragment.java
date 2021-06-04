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
import com.sticknology.jani.data.ListCreation;
import com.sticknology.jani.data.Workout;
import com.sticknology.jani.dataProcessing.InterpretWorkout;
import com.sticknology.jani.ui.create.ManageFragment;

import java.util.ArrayList;

public class WorkoutTemplateFragment extends Fragment {

    private String mPlan;

    public static WorkoutTemplateFragment newInstance(String plan) {

        WorkoutTemplateFragment f = new WorkoutTemplateFragment();
        Bundle b = new Bundle();
        b.putString("plan", plan);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        mPlan = bundle.getString("plan");
        System.out.println(mPlan + " this is plan inside workouttemplatefragment");
        return inflater.inflate(R.layout.fragment_workouttemplate, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        Button newWorkoutButton = getView().findViewById(R.id.wc_button_newworkout);
        newWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WorkoutCreationFragment workoutCreationFragment = WorkoutCreationFragment.newInstance(mPlan);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, workoutCreationFragment, null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        //Get List of Workout Templates
        InterpretWorkout interpretWorkout = new InterpretWorkout();
        ArrayList<Workout> workoutList = interpretWorkout.getWorkoutTemplates(getContext());

        //Sets up recyclerview displaying list of workout template items
        RecyclerView templateRecyclerView = getView().findViewById(R.id.wc_rev_wov);
        //ArrayList<Workout> workoutList = new ListCreation().createEmptyWorkoutList();
        WorkoutTemplateAdapter workoutTemplateAdapter = new WorkoutTemplateAdapter(workoutList);
        templateRecyclerView.setAdapter(workoutTemplateAdapter);
        templateRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
