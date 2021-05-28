package com.sticknology.jani.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sticknology.jani.R;
import com.sticknology.jani.databinding.FragmentCalendarBinding;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;
    private FragmentCalendarBinding binding;
    private String[] titles = new String[]{"Day", "Week", "Month"};

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ViewPager2 viewPager2 = getView().findViewById(R.id.calendar_pager);
        TabLayout tabLayout = getView().findViewById(R.id.calendar_tab);
        CalendarPager calendarPager = new CalendarPager(this);

        viewPager2.setAdapter(calendarPager);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(titles[position])).attach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}