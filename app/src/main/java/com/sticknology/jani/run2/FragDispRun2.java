package com.sticknology.jani.run2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data2.Distance;
import com.sticknology.jani.data2.MyTime;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FragDispRun2 extends Fragment {

    //Fragment variables
    public static Run2 dispRun;

    //Adapter
    public static AdapterDispRun2 dispAdapter;

    //Needed ui component initialization
    private TextView dispName, dispType, dispNotes, labelDist, labelTime, labelPace;
    private CardView header;
    private RecyclerView intervalRev;

    public static FragDispRun2 newInstance(Boolean isEditable) {

        Bundle args = new Bundle();
        //This boolean is used to determine whether or not edit protocols should be in place for
        //this instance of the fragment or if it should be view only (still including edit button
        //to effectively switch modes)
        args.putBoolean("isEditable", isEditable);

        FragDispRun2 fragment = new FragDispRun2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //TODO: Update this with actual reference to real variable, potentially create empty object methods
        if(dispRun == null){
            dispRun = new Run2(null, null, null, null,
                    null, null, null);
            ArrayList<Interval2> intervalList = new ArrayList<>();
            dispRun.setIntervals(intervalList);
            MyTime blankTime = new MyTime(0, 0, 0);
            Distance zeroDistance = new Distance(0, Distance.defaultUnit);
            dispRun.getIntervals().add(new Interval2(zeroDistance, "EFFORT", blankTime, blankTime));
        }

        //Inflate view with proper layout xml
        return inflater.inflate(R.layout.run2_fragment_base, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View v, Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);

        //Find all ui components needed
        //Text Fields
        dispName = getView().findViewById(R.id.run2_disp_name);
        dispType = getView().findViewById(R.id.run2_disp_type);
        dispNotes = getView().findViewById(R.id.run2_disp_notes);
        labelDist = getView().findViewById(R.id.run2_disp_distance);
        labelTime = getView().findViewById(R.id.run2_disp_time);
        labelPace = getView().findViewById(R.id.run2_disp_apace);
        //Containers
        header = getView().findViewById(R.id.run2_disp_header);
        //RecyclerView
        intervalRev = getView().findViewById(R.id.run2_disp_rev);

        //Create display for header
        //Set name/title of run
        if(dispRun.getTitle() != null){
            dispName.setText(dispRun.getTitle());
        } else {
            dispName.setText("Tap to Set Title");
        }

        //Set type text or make invisible if type is also title
        String type = dispRun.getType();;
        if(type != null && !type.equals(dispRun.getTitle())){
            dispType.setVisibility(View.VISIBLE);
            dispType.setText(type);
        } else if (type == null){
            dispType.setVisibility(View.VISIBLE);
            dispType.setText("Tap to Set Type");
        } else {
            dispType.setVisibility(View.GONE);
        }

        //Set text for notes
        if(dispRun.getNotes() != null){
            dispNotes.setText(dispRun.getNotes());
        } else {
            dispNotes.setText("Tap to Add Notes");
        }

        updateRunData();

        //Create recyclerview
        dispAdapter = new AdapterDispRun2();
        intervalRev.setAdapter(dispAdapter);
        intervalRev.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    //Update the text inside of the header for run display
    public void updateRunData(){

        String totalDistance = "";
        String totalTime = "";
        String averagePace = "";

        //If run object has interval list filled
        if(dispRun.getIntervals() != null){
            totalDistance += dispRun.getDistance() + " " + "mi";
            totalTime += dispRun.getTotalTime().getDispString();
            averagePace += dispRun.getAveragePace().getDispString();

        }
        //If there is no filled run object
        else {
            totalDistance += 0;
            totalTime += "00:00";
            averagePace += "0:00";
        }

        labelDist.setText(totalDistance);
        labelTime.setText(totalTime);
        labelPace.setText(averagePace);
    }
}
