package com.sticknology.jani.run2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.sticknology.jani.data2.MyTime;
import com.sticknology.jani.data2.Run2;
import com.sticknology.jani.ui.create.planCreation.PlanCreationActivity;
import com.sticknology.jani.uiCommon.TwoNumberPicker;

import java.util.ArrayList;

public class EditRun2Fragment extends Fragment {

    private String mEditType;

    public static EditRun2Fragment newInstance(String editType) {

        Bundle args = new Bundle();
        args.putString("editType", editType);
        EditRun2Fragment fragment = new EditRun2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Initializes run object if the object itself is currently null
        if(DispRun2Fragment.dispRun == null){
            Log.d("RunCreation", "Creating new blank run object as acting object");
            DispRun2Fragment.dispRun = new Run2(null, null, null, null,
                    null, null, null);
            ArrayList<Interval2> intervalList = new ArrayList<>();
            DispRun2Fragment.dispRun.setIntervals(intervalList);
            MyTime blankTime = new MyTime(0, 0, 0);
            Distance zeroDistance = new Distance(0, Distance.defaultUnit);
            DispRun2Fragment.dispRun.getIntervals().add(new Interval2(zeroDistance, "NA", blankTime, blankTime));
        }

        //Returns correct view between editing the header or an interval
        mEditType = getArguments().getString("editType");
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

        if(mEditType.equals("INTERVAL")){
            //This is for interval information setter

            //Set up distance picker
            Button distanceButton = getView().findViewById(R.id.run2_edit_distance);
            TwoNumberPicker twoNumberPicker = new TwoNumberPicker();
            twoNumberPicker.distanceDialog(distanceButton, getView(), getActivity(), getContext(), "Distance");
        }
        else {
            //This is for run information setter
            EditText runTitle = getView().findViewById(R.id.run2_edit_title);
            if(DispRun2Fragment.dispRun.getTitle() != null) {
                runTitle.setText(DispRun2Fragment.dispRun.getTitle());
            }
            EditText runNotes = getView().findViewById(R.id.run2_edit_notes);
            if(DispRun2Fragment.dispRun.getNotes() != null) {
                runNotes.setText(DispRun2Fragment.dispRun.getNotes());
            }

            //Set Run Type Spinner
            Spinner typeSpinner = getView().findViewById(R.id.run2_edit_type);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.runtype_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeSpinner.setAdapter(adapter);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.save_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if(item.getItemId() == android.R.id.home){

            DispRun2Fragment newFrag = DispRun2Fragment.newInstance(true);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_create, newFrag, null).commit();
            return true;

        } else if(item.getItemId() == R.id.action_save){

            //For saving run header information
            if(mEditType.equals("HEADER")) {
                SwitchCompat useTypeAsTitle = getView().findViewById(R.id.run2_edit_type_title);
                EditText runTitle = getView().findViewById(R.id.run2_edit_title);
                Spinner typeSpinner = getView().findViewById(R.id.run2_edit_type);
                String runType = typeSpinner.getSelectedItem().toString();
                DispRun2Fragment.dispRun.setTypes(runType);
                if (useTypeAsTitle.isChecked()) {
                    DispRun2Fragment.dispRun.setTitle(runType);
                } else {
                    DispRun2Fragment.dispRun.setTitle(runTitle.getText().toString());
                }
                EditText runNotes = getView().findViewById(R.id.run2_edit_notes);
                DispRun2Fragment.dispRun.setNotes(runNotes.getText().toString());

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
