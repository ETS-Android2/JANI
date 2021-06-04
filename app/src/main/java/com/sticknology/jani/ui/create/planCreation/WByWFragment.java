package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.ListCreation;
import com.sticknology.jani.data.TrainingDay;
import com.sticknology.jani.data.TrainingWeek;
import com.sticknology.jani.data.Workout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WByWFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public ArrayList<TrainingDay> mTrainingDayList;
    public static int numWeeks = 1;

    private Spinner weekSpinner;
    private ArrayAdapter<String> dataAdapter;
    private List<String> weeks;
    private int newWeekIndex;
    private String mPlan;

    public static WByWFragment newInstance(String plan) {

        WByWFragment f = new WByWFragment();
        Bundle b = new Bundle();
        b.putString("plan", plan);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mPlan = this.getArguments().getString("plan");
        return inflater.inflate(R.layout.fragment_wbyw, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Create RecyclerView for Displaying Days in Week
        RecyclerView revDay = (RecyclerView) getView().findViewById(R.id.pc_rev_dayholder);
        TrainingWeek newTrainingWeek = new ListCreation().createEmptyTrainingWeek();
        mTrainingDayList = newTrainingWeek.getTrainingWeekDays();
        ArrayList<TrainingDay> trainingDayArrayList = newTrainingWeek.getTrainingWeekDays();
        WeekByWeekRevAdapter weekByWeekRevAdapter = new WeekByWeekRevAdapter(trainingDayArrayList, mPlan);
        revDay.setAdapter(weekByWeekRevAdapter);
        revDay.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //Add Drop Down Menu For Weeks
        weekSpinner = getView().findViewById(R.id.wbyw_spinner_week);
        weeks = new ArrayList<String>();
        weeks.add("Week 1");
        weeks.add("Add Week");
        //Is one because array starts at 0
        newWeekIndex = 1;
        dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, weeks);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekSpinner.setOnItemSelectedListener(this);
        weekSpinner.setAdapter(dataAdapter);

        //Add Listener To Delete Week
        Button deleteWeekButton = getView().findViewById(R.id.wbyw_button_delete);
        deleteWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currSelected = weekSpinner.getSelectedItemPosition();
                weeks.remove(currSelected);
                newWeekIndex--;

                for(int i = currSelected; i < newWeekIndex; i++){
                    weeks.set(i, "Week " + (i+1));
                }

                dataAdapter.notifyDataSetChanged();
                if(currSelected == newWeekIndex){
                    weekSpinner.setSelection(newWeekIndex - 1);
                }
                numWeeks--;
            }
        });
    }

    //Listener for selection of week spinner
    //Currently only spinner addition when add week selected implemented
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //i is the position of the spinner, not sure what l is
        if(i == newWeekIndex){
            int newPos = newWeekIndex + 1;
            weeks.add(newWeekIndex, "Week " + newPos);
            dataAdapter.notifyDataSetChanged();
            newWeekIndex++;
            numWeeks++;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
