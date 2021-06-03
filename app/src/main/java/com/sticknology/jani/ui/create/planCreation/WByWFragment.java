package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.ListCreation;
import com.sticknology.jani.data.TrainingDay;
import com.sticknology.jani.data.TrainingWeek;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WByWFragment extends Fragment {

    public ArrayList<TrainingDay> mTrainingDayList;

    public static WByWFragment newInstance(String text) {

        WByWFragment f = new WByWFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_wbyw, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Create RecyclerView for Displaying Days in Week
        RecyclerView revDay = (RecyclerView) getView().findViewById(R.id.pc_rev_dayholder);
        TrainingWeek newTrainingWeek = new ListCreation().createEmptyTrainingWeek();
        mTrainingDayList = newTrainingWeek.getTrainingWeekDays();
        WeekByWeekRevAdapter weekByWeekRevAdapter = new WeekByWeekRevAdapter(mTrainingDayList);
        revDay.setAdapter(weekByWeekRevAdapter);
        revDay.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}
