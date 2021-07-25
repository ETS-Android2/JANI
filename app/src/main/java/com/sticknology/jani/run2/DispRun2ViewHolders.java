package com.sticknology.jani.run2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
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
    public final String cardLabel = "cardLabel", intervalDistance = "intervalDistance",
            intervalPace = "intervalPace", intervalEffort = "intervalEffort";

    public class IntervalHolder extends RecyclerView.ViewHolder {

        //public TextView cardLabel, intervalDistance, intervalTime, intervalEffort, intervalPace;
        public Hashtable<String, TextView> textViewHTable;
        public CardView intervalDispCard, intervalEditCard;
        public TextView viewNotes;
        public Button distanceButton, field2Button, doneButton, deleteButton;
        public Spinner fieldSpinner, effortSpinner;
        public Context context;
        public View view;

        public IntervalHolder(View v){
            super(v);

            //Create Hashtable to hold all textview objects for intervalholder
            textViewHTable = new Hashtable<String, TextView>();
            textViewHTable.put(cardLabel, v.findViewById(R.id.run2_interval_label));
            textViewHTable.put(intervalDistance, v.findViewById(R.id.run2_interval_distance));
            textViewHTable.put(intervalPace, v.findViewById(R.id.run2_interval_pace));
            textViewHTable.put(intervalEffort, v.findViewById(R.id.run2_interval_effort));

            //Create other references needed
            doneButton = v.findViewById(R.id.run2_edit_done);
            deleteButton = v.findViewById(R.id.run2_edit_delete);
            distanceButton = v.findViewById(R.id.run2_edit_button1);
            field2Button = v.findViewById(R.id.run2_edit_button2);
            fieldSpinner = v.findViewById(R.id.run2_edit_label1);
            effortSpinner = v.findViewById(R.id.run2_edit_effort);
            intervalDispCard = v.findViewById(R.id.run2_disp_interval);
            intervalEditCard = v.findViewById(R.id.run2_edit_interval);
            viewNotes = v.findViewById(R.id.run2_edit_inotes);
            view = v;
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
