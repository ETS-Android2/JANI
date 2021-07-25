package com.sticknology.jani.run2;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data2.Distance;
import com.sticknology.jani.data2.Interval2;
import com.sticknology.jani.data2.MyOperations;
import com.sticknology.jani.data2.MyTime;
import com.sticknology.jani.ui.create.planCreation.PlanCreationActivity;
import com.sticknology.jani.uiCommon.ThreeNumberPicker;
import com.sticknology.jani.uiCommon.TwoNumberPicker;
import com.sticknology.jani.uiMethodsCommon.SpinnerMethods;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static com.sticknology.jani.run2.DispRun2Fragment.dispRun;

public class DispRun2Adapter extends DispRun2ViewHolders {

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        //Runs proper obvh method based on what the item should be
        if(holder.getItemViewType() == 0){
            HeaderHolder headerHolder = (HeaderHolder) holder;
            obvhHeader(headerHolder, position);
        }
        else if(holder.getItemViewType() == 1){
            IntervalHolder intervalHolder = (IntervalHolder) holder;
            obvhInterval(intervalHolder, position);
        } else {
            ActionHolder actionHolder = (ActionHolder) holder;
            obvhAction(actionHolder, position);
        }
    }

    //Header Card OBVH
    private void obvhHeader(HeaderHolder holder, int position){

        //Create display for header
        //Set name/title of run and whether or not to display type
        if(dispRun.getTitle() != null && dispRun.getType() != null){
            if(dispRun.getTitle().equals(dispRun.getType())){
                holder.dispName.setText(dispRun.getType());
                holder.dispType.setVisibility(View.GONE);
            }
            //If title and type are different
            else {
                holder.dispName.setText(dispRun.getTitle());
                holder.dispType.setVisibility(View.VISIBLE);
                holder.dispType.setText(dispRun.getType());
            }
        }
        //This else should not occur, but still present
        else {
            holder.dispName.setText("Tap to Edit Run Details");
            holder.dispType.setVisibility(View.GONE);
        }

        //Set text for notes if they exist
        if(dispRun.getNotes() != null && !dispRun.getNotes().equals("")){
            holder.dispNotes.setText(dispRun.getNotes());
        } else {
            holder.dispNotes.setVisibility(View.GONE);
        }

        //Update run data for totals bar
        updateRunData(holder);

        //Set Listener for header
        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditRun2Fragment newFrag = new EditRun2Fragment().newInstance("HEADER", 0);
                PlanCreationActivity planCreationActivity = (PlanCreationActivity) holder.context;
                planCreationActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, newFrag, null)
                        .addToBackStack("").commit();
            }
        });
    }

    //Interval Card OBVH
    private void obvhInterval(IntervalHolder holder, int position){

        setDispInterval(holder, position-1);
    }

    private void setDispInterval(IntervalHolder holder, int position){

        holder.intervalEditCard.setVisibility(View.GONE);
        holder.intervalDispCard.setVisibility(View.VISIBLE);

        //Getting Hashtable with all of the textviews inside
        Hashtable<String, TextView> textViewMap = holder.getTextMap();

        //Setting the label for the interval card
        TextView testLabel = textViewMap.get(cardLabel);
        testLabel.setText("Interval " + (position+1));

        //Get interval object for current position
        Interval2 cardInterval = dispRun.getIntervals().get(position);

        //Setting display data for the card
        textViewMap.get(intervalDistance).setText(String.valueOf(cardInterval.getDistance().getDoubleDistance()));
        textViewMap.get(intervalPace).setText(cardInterval.getPace().getDispString());
        textViewMap.get(intervalEffort).setText(cardInterval.getEffort());

        //Setting on click listener for opening edit fragment
        holder.intervalDispCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEditInterval(holder, position);
            }
        });
    }

    private void setEditInterval(IntervalHolder holder, int position){

        holder.intervalDispCard.setVisibility(View.GONE);
        holder.intervalEditCard.setVisibility(View.VISIBLE);

        //Set up distance picker
        Button distanceButton = holder.distanceButton;
        distanceButton.setText(dispRun.getIntervals().get(position).getDistance().getStringDistance());
        TwoNumberPicker distancePicker = new TwoNumberPicker();
        distancePicker.twoPickerDialog(distanceButton, holder.view, DispRun2Fragment.activity, holder.context, "Distance");

        //Set up 2nd field spinner/button combo
        Button fieldButton = holder.field2Button;
        List<String> fieldList = new ArrayList<String>();
        fieldList.add("Pace");
        fieldList.add("Time");
        Spinner fieldSpinner = holder.fieldSpinner;
        ArrayAdapter<String> fieldAdapter = new ArrayAdapter<String>(DispRun2Fragment.activity,
                android.R.layout.simple_spinner_item, fieldList);
        fieldAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fieldSpinner.setAdapter(fieldAdapter);
        fieldSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(fieldSpinner.getSelectedItem().toString().equals("Pace")){
                    //Set up pace picker
                    TwoNumberPicker twoNumberPicker = new TwoNumberPicker();
                    String paceDisplay = dispRun.getIntervals().get(position).getPace().getDispString() + " /mi";
                    fieldButton.setText(paceDisplay);
                    twoNumberPicker.twoPickerDialog(fieldButton, holder.view, DispRun2Fragment.activity, holder.context, "Pace");

                } else {
                    //Set up time picker
                    ThreeNumberPicker threeNumberPicker = new ThreeNumberPicker();
                    String timeDisplay = dispRun.getIntervals().get(position).getTime().getDispString();
                    fieldButton.setText(timeDisplay);
                    threeNumberPicker.threePickerDialog(fieldButton, holder.view, DispRun2Fragment.activity, holder.context, "Time");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        fieldSpinner.setSelection(0);

        //Set up effort spinner
        Spinner effortSpinner = holder.effortSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(holder.context,
                R.array.intervaltype_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        effortSpinner.setAdapter(adapter);
        //Gets current item if it is an edit and not new
        if(position < dispRun.getIntervals().size()){
            String key = dispRun.getIntervals().get(position).getEffort();
            int currIndex = new SpinnerMethods().getSpinnerPos(key,
                    holder.context.getResources().getStringArray(R.array.intervaltype_array));
            effortSpinner.setSelection(currIndex);
        }

        //Set Listener for Done Button
        Button doneButton = holder.doneButton;
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Save data to object
                String stringDistance = distanceButton.getText().toString().split(" ")[0];
                Double doubleDistance = Double.valueOf(stringDistance);
                Distance intervalDistance = new Distance(doubleDistance, Distance.defaultUnit);

                MyOperations myOperations = new MyOperations();
                MyTime paceTime = myOperations.getTimeObjectString(fieldButton.getText().toString());

                Interval2 newInterval = new Interval2(intervalDistance,
                        effortSpinner.getSelectedItem().toString(), paceTime, new MyTime(0, 0, 0), "");

                //Checks if should replace the interval or append it to the end of the list
                if(position < dispRun.getIntervals().size()){
                    dispRun.getIntervals().set(position, newInterval);
                } else {
                    dispRun.getIntervals().add(newInterval);
                }

                setDispInterval(holder, position);
            }
        });
    }

    //Action Card OBVH
    private void obvhAction(ActionHolder holder, int position){

        //Set Behavior for Add Interval Button
        Button addInterval = holder.getAddInterval();
        addInterval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBlankInterval();
                DispRun2Fragment.dispAdapter.notifyItemInserted(dispRun.getIntervals().size());
            }
        });

        //Set Behavior for Add Repeats Button
        Button addRepeats = holder.getAddRepeat();
        addRepeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //Add blank interval to dispRun
    //TODO: MOVE THIS METHOD TO RUN2 Class???
    public void addBlankInterval(){
        MyTime blankTime = new MyTime(0, 0, 0);
        Distance zeroDistance = new Distance(0, Distance.defaultUnit);
        dispRun.getIntervals().add(new Interval2(zeroDistance, "NA", blankTime, blankTime, ""));
    }

    //Update the text inside of the header for total run display
    public void updateRunData(HeaderHolder holder){

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

        holder.labelDist.setText(totalDistance);
        holder.labelTime.setText(totalTime);
        holder.labelPace.setText(averagePace);
    }

    @Override
    public int getItemCount() {
        if(dispRun.getIntervals() != null){
            return dispRun.getIntervals().size() +2;
        }
        else {
            return 3;
        }
    }
}
