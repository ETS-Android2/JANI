package com.sticknology.jani.ui.create.planCreation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.EmptyObjects;
import com.sticknology.jani.data.Interval;
import com.sticknology.jani.data.Run;
import com.sticknology.jani.dataProcessing.InterpretRun;
import com.sticknology.jani.dataProcessing.StandardReadWrite;

import java.util.ArrayList;

public class RunCreationFragment extends Fragment {

    ArrayList<Interval> mIntervalList;
    public static RunCreationRevAdapter mAdapter;
    public static Run mSavedRun;

    private int mDayPosition;
    private String mName;
    private String mType;
    private String mDescription;

    public static RunCreationFragment newInstance(int dayPosition){

        RunCreationFragment f = new RunCreationFragment();
        Bundle b = new Bundle();
        b.putInt("day", dayPosition);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        mDayPosition = getArguments().getInt("day");
        return inflater.inflate(R.layout.fragment_runcreation, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        //Add Spinner for selecting type of run
        Spinner typeSpinner = (Spinner) getView().findViewById(R.id.spinner_runtype);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.runtype_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);


        //Create RecyclerView for Adding New Intervals
        RecyclerView revRunCreationInterval = (RecyclerView) getView().findViewById(R.id.rc_rev_interval);
        mIntervalList = new EmptyObjects().createEmptyInterval();
        RunCreationRevAdapter runCreationRevAdapter = new RunCreationRevAdapter(mIntervalList);
        revRunCreationInterval.setAdapter(runCreationRevAdapter);
        revRunCreationInterval.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mAdapter = runCreationRevAdapter;

        //Setting up listener for expanding number of intervals in run
        Button newIntervalButton = getView().findViewById(R.id.rc_button_newinterval);
        newIntervalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int curSize = runCreationRevAdapter.getItemCount();
                ArrayList<Interval> newItems = new EmptyObjects().createEmptyInterval();
                mIntervalList.addAll(newItems);
                runCreationRevAdapter.notifyItemRangeInserted(curSize, newItems.size());
            }
        });

        //Setting up Cancel Button Listener
        //TODO: Add full functionality here
        Button cancelButton = getView().findViewById(R.id.rc_button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Setting Up Save Button Listener
        Button saveButton = getView().findViewById(R.id.rc_button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Set Run Description parameters
                EditText nameEditText = getView().findViewById(R.id.run_name_edit);
                mName = nameEditText.getText().toString();
                if(mName.equals("")){mName = " ";}
                EditText descriptionEditText = getView().findViewById(R.id.run_descript_edit);
                mDescription = descriptionEditText.getText().toString();
                if(mDescription.equals("")){mDescription = " ";}
                Spinner typeSpinner = getView().findViewById(R.id.spinner_runtype);
                mType = typeSpinner.getSelectedItem().toString();
                if(mType.equals("")){mType = " ";}

                //Create List of Intervals
                ArrayList<Interval> intervalList = new ArrayList<>();
                for(int i = 0; i < runCreationRevAdapter.getItemCount(); i++){
                    //Todo: Find out why it breaks if there are multiple intervals
                    View mChild = revRunCreationInterval.getChildAt(i);
                    EditText paceEditText = mChild.findViewById(R.id.rc_rev_pace);
                    EditText timeEditText = mChild.findViewById(R.id.rc_rev_time);
                    EditText distanceEditText = mChild.findViewById(R.id.rc_rev_distance);
                    Spinner effortSpinner = mChild.findViewById(R.id.spinner_rc_intervalrev);

                    String iPace = paceEditText.getText().toString();
                    if(iPace.equals("")){iPace = " ";}
                    String iTime = timeEditText.getText().toString();
                    if(iTime.equals("")){iTime = " ";}
                    String iDistance = distanceEditText.getText().toString();
                    if(iDistance.equals("")){iDistance = " ";}
                    String iEffort = effortSpinner.getSelectedItem().toString();
                    if(iEffort.equals("")){iEffort = " ";}

                    intervalList.add(new Interval(iDistance, iPace, iTime, iEffort));
                }

                //Build interval list and save run
                mSavedRun = new Run(intervalList, mName, mDescription, mType);

                //Add to template file if check
                Switch mSwitch = getView().findViewById(R.id.rc_switch_template);
                if(mSwitch.isChecked()) {
                    StandardReadWrite standardReadWrite = new StandardReadWrite();
                    InterpretRun interpretRun = new InterpretRun();
                    String workoutString = interpretRun.getStringRun(mSavedRun);
                    standardReadWrite.appendText(workoutString,"run_templates.txt", getContext(), Context.MODE_APPEND);
                }

                //Move back to view
                PlanCreationActivity.mTrainingPlan.getTrainingDay(WByWFragment.weekPosition, mDayPosition).addRun(mSavedRun);
                PlanCreationActivity.currentTabSet = PlanCreationActivity.TABSET.VIEW;
                PlanCreateInterFragment planCreateInterFragment = PlanCreateInterFragment.newInstance(0);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, planCreateInterFragment, null)
                        .commit();
            }
        });

    }
}
