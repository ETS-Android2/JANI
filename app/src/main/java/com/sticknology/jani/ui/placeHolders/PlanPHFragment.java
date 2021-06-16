package com.sticknology.jani.ui.placeHolders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.MainActivity;
import com.sticknology.jani.R;
import com.sticknology.jani.data.TrainingWeek;

import java.util.ArrayList;
import java.util.List;

public class PlanPHFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    public static TrainingWeek trainingWeek;
    public static int weekPosition;
    private PHPlanAdapter phPlanAdapter;
    private ArrayAdapter<String> spinnerAdapter;

    public PlanPHFragment newInstance(){

        PlanPHFragment f = new PlanPHFragment();
        Bundle b = new Bundle();

        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.ph_fragment_planview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        RecyclerView dayRev = getView().findViewById(R.id.ph_plan_rev);
        TextView title = getView().findViewById(R.id.ph_plan_title);
        Spinner weekSelector = getView().findViewById(R.id.ph_plan_spinner);
        TextView goal = getView().findViewById(R.id.ph_plan_goal);

        if(MainActivity.aTrainingPlan != null) {

            //Set Day Recycler View
            dayRev.setVisibility(View.VISIBLE);
            dayRev.setVisibility(View.VISIBLE);
            weekSelector.setVisibility(View.VISIBLE);
            goal.setVisibility(View.VISIBLE);

            trainingWeek = MainActivity.aTrainingPlan.getTrainingPlanWeeks().get(0);

            phPlanAdapter = new PHPlanAdapter();
            dayRev.setAdapter(phPlanAdapter);
            dayRev.setLayoutManager(new LinearLayoutManager(this.getContext()));

            title.setText(MainActivity.aTrainingPlan.getTrainingPlanName());
            goal.setText(MainActivity.aTrainingPlan.getTrainingPlanGoal());

            weekSelector = getView().findViewById(R.id.ph_plan_spinner);
            int weekSize = MainActivity.aTrainingPlan.getTrainingPlanWeeks().size();
            List<String> weeks = new ArrayList<String>();
            for(int i = 0; i < weekSize; i++){
                weeks.add("Week " + (i+1));
            }
            spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, weeks);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            weekSelector.setOnItemSelectedListener(this);
            weekSelector.setAdapter(spinnerAdapter);
            weekSelector.setSelection(weekPosition);

        } else{
            dayRev.setVisibility(View.GONE);
            weekSelector.setVisibility(View.GONE);
            goal.setVisibility(View.GONE);
            title.setText("No Active Plan");
        }
    }

    //Listener for selection of week spinner
    //Currently only spinner addition when add week selected implemented
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        trainingWeek = MainActivity.aTrainingPlan.getTrainingPlanWeeks().get(i);
        weekPosition = i;
        phPlanAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
