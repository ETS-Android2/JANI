package com.sticknology.jani.run2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sticknology.jani.R;
import com.sticknology.jani.ui.create.planCreation.PlanCreationActivity;
import com.sticknology.jani.uiCommon.TwoNumberPicker;

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if(item.getItemId() == android.R.id.home){

            DispRun2Fragment newFrag = DispRun2Fragment.newInstance(true);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_create, newFrag, null).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
