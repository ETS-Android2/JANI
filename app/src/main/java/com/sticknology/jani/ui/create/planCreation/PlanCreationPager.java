package com.sticknology.jani.ui.create.planCreation;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PlanCreationPager extends FragmentStateAdapter {

    private static final int NUM_TABS = 2;
    private int mDayIndex;

    public PlanCreationPager(Fragment fragment, int day) {
        super(fragment);
        mDayIndex = day;
    }

    @Override
    public Fragment createFragment(int position) {

        PlanCreationActivity.TABSET tabset = PlanCreationActivity.currentTabSet;

        if(tabset == PlanCreationActivity.TABSET.VIEW && position == 0){
            return EditOverviewFragment.newInstance();
        } else if(tabset == PlanCreationActivity.TABSET.VIEW && position == 1){
            return WByWFragment.newInstance();
        } else if(tabset == PlanCreationActivity.TABSET.TEMPLATES && position == 0){
            return RunTemplateFragment.newInstance(mDayIndex);
        } else if(tabset == PlanCreationActivity.TABSET.TEMPLATES && position == 1){
            return WorkoutTemplateFragment.newInstance(mDayIndex);
        } else{
            return EditOverviewFragment.newInstance();
        }
    }

    @Override
    public int getItemCount() {return NUM_TABS;}
}
