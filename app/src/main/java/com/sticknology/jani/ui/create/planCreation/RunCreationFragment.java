package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.dataProcessing.ReadWriteRun;

import java.util.ArrayList;

public class RunCreationFragment extends Fragment {

    ArrayList<TrainingPlan> mTrainingPlans;
    public static RunCreationRevAdapter mAdapter;

    private String[] mDistance;
    private String[] mPace;
    private String[] mTime;
    private int[] mEfforts;
    private String mName;
    private String mDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

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
        mTrainingPlans = TrainingPlan.createContactsList(1);
        RunCreationRevAdapter runCreationRevAdapter = new RunCreationRevAdapter(mTrainingPlans);
        revRunCreationInterval.setAdapter(runCreationRevAdapter);
        revRunCreationInterval.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mAdapter = runCreationRevAdapter;

        //Setting up listener for expanding number of intervals in run
        Button newIntervalButton = getView().findViewById(R.id.rc_button_newinterval);
        newIntervalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int curSize = runCreationRevAdapter.getItemCount();
                ArrayList<TrainingPlan> newItems = TrainingPlan.createContactsList(1);
                mTrainingPlans.addAll(newItems);
                runCreationRevAdapter.notifyItemRangeInserted(curSize, newItems.size());
            }
        });

        //Setting Up Save Button Listener
        Button saveButton = getView().findViewById(R.id.rc_button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int currLength = runCreationRevAdapter.getItemCount();

                System.out.println(currLength);
                EditText nameEditText = getView().findViewById(R.id.run_name_edit);
                mName = nameEditText.getText().toString();
                EditText descriptionEditText = getView().findViewById(R.id.run_descript_edit);
                mDescription = descriptionEditText.getText().toString();
                mDistance = new String[currLength];
                mPace = new String[currLength];
                mTime = new String[currLength];
                mEfforts = new int[currLength];


                for(int i = 0; i < currLength; i++){
                    //Todo: Find out why it breaks if there are multiple intervals
                    View mChild = revRunCreationInterval.getChildAt(i);
                    EditText paceEditText = mChild.findViewById(R.id.rc_rev_pace);
                    mPace[i] = paceEditText.getText().toString();
                    EditText timeEditText = mChild.findViewById(R.id.rc_rev_time);
                    mTime[i] = timeEditText.getText().toString();
                    EditText distanceEditText = mChild.findViewById(R.id.rc_rev_distance);
                    mDistance[i] = distanceEditText.getText().toString();
                    Spinner effortSpinner = mChild.findViewById(R.id.spinner_rc_intervalrev);
                    mEfforts[i] = effortSpinner.getSelectedItemPosition();
                }

                ReadWriteRun readWriteRun = new ReadWriteRun();
                readWriteRun.writeRunToText(mName, mDescription, mDistance, mTime, mEfforts, mPace, getContext());
            }
        });

    }
}
