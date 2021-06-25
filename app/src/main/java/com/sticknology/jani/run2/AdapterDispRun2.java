package com.sticknology.jani.run2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data2.Distance;
import com.sticknology.jani.data2.MyTime;

import org.jetbrains.annotations.NotNull;

import java.util.Hashtable;

public class AdapterDispRun2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //String identifiers to get textview from hashtable for IntervalHolder
    private final String cardLabel = "cardLabel", intervalTime = "intervalTime",
            intervalDistance = "intervalDistance", intervalPace = "intervalPace",
            intervalEffort = "intervalEffort";

    public class IntervalHolder extends RecyclerView.ViewHolder {

        //public TextView cardLabel, intervalDistance, intervalTime, intervalEffort, intervalPace;
        public Hashtable<String, TextView> textViewHTable;

        public IntervalHolder(View v){
            super(v);

            //Create Hashtable to hold all textview objects for intervalholder
            textViewHTable = new Hashtable<String, TextView>();
            textViewHTable.put(cardLabel, v.findViewById(R.id.run2_interval_label));
            textViewHTable.put(intervalTime, v.findViewById(R.id.run2_interval_time));
            textViewHTable.put(intervalDistance, v.findViewById(R.id.run2_interval_distance));
            textViewHTable.put(intervalPace, v.findViewById(R.id.run2_interval_pace));
            textViewHTable.put(intervalEffort, v.findViewById(R.id.run2_interval_effort));
        }
        //Return hashtable for use later in adapter
        public Hashtable<String, TextView> getTextMap(){
            return textViewHTable;
        }
    }

    public class ActionHolder extends RecyclerView.ViewHolder {

        public Button addInterval, addRepeat;

        public ActionHolder(View v){
            super(v);
            addInterval = v.findViewById(R.id.run2_button_addinterval);
            addRepeat = v.findViewById(R.id.run2_button_addrepeats);
        }
        //Getters for button objects
        public Button getAddInterval() {return addInterval;}
        public Button getAddRepeat(){return addRepeat;}
    }


    @Override
    public int getItemViewType(int position) {

        if (position < FragDispRun2.dispRun.getIntervals().size()) return 0;
        else return 1;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(viewType == 0){
            View v = inflater.inflate(R.layout.run2_item_intervalcard, parent, false);
            viewHolder = new IntervalHolder(v);
        } else {
            View v = inflater.inflate(R.layout.run2_item_buttoncard, parent, false);
            viewHolder = new ActionHolder(v);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        //Runs proper obvh method based on what the item should be
        if(holder.getItemViewType() == 0){
            IntervalHolder intervalHolder = (IntervalHolder) holder;
            obvhIntervalHolder(intervalHolder, position);
        } else {
            ActionHolder actionHolder = (ActionHolder) holder;
            obvhActionHolder(actionHolder, position);
        }
    }

    //Interval Card OBVH
    private void obvhIntervalHolder(IntervalHolder holder, int position){

        //Getting Hashtable with all of the textviews inside
        Hashtable<String, TextView> textViewMap = holder.getTextMap();

        //Setting the label for the interval card
        TextView testLabel = textViewMap.get(cardLabel);
        testLabel.setText("Interval " + (position+1));

        //Get interval object for current position
        Interval2 cardInterval = FragDispRun2.dispRun.getIntervals().get(position);

        //Setting display data for the card
        textViewMap.get(intervalDistance).setText(String.valueOf(cardInterval.getDistance().getDoubleDistance()));
        textViewMap.get(intervalTime).setText(cardInterval.getTime().getDispString());
        textViewMap.get(intervalPace).setText(cardInterval.getPace().getDispString());
        textViewMap.get(intervalEffort).setText(cardInterval.getEffort());

    }

    //Action Card OBVH
    private void obvhActionHolder(ActionHolder holder, int position){

        //Set Behavior for Add Interval Button
        Button addInterval = holder.getAddInterval();
        addInterval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBlankInterval();
                FragDispRun2.dispAdapter.notifyItemInserted(FragDispRun2.dispRun.getIntervals().size());
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

    public void addBlankInterval(){
        MyTime blankTime = new MyTime(0, 0, 0);
        Distance zeroDistance = new Distance(0, Distance.defaultUnit);
        FragDispRun2.dispRun.getIntervals().add(new Interval2(zeroDistance, "NA", blankTime, blankTime));
    }

    @Override
    public int getItemCount() {
        if(FragDispRun2.dispRun.getIntervals() != null){
            return FragDispRun2.dispRun.getIntervals().size() +1;
        }
        else {
            return 2;
        }
    }
}
