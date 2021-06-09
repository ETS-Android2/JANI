package com.sticknology.jani.ui.placeHolders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.Run;
import com.sticknology.jani.data.Workout;
import com.sticknology.jani.ui.create.planCreation.WByWDataHandler;
import com.sticknology.jani.ui.create.planCreation.WByWFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PHPlanInternalAdapter extends RecyclerView.Adapter<PHPlanInternalAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mName;
        public TextView mDescript;
        public Button mRemove;

        public ViewHolder(View itemView){

            super(itemView);
            mName = itemView.findViewById(R.id.wbyw_rev_rev_name);
            mDescript = itemView.findViewById(R.id.wbyw_rev_rev_descript);
            mRemove = itemView.findViewById(R.id.wbyw_rev_rev_del);
        }
    }

    private List<Workout> mWorkouts;
    private List<Run> mRuns;
    private int mDayPosition;

    public PHPlanInternalAdapter(List<Workout> workoutList, List<Run> runList, int dayPosition){

        mWorkouts = workoutList;
        mDayPosition = dayPosition;
        mRuns = runList;
    }

    @Override
    public PHPlanInternalAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View runView = inflater.inflate(R.layout.item_day_rev, parent, false);

        // Return a new holder instance
        PHPlanInternalAdapter.ViewHolder viewHolder = new PHPlanInternalAdapter.ViewHolder(runView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PHPlanInternalAdapter.ViewHolder holder, int position) {

        int woIndex = position - mRuns.size();

        if(position < mRuns.size() && !mRuns.get(position).getRunName().equals(":;:")){

            holder.mName.setText(mRuns.get(position).getRunName());
            holder.mDescript.setText(mRuns.get(position).getRunType());

            holder.mName.setVisibility(View.VISIBLE);
            holder.mDescript.setVisibility(View.VISIBLE);
            holder.mRemove.setVisibility(View.GONE);
        }
        else if(woIndex >= 0 && !mWorkouts.get(woIndex).getWorkoutName().equals(":;:")) {
            holder.mName.setText(mWorkouts.get(woIndex).getWorkoutName());
            holder.mDescript.setText(mWorkouts.get(woIndex).getWorkoutType());

            holder.mName.setVisibility(View.VISIBLE);
            holder.mDescript.setVisibility(View.VISIBLE);
            holder.mRemove.setVisibility(View.GONE);
        }
        else {
            holder.mName.setVisibility(View.GONE);
            holder.mDescript.setVisibility(View.GONE);
            holder.mRemove.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mWorkouts.size() + mRuns.size();
    }
}
