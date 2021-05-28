package com.sticknology.jani.ui.create;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CreatePager extends FragmentStateAdapter {

    private static final int NUM_PAGES = 2;

    public CreatePager(Fragment frag){
        super(frag);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:{
                return ManageFragment.newInstance("manage fragment");
            }
            case 1:{
                return ExportFragment.newInstance("export fragment");
            }
            default:{
                return ManageFragment.newInstance("manage fragment, default");
            }
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
