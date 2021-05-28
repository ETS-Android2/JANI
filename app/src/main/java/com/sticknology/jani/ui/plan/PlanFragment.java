package com.sticknology.jani.ui.plan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sticknology.jani.R;
import com.sticknology.jani.databinding.FragmentPlanBinding;

public class PlanFragment extends Fragment {

    private FragmentPlanBinding binding;

    // Array of strings FOR TABS TITLES
    private String[] titles = new String[]{"Past", "Overview", "Future"};
    // tab titles

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        ViewPager2 myViewPager = getView().findViewById(R.id.plan_viewPager2);
        PlanPager planPager = new PlanPager(this);
        myViewPager.setAdapter(planPager);
        TabLayout tabLayout = (TabLayout) getView().findViewById(R.id.plan_tabs);
        new TabLayoutMediator(tabLayout, myViewPager, (tab, position) -> tab.setText(titles[position])).attach();

        myViewPager.setCurrentItem(1, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
