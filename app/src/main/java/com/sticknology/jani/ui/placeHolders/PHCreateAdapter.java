package com.sticknology.jani.ui.placeHolders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.TrainingPlan;

import java.util.List;

public class PHCreateAdapter extends RecyclerView.Adapter<PHCreateAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView planName;
        public TextView planGoal;
        public TextView planDescript;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            planName = itemView.findViewById(R.id.rev_trainingplan_name);
            planGoal = itemView.findViewById(R.id.rev_trainingplan_goal);
            planDescript = itemView.findViewById(R.id.rev_trainingplan_descript);

        }
    }

    private List<TrainingPlan> mTrainingPlans;

    public PHCreateAdapter(List<TrainingPlan> trainingPlans) {
        mTrainingPlans = trainingPlans;
    }

    @Override
    public PHCreateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_trainingplan, parent, false);

        // Return a new holder instance
        PHCreateAdapter.ViewHolder viewHolder = new PHCreateAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView name = holder.planName;
        TextView goal = holder.planGoal;
        TextView descript = holder.planDescript;

        name.setText(mTrainingPlans.get(position).getTrainingPlanName());
        goal.setText(mTrainingPlans.get(position).getTrainingPlanGoal());
        descript.setText(mTrainingPlans.get(position).getTrainingPlanDescriptor());

    }

    @Override
    public int getItemCount() {
        return mTrainingPlans.size();
    }
}
