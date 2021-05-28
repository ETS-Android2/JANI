package com.sticknology.jani.ui.plan;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PlanPager extends FragmentStateAdapter {

    private static final int NUM_PAGES = 3;

    public PlanPager(Fragment frag){
        super(frag);
    }

    @Override
    public Fragment createFragment(int pos){
        switch(pos){
            case 0: {
                return PastFragment.newInstance("Past");
            }
            case 1: {

                return OverviewFragment.newInstance("Overview");
            }
            case 2: {
                return FutureFragment.newInstance("Future");
            }
            default:
                return OverviewFragment.newInstance("Overview, Default");
        }
    }

    @Override
    public int getItemCount(){
        return NUM_PAGES;
    }
}
