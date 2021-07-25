package com.sticknology.jani.run2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data2.Distance;
import com.sticknology.jani.data2.Interval2;
import com.sticknology.jani.data2.MyTime;
import com.sticknology.jani.data2.Run2;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DispRun2Fragment extends Fragment {

    //Fragment variables
    public static Run2 dispRun;
    public static Activity activity;

    //Rev
    public static DispRun2Adapter dispAdapter;
    public static RecyclerView intervalRev;

    public static DispRun2Fragment newInstance(Boolean isEditable) {

        Bundle args = new Bundle();
        //This boolean is used to determine whether or not edit protocols should be in place for
        //this instance of the fragment or if it should be view only (still including edit button
        //to effectively switch modes)
        args.putBoolean("isEditable", isEditable);

        DispRun2Fragment fragment = new DispRun2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //TODO: Update this with actual reference to real variable, potentially create empty object methods
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

        //Inflate view with proper layout xml
        return inflater.inflate(R.layout.run2_fragment_base, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View v, Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);

        activity = getActivity();

        //Create recyclerview
        intervalRev = getView().findViewById(R.id.run2_disp_rev);
        dispAdapter = new DispRun2Adapter();
        intervalRev.setAdapter(dispAdapter);
        intervalRev.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
