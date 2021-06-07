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
import com.sticknology.jani.data.Run;
import com.sticknology.jani.data.Workout;
import com.sticknology.jani.dataProcessing.InterpretRun;
import com.sticknology.jani.dataProcessing.InterpretWorkout;
import com.sticknology.jani.ui.create.ManageFragment;

import java.util.ArrayList;

public class RunTemplateFragment extends Fragment {

    private int mDayPosition;

    public static RunTemplateFragment newInstance(int dayPosition) {

        RunTemplateFragment f = new RunTemplateFragment();
        Bundle b = new Bundle();
        b.putInt("day", dayPosition);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mDayPosition = getArguments().getInt("day");
        return inflater.inflate(R.layout.fragment_runtemplate, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button newButton = getView().findViewById(R.id.button_newrun_template);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RunCreationFragment runCreationFragment = RunCreationFragment.newInstance(mDayPosition);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, runCreationFragment, null)
                        .commit();
            }
        });

        //Get List of Workout Templates
        InterpretRun interpretRun = new InterpretRun();
        ArrayList<Run> runList = interpretRun.getRunTemplates(getContext());

        //Sets up recyclerview displaying list of workout template items
        RecyclerView templateRecyclerView = getView().findViewById(R.id.runtemplate_recyclerview);
        RunTemplateAdapter runTemplateAdapter = new RunTemplateAdapter(runList, mDayPosition);
        templateRecyclerView.setAdapter(runTemplateAdapter);
        templateRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
