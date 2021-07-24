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

import org.jetbrains.annotations.NotNull;

import java.util.Hashtable;

import static com.sticknology.jani.run2.DispRun2Fragment.dispRun;

public class DispRun2ViewHolders extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //String identifiers to get textview from hashtable for IntervalHolder
    public final String cardLabel = "cardLabel", intervalTime = "intervalTime",
            intervalDistance = "intervalDistance", intervalPace = "intervalPace",
            intervalEffort = "intervalEffort", intervalFrag = "intervalFrag";

    public class IntervalHolder extends RecyclerView.ViewHolder {

        //public TextView cardLabel, intervalDistance, intervalTime, intervalEffort, intervalPace;
        public Hashtable<String, TextView> textViewHTable;
        public CardView intervalCard;
        public Button moveUp, moveDown;
        public Context context;

        public IntervalHolder(View v){
            super(v);

            //Create Hashtable to hold all textview objects for intervalholder
            textViewHTable = new Hashtable<String, TextView>();
            textViewHTable.put(cardLabel, v.findViewById(R.id.run2_interval_label));
            textViewHTable.put(intervalTime, v.findViewById(R.id.run2_interval_time));
            textViewHTable.put(intervalDistance, v.findViewById(R.id.run2_interval_distance));
            textViewHTable.put(intervalPace, v.findViewById(R.id.run2_interval_pace));
            textViewHTable.put(intervalEffort, v.findViewById(R.id.run2_interval_effort));

            //TODO: THESE ARE A TEST
            textViewHTable.put(intervalFrag, v.findViewById(R.id.run2_interval_frag));

            //Create other references needed
            intervalCard = v.findViewById(R.id.run2_disp_interval);
            moveUp = v.findViewById(R.id.run2_interval_up);
            moveDown = v.findViewById(R.id.run2_interval_down);
            context = v.getContext();
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
        public TextView dispName, dispType, dispNotes, labelDist, labelTime, labelPace;
        public CardView header;
        public Context context;

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

    //Below two methods are blank overrides and are overridden in DispRun2Adapter
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        //Determine the type index of the desired viewHolder
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
        //Set viewholder class to be used
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
}
