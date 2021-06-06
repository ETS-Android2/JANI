package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sticknology.jani.R;
import com.sticknology.jani.data.ListCreation;
import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.dataProcessing.InterpretTrainingPlan;
import com.sticknology.jani.ui.calendar.CalendarPager;
import com.sticknology.jani.ui.create.CreatePager;
import com.sticknology.jani.ui.plan.PlanPager;
import com.sticknology.jani.ui.profile.ProfilePager;

public class PlanCreationActivity extends AppCompatActivity {

    protected enum TABSET {VIEW, TEMPLATES}
    protected static TABSET currentTabSet = TABSET.VIEW;

    public static TrainingPlan mTrainingPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plancreation);

        if(mTrainingPlan == null){
            mTrainingPlan = new ListCreation().createEmptyTrainingPlan();
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