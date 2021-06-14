package com.sticknology.jani.ui.placeHolders;

import android.app.Activity;
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

        Button setActive = holder.setActiveButton;
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

                new StandardReadWrite().appendText(dateString, "active_plan.txt", holder.mContext, Activity.MODE_APPEND, true);
                String planString = new InterpretTrainingPlan().getStringFromTrainingPlan(mTrainingPlans.get(position));
                new StandardReadWrite().appendText(planString, "active_plan.txt", holder.mContext, Activity.MODE_APPEND, true);
                MainActivity.aTrainingPlan = mTrainingPlans.get(position);

                Toast toast = Toast.makeText(holder.mContext, "Set Plan As Active", Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mTrainingPlans.size();
    }
}
