package com.sticknology.jani.ui.create.planCreation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.Run;

import java.util.List;

public class RunTemplateAdapter extends  RecyclerView.Adapter<RunTemplateAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mName;
        public TextView mType;
        public TextView mDescriptor;
        public Button mAddButton;
        public Context mContext;


        public ViewHolder(View itemView){

            super(itemView);
            mName = itemView.findViewById(R.id.wov_name);
            mType = itemView.findViewById(R.id.wov_type);
            mDescriptor = itemView.findViewById(R.id.wov_description);
            mAddButton = itemView.findViewById(R.id.wov_button_add);
            mContext = itemView.getContext();
        }
    }

    private final List<Run> mRuns;
    private final int mDayIndex;

    public RunTemplateAdapter(List<Run> runs, int day){
        mRuns = runs;
        mDayIndex = day;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View runView = inflater.inflate(R.layout.item_workouttemplate_view, parent, false);

        // Return a new holder instance
        RunTemplateAdapter.ViewHolder viewHolder = new RunTemplateAdapter.ViewHolder(runView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView nameView = holder.mName;
        TextView typeView = holder.mType;
        TextView descriptorView = holder.mDescriptor;
        nameView.setText(mRuns.get(position).getRunName());
        typeView.setText(mRuns.get(position).getRunType());
        descriptorView.setText(mRuns.get(position).getRunDescriptor());

        Button addButton = holder.mAddButton;
        addButton.setText("Add");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("pager", "Adding run");

                PlanCreationActivity.mTrainingPlan.getTrainingDay(WByWFragment.weekPosition, mDayIndex).addRun(mRuns.get(position));

                if(PlanCreationActivity.mTrainingPlan.getTrainingDay(WByWFragment.weekPosition, mDayIndex).getTrainingDayRuns()
                        .get(0).getRunName().equals(":;:")){

                    PlanCreationActivity.mTrainingPlan.getTrainingDay(WByWFragment.weekPosition,mDayIndex).removeRun(0);
                }

                PlanCreationActivity.currentTabSet = PlanCreationActivity.TABSET.VIEW;

                PlanCreateInterFragment planCreateInterFragment = PlanCreateInterFragment.newInstance(0, 1);
                PlanCreationActivity planCreationActivity = (PlanCreationActivity) holder.mContext;
                planCreationActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, planCreateInterFragment, null)
                        .addToBackStack("")
                        .commit();
                PlanCreateInterFragment.viewPager2.setCurrentItem(1, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return RunTemplateFragment.runList.size();
    }

}
