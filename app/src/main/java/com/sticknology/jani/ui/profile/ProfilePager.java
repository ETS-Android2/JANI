package com.sticknology.jani.ui.profile;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sticknology.jani.ui.plan.FutureFragment;
import com.sticknology.jani.ui.plan.OverviewFragment;
import com.sticknology.jani.ui.plan.PastFragment;

public class ProfilePager extends FragmentStateAdapter {

    private static final int NUM_PAGES = 3;

    public ProfilePager(Fragment frag){
        super(frag);
    }

    @Override
    public Fragment createFragment(int pos){
        switch(pos){
            case 0: {
                return StatsFragment.newInstance("Stats");
            }
            case 1: {

                return PreferencesFragment.newInstance("Preferences");
            }
            case 2: {
                return AboutFragment.newInstance("About");
            }
            default:
                return StatsFragment.newInstance("Stats, Default");
        }
    }

    @Override
    public int getItemCount(){
        return NUM_PAGES;
    }
}
