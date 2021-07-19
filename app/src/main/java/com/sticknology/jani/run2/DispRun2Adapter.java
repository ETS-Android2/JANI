package com.sticknology.jani.run2;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data2.Distance;
import com.sticknology.jani.data2.Interval2;
import com.sticknology.jani.data2.MyTime;
import com.sticknology.jani.ui.create.planCreation.PlanCreationActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Hashtable;

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

        //Getting Hashtable with all of the textviews inside
        Hashtable<String, TextView> textViewMap = holder.getTextMap();

        //Setting the label for the interval card
        TextView testLabel = textViewMap.get(cardLabel);
        testLabel.setText("Interval " + (position));

        //Get interval object for current position
        Interval2 cardInterval = dispRun.getIntervals().get(position-1);

        //Setting display data for the card
        textViewMap.get(intervalDistance).setText(String.valueOf(cardInterval.getDistance().getDoubleDistance()));
        textViewMap.get(intervalTime).setText(cardInterval.getTime().getDispString());
        textViewMap.get(intervalPace).setText(cardInterval.getPace().getDispString());
        textViewMap.get(intervalEffort).setText(cardInterval.getEffort());

        //Setting on click listener for opening edit fragment
        holder.intervalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditRun2Fragment newFrag = new EditRun2Fragment().newInstance("INTERVAL", position-1);
                PlanCreationActivity planCreationActivity = (PlanCreationActivity) holder.context;
                planCreationActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, newFrag, null)
                        .addToBackStack("").commit();
            }
        });

        //Setting up/down behavior for intervals
        holder.moveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position > 1){
                    //-1 and -2 bc header holds position 0
                    exchangeIntervals(position-1, position-2);
                    DispRun2Fragment.dispAdapter.notifyItemRangeChanged(position, 2);
                }
            }
        });
        holder.moveDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        dispRun.getIntervals().add(new Interval2(zeroDistance, "NA", blankTime, blankTime));
    }

    //Swap two intervals in the list
    public void exchangeIntervals(int position1, int position2){
        Interval2 firstInterval = dispRun.getIntervals().get(position1);
        Interval2 secondInterval = (dispRun.getIntervals().get(position2));
        dispRun.getIntervals().set(position1, secondInterval);
        dispRun.getIntervals().set(position2, firstInterval);
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
