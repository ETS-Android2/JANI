package com.sticknology.jani.ui.placeHolders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.MainActivity;
import com.sticknology.jani.R;
import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.dataProcessing.InterpretTrainingPlan;
import com.sticknology.jani.dataProcessing.StandardReadWrite;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PHCreateAdapter extends RecyclerView.Adapter<PHCreateAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView planName;
        public TextView planGoal;
        public TextView planDescript;
        public Button setActiveButton;
        public Context mContext;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            planName = itemView.findViewById(R.id.rev_trainingplan_name);
            planGoal = itemView.findViewById(R.id.rev_trainingplan_goal);
            planDescript = itemView.findViewById(R.id.rev_trainingplan_descript);
            setActiveButton = itemView.findViewById(R.id.rev_setactive_trainingplan);
            mContext = itemView.getContext();

        }
    }

    private final List<TrainingPlan> mTrainingPlans;

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

        Button setActive = holder.setActiveButton;
        setActive.setText("Set Active");
        setActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date currentDate = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String dateString = dateFormat.format(currentDate);

                try {
                    PrintWriter writer = new PrintWriter("active_plan.txt");
                    writer.print("");
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                //Writing The File
                //Removes active tag from any other training plan
                for(int i = 0; i < mTrainingPlans.size(); i++){
                    if(mTrainingPlans.get(i).getTrainingPlanActive().equals("ACTIVE")){
                        mTrainingPlans.get(i).setTrainingPlanActive(" ");
                    }
                }
                //Applies active tag for selected training plan
                TrainingPlan trainingPlan = mTrainingPlans.get(position);
                trainingPlan.setTrainingPlanActive("ACTIVE");

                //Build string and write
                String buildN = holder.mContext.getString(R.string.file_encoding) + "\n";
                for(int i = 0; i < mTrainingPlans.size(); i++){
                    buildN += new InterpretTrainingPlan().getStringFromTrainingPlan(mTrainingPlans.get(i));
                    if(i < mTrainingPlans.size()-1){
                        buildN += "\n";
                    }
                }
                new StandardReadWrite().writeFile(buildN, "training_plans.txt", holder.mContext);

                //Below is legacy code for creating active_plan.txt
                String planString = new InterpretTrainingPlan().getStringFromTrainingPlan(mTrainingPlans.get(position));
                String build = holder.mContext.getString(R.string.file_encoding) + "\n" + dateString + "\n" + planString;
                new StandardReadWrite().writeFile(build, "active_plan.txt", holder.mContext);
                MainActivity.aTrainingPlan = mTrainingPlans.get(position);

                //Shows toast for visual confirmation that plan was activated
                Toast toast = Toast.makeText(holder.mContext, "Set Plan As Active", Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return PHCreateFragment.trainingPlanList.size();
    }
}
