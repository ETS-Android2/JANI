package com.sticknology.jani.ui.placeHolders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.dataProcessing.InterpretTrainingPlan;
import com.sticknology.jani.dataProcessing.StandardReadWrite;
import com.sticknology.jani.ui.create.planCreation.PlanCreationActivity;

import java.util.ArrayList;
import java.util.List;

public class PHCreateFragment extends Fragment {

    public static PHCreateFragment newInstance(String text) {

        PHCreateFragment f = new PHCreateFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_manage, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        Button newButton = getView().findViewById(R.id.button_new_plan_manage);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlanCreationActivity.currentTabSet = PlanCreationActivity.TABSET.VIEW;
                Intent newPlanActivity = new Intent(getActivity().getApplicationContext(), PlanCreationActivity.class);
                startActivity(newPlanActivity);
            }
        });

        List<TrainingPlan> trainingPlanList = new ArrayList<>();
        String readPlanFile = new StandardReadWrite().readFileToString("training_plans.txt", getContext());
        String[] readFileSplit = readPlanFile.split("\n");
        for(int i = 0; i < readFileSplit.length; i++){
            if(!readFileSplit[i].equals("") && !readFileSplit[i].equals(" ")){
                trainingPlanList.add(new InterpretTrainingPlan().getTrainingPlanFromString(readFileSplit[i]));
            }
        }
        RecyclerView planRev = getView().findViewById(R.id.rev_manage);
        PHCreateAdapter phCreateAdapter = new PHCreateAdapter(trainingPlanList);
        planRev.setAdapter(phCreateAdapter);
        planRev.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}

