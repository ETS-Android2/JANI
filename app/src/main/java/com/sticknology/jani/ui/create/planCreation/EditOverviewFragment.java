package com.sticknology.jani.ui.create.planCreation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.sticknology.jani.MainActivity;
import com.sticknology.jani.R;
import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.dataProcessing.InterpretTrainingPlan;
import com.sticknology.jani.dataProcessing.StandardReadWrite;

import static com.sticknology.jani.ui.create.planCreation.PlanCreationActivity.mTrainingPlan;

public class EditOverviewFragment extends Fragment {

    public static EditOverviewFragment newInstance() {

        EditOverviewFragment f = new EditOverviewFragment();
        Bundle b = new Bundle();

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_overview_creation, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        EditText nameText = getView().findViewById(R.id.pc_edittext_planname);
        EditText goalText = getView().findViewById(R.id.pc_edittext_plangoal);
        EditText descriptText = getView().findViewById(R.id.pc_edittext_plandescript);
        if(!mTrainingPlan.getTrainingPlanName().equals(" ") && !mTrainingPlan.getTrainingPlanName().equals(":;:")) {
            nameText.setText(mTrainingPlan.getTrainingPlanName());
        }
        if(!mTrainingPlan.getTrainingPlanGoal().equals(" ") && !mTrainingPlan.getTrainingPlanGoal().equals(":;:")) {
            goalText.setText(mTrainingPlan.getTrainingPlanGoal());
        }
        if(!mTrainingPlan.getTrainingPlanDescriptor().equals(" ") && !mTrainingPlan.getTrainingPlanDescriptor().equals(":;:")){
            descriptText.setText(mTrainingPlan.getTrainingPlanDescriptor());
        }
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTrainingPlan.setTrainingPlanName(nameText.getText().toString());
            }
        });
        goalText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTrainingPlan.setTrainingPlanGoal(goalText.getText().toString());
            }
        });
        descriptText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTrainingPlan.setTrainingPlanDescriptor(descriptText.getText().toString());
            }
        });


        Button savePlan = getView().findViewById(R.id.pc_button_saveplan);
        savePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameText.getText().toString();
                String goal = goalText.getText().toString();
                String descript = descriptText.getText().toString();

                TrainingPlan trainingPlan = mTrainingPlan;


                //trainingPlan.setTrainingPlanGoal(goal);
                //trainingPlan.setTrainingPlanDescriptor(descript);

                String planString = new InterpretTrainingPlan().getStringFromTrainingPlan(trainingPlan);
                StandardReadWrite standardReadWrite = new StandardReadWrite();
                standardReadWrite.appendText(planString, "training_plans.txt", getContext(), Activity.MODE_APPEND);

                Intent newMainActivity = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(newMainActivity);
            }
        });

    }
}
