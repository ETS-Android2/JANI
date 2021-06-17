package com.sticknology.jani.ui.create.planCreation;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sticknology.jani.R;
import com.sticknology.jani.data.EmptyObjects;
import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.dataProcessing.InterpretTrainingPlan;

public class PlanCreationActivity extends AppCompatActivity {

    public enum TABSET {VIEW, TEMPLATES}
    public static TABSET currentTabSet = TABSET.VIEW;

    public static TrainingPlan mTrainingPlan;

    public static boolean isEdit;
    public static int editPlanIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plancreation);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#002C47")));

        Bundle b = getIntent().getExtras();
        if(b != null){
            mTrainingPlan = new InterpretTrainingPlan().getTrainingPlanFromString(b.getString("plan"));
            editPlanIndex = b.getInt("index");
            isEdit = true;
        } else {
            mTrainingPlan = new EmptyObjects().createEmptyTrainingPlan();
            isEdit = false;
        }

        //Starts fragment
        if (savedInstanceState == null) {

            //Create Fragment
            PlanCreateInterFragment planCreateInterFragment = PlanCreateInterFragment.newInstance(0, 0);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_create, planCreateInterFragment, null)
                    .commit();
        }

    }
}