package com.sticknology.jani.ui.create;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;

import java.util.List;

/*public class PlanRevAdapter extends RecyclerView.Adapter<PlanRevAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.rev_trainingplan_name);
            messageButton = (Button) itemView.findViewById(R.id.rev_trainingplan_button);
        }
    }

    // Store a member variable for the contacts
    private List<TrainingPlan> mTrainingPlans;

    // Pass in the contact array into the construct or
    public PlanRevAdapter(List<TrainingPlan> trainingPlans) {
        mTrainingPlans = trainingPlans;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public PlanRevAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_trainingplan, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PlanRevAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        TrainingPlan contact = mTrainingPlans.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(contact.getName());
        Button button = holder.messageButton;
        button.setText(contact.isOnline() ? "Message" : "Offline");
        button.setEnabled(contact.isOnline());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mTrainingPlans.size();
    }
}*/
