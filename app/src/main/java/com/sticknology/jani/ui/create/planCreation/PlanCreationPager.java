package com.sticknology.jani.ui.create.planCreation;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PlanCreationPager extends FragmentStateAdapter {

    private static final int NUM_TABS = 2;
    private String mPlan;
    private int mWeekIndex;
    private int mDayIndex;

    public PlanCreationPager(Fragment fragment, String test, int week, int day) {
        super(fragment);
        mPlan = test;
        mWeekIndex = week;
        mDayIndex = day;
    }

    @Override
    public Fragment createFragment(int position) {

        PlanCreationActivity.TABSET tabset = PlanCreationActivity.currentTabSet;

        if(tabset == PlanCreationActivity.TABSET.VIEW && position == 0){
            return EditOverviewFragment.newInstance(mPlan);
        } else if(tabset == PlanCreationActivity.TABSET.VIEW && position == 1){
            return WByWFragment.newInstance(mPlan);
        } else if(tabset == PlanCreationActivity.TABSET.TEMPLATES && position == 0){
            return RunTemplateFragment.newInstance(mPlan);
        } else if(tabset == PlanCreationActivity.TABSET.TEMPLATES && position == 1){
            return WorkoutTemplateFragment.newInstance(mPlan, mWeekIndex, mDayIndex);
        } else{
            return EditOverviewFragment.newInstance(mPlan);
        }
    }

    @Override
    public int getItemCount() {return NUM_TABS;}
}
