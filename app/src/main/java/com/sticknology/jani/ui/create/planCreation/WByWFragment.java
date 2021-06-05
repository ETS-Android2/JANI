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
import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.data.TrainingWeek;
import com.sticknology.jani.dataProcessing.InterpretTrainingPlan;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WByWFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public static int numWeeks = 1;
    public static int weekPosition = 0;

    public static String mPlan;
    public static TrainingWeek mTrainingWeek;

    private Spinner weekSpinner;
    private ArrayAdapter<String> spinnerAdapter;
    private WeekByWeekRevAdapter mWByWRevAdapter;
    private List<String> weeks;
    private int newWeekIndex;

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
        mTrainingWeek = new InterpretTrainingPlan().getTrainingPlanFromString(mPlan).getTrainingPlanWeeks().get(0);
        return inflater.inflate(R.layout.fragment_wbyw, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Create RecyclerView for Displaying Days in Week
        RecyclerView revDay = (RecyclerView) getView().findViewById(R.id.pc_rev_dayholder);
        mWByWRevAdapter = new WeekByWeekRevAdapter();
        revDay.setAdapter(mWByWRevAdapter);
        revDay.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //Add Drop Down Menu For Weeks
        weekSpinner = getView().findViewById(R.id.wbyw_spinner_week);
        weeks = new ArrayList<String>();
        weeks.add("Week 1");
        weeks.add("Add Week");
        //Is one because array starts at 0
        newWeekIndex = 1;
        spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, weeks);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekSpinner.setOnItemSelectedListener(this);
        weekSpinner.setAdapter(spinnerAdapter);

        //Add Listener To Delete Week
        Button deleteWeekButton = getView().findViewById(R.id.wbyw_button_delete);
        deleteWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int currSelected = weekSpinner.getSelectedItemPosition();
                weeks.remove(currSelected);
                newWeekIndex--;

                TrainingPlan trainingPlan = new InterpretTrainingPlan().getTrainingPlanFromString(mPlan);
                trainingPlan.getTrainingPlanWeeks().remove(currSelected);
                mPlan = new InterpretTrainingPlan().getStringFromTrainingPlan(trainingPlan);

                for(int i = currSelected; i < newWeekIndex; i++){
                    weeks.set(i, "Week " + (i+1));
                }

                spinnerAdapter.notifyDataSetChanged();
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
            spinnerAdapter.notifyDataSetChanged();
            newWeekIndex++;
            numWeeks++;

            TrainingPlan trainingPlan = new InterpretTrainingPlan().getTrainingPlanFromString(mPlan);
            trainingPlan.getTrainingPlanWeeks().add(new ListCreation().createEmptyTrainingWeek());
            mTrainingWeek = trainingPlan.getTrainingPlanWeeks().get(i);
            mPlan = new InterpretTrainingPlan().getStringFromTrainingPlan(trainingPlan);
        } else {
            TrainingPlan trainingPlan = new InterpretTrainingPlan().getTrainingPlanFromString(mPlan);
            mTrainingWeek = trainingPlan.getTrainingPlanWeeks().get(i);
            weekPosition = i;
        }
        try {
            mWByWRevAdapter.notifyDataSetChanged();
            WeekByWeekRevAdapter.mRunAdapter.notifyDataSetChanged();
        } catch (NullPointerException nullPointerException){
            System.out.println("DID NOT UPDATE ADAPTERS");
        }


        System.out.println("TRIED TO CHANGE VIEWING WEEK");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
