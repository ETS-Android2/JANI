package com.sticknology.jani.ui.create.planCreation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.sticknology.jani.MainActivity;
import com.sticknology.jani.R;
import com.sticknology.jani.dataProcessing.InterpretTrainingPlan;
import com.sticknology.jani.dataProcessing.StandardReadWrite;

import static com.sticknology.jani.ui.create.planCreation.PlanCreationActivity.mTrainingPlan;

public class EditOverviewFragment extends Fragment {

    public static EditOverviewFragment newInstance() {

        EditOverviewFragment f = new EditOverviewFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_overview_creation, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ActionBar actionBar = ((PlanCreationActivity) getActivity()).getSupportActionBar();

        EditText nameText = getView().findViewById(R.id.pc_edittext_planname);
        EditText goalText = getView().findViewById(R.id.pc_edittext_plangoal);
        EditText descriptText = getView().findViewById(R.id.pc_edittext_plandescript);

        //Set Text for When Navigated Back To Fragment
        if(!mTrainingPlan.getTrainingPlanName().equals(":;:")) {
            nameText.setText(mTrainingPlan.getTrainingPlanName());
        } else {
            nameText.setText("");
        }
        goalText.setText(mTrainingPlan.getTrainingPlanGoal());
        descriptText.setText(mTrainingPlan.getTrainingPlanDescriptor());

        //Set Listeners for Updating TrainingPlan Object
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                mTrainingPlan.setTrainingPlanName(nameText.getText().toString());
            }
        });
        goalText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                mTrainingPlan.setTrainingPlanGoal(goalText.getText().toString());
            }
        });
        descriptText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                mTrainingPlan.setTrainingPlanDescriptor(descriptText.getText().toString());
            }
        });

        //Set Behavior for Saving Plan
        Button savePlan = getView().findViewById(R.id.pc_button_saveplan);
        savePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String planString = new InterpretTrainingPlan().getStringFromTrainingPlan(mTrainingPlan);
                StandardReadWrite standardReadWrite = new StandardReadWrite();

                System.out.println("THIS IS SAVE BOOLEAN  " + PlanCreationActivity.isEdit);

                if(!PlanCreationActivity.isEdit) {
                    //Save TrainingPlan to File
                    standardReadWrite.appendText(planString, "training_plans.txt", getContext(), true);
                } else {
                    System.out.println("GOT INSIDE THE CORRECT SAVE CODE");
                    //Replace Edited Plan and Save to File
                    String tplanfile = standardReadWrite.readFileToString("training_plans.txt", getContext());
                    String[] fileSplit = tplanfile.split("\n");
                    //Plus 2 to index bc of blank line and version line?
                    fileSplit[PlanCreationActivity.editPlanIndex +2] = planString;
                    StringBuilder build = new StringBuilder();
                    //i = 1 because of
                    for(int i = 1; i < fileSplit.length; i++){
                        build.append(fileSplit[i]);
                        if(i < fileSplit.length-1){
                            build.append("\n");
                        }
                    }
                    standardReadWrite.writeFile(build.toString(), "training_plans.txt", getContext());
                }

                //Launches Back to MainActivity
                Intent newMainActivity = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(newMainActivity);
            }
        });

        //Set Behavior for Cancel Button
        Button cancelPlan = getView().findViewById(R.id.pc_button_cancelplan);
        cancelPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newMainActivity = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(newMainActivity);
            }
        });

    }
}
