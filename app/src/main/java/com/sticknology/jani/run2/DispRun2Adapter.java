package com.sticknology.jani.run2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data2.Distance;
import com.sticknology.jani.data2.MyTime;
import com.sticknology.jani.ui.create.planCreation.PlanCreationActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Hashtable;

import static com.sticknology.jani.run2.DispRun2Fragment.dispRun;

public class DispRun2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    public class HeaderHolder extends RecyclerView.ViewHolder {

        //Needed ui component initialization
        private TextView dispName, dispType, dispNotes, labelDist, labelTime, labelPace;
        private CardView header;
        private Context context;

        public HeaderHolder(View v){
            super(v);
            //Find all ui components needed
            //Text Fields
            dispName = v.findViewById(R.id.run2_disp_name);
            dispType = v.findViewById(R.id.run2_disp_type);
            dispNotes = v.findViewById(R.id.run2_disp_notes);
            labelDist = v.findViewById(R.id.run2_disp_distance);
            labelTime = v.findViewById(R.id.run2_disp_time);
            labelPace = v.findViewById(R.id.run2_disp_apace);
            //Containers
            header = v.findViewById(R.id.run2_disp_header);
            //Activity
            context = v.getContext();
        }
    }


    @Override
    public int getItemViewType(int position) {

        if (position == 0) return 0;
        else if (position <= dispRun.getIntervals().size()) return 1;
        else return 2;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == 0){
            View v = inflater.inflate(R.layout.run2_item_header, parent, false);
            viewHolder = new HeaderHolder(v);
        }
        else if(viewType == 1){
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
            HeaderHolder headerHolder = (HeaderHolder) holder;
            obvhHeaderHolder(headerHolder, position);
        }
        else if(holder.getItemViewType() == 1){
            IntervalHolder intervalHolder = (IntervalHolder) holder;
            obvhIntervalHolder(intervalHolder, position);
        } else {
            ActionHolder actionHolder = (ActionHolder) holder;
            obvhActionHolder(actionHolder, position);
        }
    }

    //Header Card OBVH
    private void obvhHeaderHolder(HeaderHolder holder, int position){

        //Create display for header
        //Set name/title of run
        if(dispRun.getTitle() != null){
            holder.dispName.setText(dispRun.getTitle());
        } else {
            holder.dispName.setText("Tap to Set Title");
        }

        //Set type text or make invisible if type is also title
        String type = dispRun.getType();;
        if(type != null && !type.equals(dispRun.getTitle())){
            holder.dispType.setVisibility(View.VISIBLE);
            holder.dispType.setText(type);
        } else if (type == null){
            holder.dispType.setVisibility(View.VISIBLE);
            holder.dispType.setText("Tap to Set Type");
        } else {
            holder.dispType.setVisibility(View.GONE);
        }

        //Set text for notes
        if(dispRun.getNotes() != null){
            holder.dispNotes.setText(dispRun.getNotes());
        } else {
            holder.dispNotes.setText("Tap to Add Notes");
        }

        //Update run data for totals bar
        updateRunData(holder);

        //Set Listener for header
        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditRun2Fragment newFrag = new EditRun2Fragment();
                PlanCreationActivity planCreationActivity = (PlanCreationActivity) holder.context;
                planCreationActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, newFrag, null)
                        .addToBackStack("").commit();
            }
        });
    }

    //Interval Card OBVH
    private void obvhIntervalHolder(IntervalHolder holder, int position){

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
    }

    //Action Card OBVH
    private void obvhActionHolder(ActionHolder holder, int position){

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

    public void addBlankInterval(){
        MyTime blankTime = new MyTime(0, 0, 0);
        Distance zeroDistance = new Distance(0, Distance.defaultUnit);
        dispRun.getIntervals().add(new Interval2(zeroDistance, "NA", blankTime, blankTime));
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
