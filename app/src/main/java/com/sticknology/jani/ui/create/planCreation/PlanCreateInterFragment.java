package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sticknology.jani.R;

import static com.sticknology.jani.ui.create.planCreation.PlanCreationActivity.currentTabSet;

public class PlanCreateInterFragment extends Fragment {

    private final String[] titlesV = {"Overview", "Week by Week"};
    private final String[] titlesT = {"Run", "Workout"};

    private static ViewPager2 viewPager2;
    private static TabLayout tabLayout;

    public static PlanCreateInterFragment newInstance(String plan) {

        PlanCreateInterFragment f = new PlanCreateInterFragment();
        Bundle b = new Bundle();
        b.putString("plan", plan);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_plancreation_inter, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2 = getView().findViewById(R.id.pager_plancreation);
        tabLayout = getView().findViewById(R.id.tab_plancreation);

        setTabs(viewPager2, tabLayout);
    }

    protected void setTabs(ViewPager2 viewPager2, TabLayout tabLayout){

        PlanCreationPager planCreationPager = new PlanCreationPager(this, "test");
        viewPager2.setAdapter(planCreationPager);
        if (currentTabSet == PlanCreationActivity.TABSET.VIEW){
            new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(titlesV[position])).attach();
        } else if (currentTabSet == PlanCreationActivity.TABSET.TEMPLATES){
            new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(titlesT[position])).attach();
        }
    }
}
