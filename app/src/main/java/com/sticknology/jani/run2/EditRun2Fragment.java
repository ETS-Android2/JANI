package com.sticknology.jani.run2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.sticknology.jani.R;
import com.sticknology.jani.data2.Distance;
import com.sticknology.jani.data2.Interval2;
import com.sticknology.jani.data2.MyOperations;
import com.sticknology.jani.data2.MyTime;
import com.sticknology.jani.data2.Run2;
import com.sticknology.jani.ui.create.planCreation.PlanCreationActivity;
import com.sticknology.jani.uiCommon.ThreeNumberPicker;
import com.sticknology.jani.uiCommon.TwoNumberPicker;
import com.sticknology.jani.uiMethodsCommon.SpinnerMethods;

import java.util.ArrayList;
import java.util.List;

import static com.sticknology.jani.run2.DispRun2Fragment.dispRun;

public class EditRun2Fragment extends Fragment {

    private String mEditType;

    private int mIntervalIndex;

    public static EditRun2Fragment newInstance(String editType, int intervalIndex) {

        Bundle args = new Bundle();
        args.putString("editType", editType);
        args.putInt("intervalIndex", intervalIndex);
        EditRun2Fragment fragment = new EditRun2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Initializes run object if the object itself is currently null
        if(dispRun == null){
            Log.d("RunCreation", "Creating new blank run object as acting object");
            dispRun = new Run2(null, null, null, null,
                    null, null, null);
            ArrayList<Interval2> intervalList = new ArrayList<>();
            dispRun.setIntervals(intervalList);
            MyTime blankTime = new MyTime(0, 0, 0);
            Distance zeroDistance = new Distance(0, Distance.defaultUnit);
            dispRun.getIntervals().add(new Interval2(zeroDistance, "NA", blankTime, blankTime, ""));
        }

        //Returns correct view between editing the header or an interval
        mEditType = getArguments().getString("editType");
        mIntervalIndex = getArguments().getInt("intervalIndex");
        if(mEditType.equals("HEADER")){
            return inflater.inflate(R.layout.run2_edit_header, container, false);
        }
        else { //Value passed is "INTERVAL"
            return inflater.inflate(R.layout.run2_edit_interval, container, false);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set home display and that options menu should be called on click
        PlanCreationActivity.createBar.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        if(mEditType.equals("INTERVAL")) ovcInterval(view, savedInstanceState);
        else if(mEditType.equals("HEADER")) ovcHeader(view, savedInstanceState);
    }

    //This is for run information setter
    public void ovcHeader(View view, Bundle savedInstanceState){

        EditText runTitle = getView().findViewById(R.id.run2_edit_title);
        if(dispRun.getTitle() != null) {
            runTitle.setText(dispRun.getTitle());
        }

        EditText runNotes = getView().findViewById(R.id.run2_edit_notes);
        if(dispRun.getNotes() != null) {
            runNotes.setText(dispRun.getNotes());
        }

        //Set Run Type Spinner
        Spinner typeSpinner = getView().findViewById(R.id.run2_edit_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.runtype_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        int typeIndex = new SpinnerMethods().getSpinnerPos(dispRun.getType(),
                getResources().getStringArray(R.array.runtype_array));
        typeSpinner.setSelection(typeIndex);

        //Check if title equals should be set
        Run2 localCurrentRun = dispRun;
        if(localCurrentRun.getTitle() != null && localCurrentRun.getType() != null
                && localCurrentRun.getTitle().equals(localCurrentRun.getType())){
            SwitchCompat titleSwitch = getView().findViewById(R.id.run2_edit_type_title);
            titleSwitch.setChecked(true);
        }
    }

    //This is for interval view setting
    public void ovcInterval(View view, Bundle savedInstanceState){

        //Set up distance picker
        Button distanceButton = getView().findViewById(R.id.run2_edit_button1);
        distanceButton.setText(dispRun.getIntervals().get(mIntervalIndex).getDistance().getStringDistance());
        TwoNumberPicker distancePicker = new TwoNumberPicker();
        distancePicker.twoPickerDialog(distanceButton, getView(), getActivity(), getContext(), "Distance");

        //Set up 2nd field spinner
        List<String> fieldList = new ArrayList<String>();
        fieldList.add("Pace");
        fieldList.add("Time");
        Spinner fieldSpinner = getView().findViewById(R.id.run2_edit_label1);
        ArrayAdapter<String> fieldAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, fieldList);
        fieldAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fieldSpinner.setAdapter(fieldAdapter);
        fieldSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Button fieldButton = getView().findViewById(R.id.run2_edit_button2);

                if(fieldSpinner.getSelectedItem().toString().equals("Pace")){
                    //Set up pace picker
                    TwoNumberPicker twoNumberPicker = new TwoNumberPicker();
                    String paceDisplay = dispRun.getIntervals().get(mIntervalIndex).getPace().getDispString() + " /mi";
                    fieldButton.setText(paceDisplay);
                    twoNumberPicker.twoPickerDialog(fieldButton, getView(), getActivity(), getContext(), "Pace");

                } else {
                    //Set up time picker
                    ThreeNumberPicker threeNumberPicker = new ThreeNumberPicker();
                    String timeDisplay = dispRun.getIntervals().get(mIntervalIndex).getTime().getDispString();
                    fieldButton.setText(timeDisplay);
                    threeNumberPicker.threePickerDialog(fieldButton, getView(), getActivity(), getContext(), "Time");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        fieldSpinner.setSelection(0);

        //Set up effort spinner
        Spinner effortSpinner = getView().findViewById(R.id.run2_edit_effort);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.intervaltype_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        effortSpinner.setAdapter(adapter);
        //Gets current item if it is an edit and not new
        if(mIntervalIndex < dispRun.getIntervals().size()){
            String key = dispRun.getIntervals().get(mIntervalIndex).getEffort();
            int currIndex = new SpinnerMethods().getSpinnerPos(key,
                    getResources().getStringArray(R.array.intervaltype_array));
            effortSpinner.setSelection(currIndex);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        //Changes button to next if it is first iteration of run header information
        if(dispRun.getTitle() == null) {
            inflater.inflate(R.menu.next_menu, menu);
        }
        //Default case of save text
        else {
            inflater.inflate(R.menu.save_menu, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if(item.getItemId() == android.R.id.home){

            DispRun2Fragment newFrag = DispRun2Fragment.newInstance(true);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_create, newFrag, null).commit();
            return true;

        } else if(item.getItemId() == R.id.action_save){

            System.err.println("THIS IS MEDITTYPE: " + mEditType);

            //For saving run header information
            if(mEditType.equals("HEADER")) {
                //Set base objects
                SwitchCompat useTypeAsTitle = getView().findViewById(R.id.run2_edit_type_title);
                EditText runTitle = getView().findViewById(R.id.run2_edit_title);
                Spinner typeSpinner = getView().findViewById(R.id.run2_edit_type);

                String runType = typeSpinner.getSelectedItem().toString();
                dispRun.setTypes(runType);
                if (useTypeAsTitle.isChecked()) {
                    dispRun.setTitle(runType);
                } else {
                    dispRun.setTitle(runTitle.getText().toString());
                }
                EditText runNotes = getView().findViewById(R.id.run2_edit_notes);
                dispRun.setNotes(runNotes.getText().toString());

                //Update Display (Frag Transaction)
                DispRun2Fragment newFrag = DispRun2Fragment.newInstance(true);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, newFrag, null).commit();
                return true;

            } else if(mEditType.equals("INTERVAL")){
                //Set base objects
                Button distanceButton = getView().findViewById(R.id.run2_edit_button1);
                Button paceButton = getView().findViewById(R.id.run2_edit_button2);
                Spinner effortSpinner = getView().findViewById(R.id.run2_edit_effort);

                //Save data to object
                String stringDistance = distanceButton.getText().toString().split(" ")[0];
                Double doubleDistance = Double.valueOf(stringDistance);
                Distance intervalDistance = new Distance(doubleDistance, Distance.defaultUnit);

                MyOperations myOperations = new MyOperations();
                MyTime paceTime = myOperations.getTimeObjectString(paceButton.getText().toString());
                //MyTime durationTime = myOperations.getTimeObjectString(timeButton.getText().toString());

                Interval2 newInterval = new Interval2(intervalDistance,
                        effortSpinner.getSelectedItem().toString(), paceTime, new MyTime(0, 0, 0), "");

                //Checks if should replace the interval or append it to the end of the list
                if(mIntervalIndex < dispRun.getIntervals().size()){
                    dispRun.getIntervals().set(mIntervalIndex, newInterval);
                } else {
                    dispRun.getIntervals().add(newInterval);
                }

                //Update Display (Frag Transaction)
                DispRun2Fragment newFrag = DispRun2Fragment.newInstance(true);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, newFrag, null).commit();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
