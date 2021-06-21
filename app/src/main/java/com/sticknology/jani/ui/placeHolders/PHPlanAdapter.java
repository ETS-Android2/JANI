package com.sticknology.jani.ui.placeHolders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.TrainingDay;
import com.sticknology.jani.data.Workout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PHPlanAdapter extends RecyclerView.Adapter<PHPlanAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public Spinner mDayTypeSpinner;
        public Context mContext;
        public RecyclerView mInternalRecyclerView;
        public TextView mDayName;
        public Button mNewItemButton;
        public TextView mDayTypeText;

        public ViewHolder(View itemView){

            super(itemView);

            mDayTypeSpinner = itemView.findViewById(R.id.wbyw_rev_spinner_daytype);
            mContext = itemView.getContext();
            mInternalRecyclerView = itemView.findViewById(R.id.wbyw_rev_rev);
            mDayName = itemView.findViewById(R.id.wbyw_rev_text_dayname);
            mNewItemButton = itemView.findViewById(R.id.wbyw_rev_newitem);
            mDayTypeText = itemView.findViewById(R.id.wbyw_daytype_text);
        }
    }

    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public static List<Workout>[] mWorkoutList = new List[7];

    private final List<TrainingDay> mTrainingDayList;


    public PHPlanAdapter(){

        mTrainingDayList = PlanPHFragment.trainingWeek.getTrainingWeekDays();
    }

    @NotNull
    @Override
    public PHPlanAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View intervalView = inflater.inflate(R.layout.item_day_wbw, parent, false);

        return new PHPlanAdapter.ViewHolder(intervalView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PHPlanAdapter.ViewHolder holder, int position) {

        //Set Spinner for type of day
        Spinner dayType = holder.mDayTypeSpinner;
        dayType.setVisibility(View.GONE);

        TextView dayTypeText = holder.mDayTypeText;
        dayTypeText.setText("Day Type: " + mTrainingDayList.get(position).getTrainingDayType());

        //Create list for interior Recycler View
        mWorkoutList[position] = mTrainingDayList.get(position).getTrainingDayWorkouts();

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.mInternalRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL, false);

        layoutManager.setInitialPrefetchItemCount(PlanPHFragment.trainingWeek.getTrainingWeekDays().get(position).getTrainingDayWorkouts().size());

        TrainingDay trainingDay = PlanPHFragment.trainingWeek.getTrainingWeekDays().get(position);

        PHPlanInternalAdapter childItemAdapter = new PHPlanInternalAdapter(trainingDay.getTrainingDayWorkouts(), trainingDay.getTrainingDayRuns(), position);
        holder.mInternalRecyclerView.setLayoutManager(layoutManager);
        holder.mInternalRecyclerView.setAdapter(childItemAdapter);
        holder.mInternalRecyclerView.setRecycledViewPool(viewPool);

        //Sets the correct day for each rev item
        TextView dayName = holder.mDayName;
        String[] dayArray = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayName.setText(dayArray[position]);

        //Hides the additem button as reusing layout
        Button newItemButton = holder.mNewItemButton;
        newItemButton.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return mTrainingDayList.size();
    }
}

