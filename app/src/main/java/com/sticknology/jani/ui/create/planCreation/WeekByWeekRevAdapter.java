package com.sticknology.jani.ui.create.planCreation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.TrainingDay;

import org.jetbrains.annotations.NotNull;

import static com.sticknology.jani.ui.create.planCreation.PlanCreationActivity.mTrainingPlan;
import static com.sticknology.jani.ui.create.planCreation.WByWFragment.weekPosition;

public class WeekByWeekRevAdapter extends RecyclerView.Adapter<WeekByWeekRevAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public Spinner mDayTypeSpinner;
        public Context mContext;
        public RecyclerView mInternalRecyclerView;
        public TextView mDayName;
        public Button mNewItemButton;
        public RecyclerView mRecyclerView;
        public TextView mDayTypeDisp;

        public ViewHolder(View itemView){

            super(itemView);

            mDayTypeSpinner = itemView.findViewById(R.id.wbyw_rev_spinner_daytype);
            mContext = itemView.getContext();
            mInternalRecyclerView = itemView.findViewById(R.id.wbyw_rev_rev);
            mDayName = itemView.findViewById(R.id.wbyw_rev_text_dayname);
            mNewItemButton = itemView.findViewById(R.id.wbyw_rev_newitem);
            mRecyclerView = itemView.findViewById(R.id.wbyw_rev_rev);
            mDayTypeDisp = itemView.findViewById(R.id.wbyw_daytype_text);
        }
    }

    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();


    public WeekByWeekRevAdapter(){

    }

    @Override
    public WeekByWeekRevAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View intervalView = inflater.inflate(R.layout.item_day_wbw, parent, false);

        // Return a new holder instance
        WeekByWeekRevAdapter.ViewHolder viewHolder = new WeekByWeekRevAdapter.ViewHolder(intervalView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        //Hide textview used instead of spinner for display
        TextView dayTypeTextView = holder.mDayTypeDisp;
        dayTypeTextView.setVisibility(View.GONE);

        //Set Spinner for type of day
        Spinner dayType = holder.mDayTypeSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(holder.mContext,
                R.array.daytype_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedType = dayType.getSelectedItem().toString();
                mTrainingPlan.getTrainingDay(weekPosition, position).setTrainingDayType(selectedType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        dayType.setAdapter(adapter);
        //TODO: Make this code not suck
        int dayIndex = 0;
        String[] dayTypes = {"None", "Easy", "Recovery", "Workout", "Long Distance", "Training", "Off Day"};
        for(int i = 0; i < dayTypes.length; i++){
            if(dayTypes[i].equals(mTrainingPlan.getTrainingDay(weekPosition, position).getTrainingDayType())){
                dayIndex = i;
                break;
            }
        }
        dayType.setSelection(dayIndex);


        //Create list for interior Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.mInternalRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL, false);

        layoutManager.setInitialPrefetchItemCount(WByWFragment.mTrainingWeek.getTrainingWeekDays().get(position).getTrainingDayWorkouts().size());

        TrainingDay trainingDay = WByWFragment.mTrainingWeek.getTrainingWeekDays().get(position);

        Log.d("edit", trainingDay.getTrainingDayRuns().get(0).getRunName());

        WByWRunRevAdapter childItemAdapter = new WByWRunRevAdapter(trainingDay.getTrainingDayWorkouts(), trainingDay.getTrainingDayRuns(), position);
        holder.mInternalRecyclerView.setLayoutManager(layoutManager);
        holder.mInternalRecyclerView.setAdapter(childItemAdapter);
        holder.mInternalRecyclerView.setRecycledViewPool(viewPool);

        //Sets the correct day for each rev item
        TextView dayName = holder.mDayName;
        String[] dayArray = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayName.setText(dayArray[position]);

        //Sets listener for the button to add an item to the day
        Button newItemButton = holder.mNewItemButton;
        newItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WByWFragment.mTrainingWeek = new WByWDataHandler().fixTrainingWeek(WByWFragment.mTrainingWeek);
                mTrainingPlan.getTrainingPlanWeeks().set(weekPosition, WByWFragment.mTrainingWeek);

                //Prepare for transaction
                PlanCreationActivity planCreationActivity = (PlanCreationActivity) holder.mContext;
                PlanCreationActivity.currentTabSet = PlanCreationActivity.TABSET.TEMPLATES;
                PlanCreateInterFragment planCreateInterFragment = PlanCreateInterFragment
                        .newInstance(position);

                //Fragment Transaction
                planCreationActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, planCreateInterFragment, null)
                        .addToBackStack("")
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return WByWFragment.mTrainingWeek.getTrainingWeekDays().size();
    }
}
