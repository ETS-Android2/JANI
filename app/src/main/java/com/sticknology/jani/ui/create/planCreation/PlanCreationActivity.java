package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sticknology.jani.R;
import com.sticknology.jani.data.EmptyObjects;
import com.sticknology.jani.data.TrainingPlan;

public class PlanCreationActivity extends AppCompatActivity {

    protected enum TABSET {VIEW, TEMPLATES}
    protected static TABSET currentTabSet = TABSET.VIEW;

    public static TrainingPlan mTrainingPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plancreation);

        if (mTrainingPlan == null) mTrainingPlan = new EmptyObjects().createEmptyTrainingPlan();

        //Starts fragment
        if (savedInstanceState == null) {

            //Create Fragment
            PlanCreateInterFragment planCreateInterFragment = PlanCreateInterFragment.newInstance(0);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_create, planCreateInterFragment, null)
                    .commit();
        }

    }
}