package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.Workout;
import com.sticknology.jani.dataProcessing.InterpretWorkout;
import com.sticknology.jani.dataProcessing.StandardReadWrite;

import java.util.ArrayList;

public class WorkoutTemplateFragment extends Fragment {

    private int mDayIndex;
    private WorkoutTemplateAdapter workoutTemplateAdapter;
    private RecyclerView templateRecyclerView;
    public static ArrayList<Workout> workoutList;

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
        setHasOptionsMenu(true);

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
        workoutList = interpretWorkout.getWorkoutTemplates(getContext());

        //Sets up recyclerview displaying list of workout template items
        templateRecyclerView = getView().findViewById(R.id.wc_rev_wov);
        workoutTemplateAdapter = new WorkoutTemplateAdapter(workoutList, mDayIndex);
        templateRecyclerView.setAdapter(workoutTemplateAdapter);
        templateRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.delete_button_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.abar_delete_button){
            //Iterate through child items of recycler view, index based off of adapter
            for(int i = 0; i < workoutTemplateAdapter.getItemCount(); i++){
                //Get Child View, update text and set listener for items
                View mChild = templateRecyclerView.getChildAt(i);
                Button nowDelete = mChild.findViewById(R.id.wov_button_add);
                nowDelete.setText("Delete");
                int index = i;
                nowDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Sets the version of the template file
                        String build = getString(R.string.file_encoding);;

                        workoutList.remove(index);
                        for(int u = 0; u < workoutList.size(); u++){
                            build += "\n" + new InterpretWorkout().getStringWorkout(workoutList.get(u));
                        }
                        new StandardReadWrite().writeFile(build, "workout_templates.txt", getContext());
                        workoutList = new InterpretWorkout().getWorkoutTemplates(getContext());
                        System.err.println("THIS IS SIZE: " + workoutList.size());
                        workoutTemplateAdapter.notifyDataSetChanged();

                    }
                });
            }

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

}
