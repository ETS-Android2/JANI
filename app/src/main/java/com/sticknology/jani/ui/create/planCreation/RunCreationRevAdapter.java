package com.sticknology.jani.ui.create.planCreation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;

import org.jetbrains.annotations.NotNull;

public class RunCreationRevAdapter extends RecyclerView.Adapter<RunCreationRevAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Spinner rcIntervalSpinner;
        public Button rcDeleteButton;
        public Context mContext;

        public ViewHolder(View itemView) {

            super(itemView);

            rcIntervalSpinner = itemView.findViewById(R.id.spinner_rc_intervalrev);
            rcDeleteButton = itemView.findViewById(R.id.rc_rev_button_delete);
            mContext = itemView.getContext();
        }
    }

    public RunCreationRevAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View intervalView = inflater.inflate(R.layout.item_interval_runcreation, parent, false);

        // Return a new holder instance
        RunCreationRevAdapter.ViewHolder viewHolder = new RunCreationRevAdapter.ViewHolder(intervalView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RunCreationRevAdapter.ViewHolder holder, int position) {

        //Set spinner for the interval type
        Spinner intervalTypeSpinner = holder.rcIntervalSpinner;
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(holder.mContext,
                R.array.intervaltype_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        intervalTypeSpinner.setAdapter(adapter2);

        Button rcDeleteButton = holder.rcDeleteButton;

        //Set listener for removing item
        rcDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(RunCreationFragment.mIntervalList.size() != 1) {
                    RunCreationRevAdapter runCreationRevAdapter = RunCreationFragment.mAdapter;
                    RunCreationFragment.mIntervalList.remove(position);
                    runCreationRevAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return RunCreationFragment.mIntervalList.size();
    }


}
