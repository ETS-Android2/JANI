package com.sticknology.jani.ui.calendar;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sticknology.jani.ui.plan.FutureFragment;
import com.sticknology.jani.ui.plan.OverviewFragment;
import com.sticknology.jani.ui.plan.PastFragment;

public class CalendarPager extends FragmentStateAdapter {

    private static final int NUM_PAGES = 3;

    public CalendarPager(Fragment frag){
        super(frag);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:{
                return DayFragment.newInstance("Day");
            }
            case 1:{
                return WeekFragment.newInstance("Week");
            }
            case 2: {
                return MonthFragment.newInstance("Month");
            }
            default:{
                return DayFragment.newInstance("Day, default");
            }
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}