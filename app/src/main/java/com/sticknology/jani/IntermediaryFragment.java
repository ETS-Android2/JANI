package com.sticknology.jani;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sticknology.jani.ui.calendar.CalendarPager;
import com.sticknology.jani.ui.create.CreatePager;
import com.sticknology.jani.ui.plan.PlanPager;
import com.sticknology.jani.ui.profile.ProfilePager;

public class IntermediaryFragment extends Fragment {

    private String[] titlesCalendar = new String[]{"Day", "Week", "Month"};
    private String[] titlesPlan = new String[]{"Past", "Overview", "Future"};
    private String[] titlesCreate = new String[]{"Manage", "Export"};
    private String[] titlesProfile = new String[]{"Stats", "Preferences", "About"};

    private CalendarPager calendarPager;
    private PlanPager planPager;
    private CreatePager createPager;
    private ProfilePager profilePager;

    private String test;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_intermediary, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        
        Bundle test3 = getArguments();
        test = test3.getString("Category");
        System.err.println(test);

        //String test = PlanFragmentArgs.fromBundle(getArguments().getString());

        ViewPager2 viewPager2 = getView().findViewById(R.id.inter_pager2);
        TabLayout tabLayout = getView().findViewById(R.id.inter_tab);

        if(test.equals("CALENDAR")){
            calendarPager = new CalendarPager(this);
            viewPager2.setAdapter(calendarPager);
            new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                    tab.setText(titlesCalendar[position])).attach();
            viewPager2.setUserInputEnabled(false);
        } else if(test.equals("PLAN")) {
            planPager = new PlanPager(this);
            viewPager2.setAdapter(planPager);
            new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                    tab.setText(titlesPlan[position])).attach();
            viewPager2.setCurrentItem(1, false);
        } else if(test.equals("CREATE")) {
            createPager = new CreatePager(this);
            viewPager2.setAdapter(createPager);
            new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                    tab.setText(titlesCreate[position])).attach();
        } else {
            profilePager = new ProfilePager(this);
            viewPager2.setAdapter(profilePager);
            new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                    tab.setText(titlesProfile[position])).attach();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
