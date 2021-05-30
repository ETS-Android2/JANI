package com.sticknology.jani.ui.create.planCreation;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;

import org.jetbrains.annotations.NotNull;

public class RunCreationRevAdapter extends RecyclerView.Adapter<RunCreationRevAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView filler;

        public ViewHolder(View itemView) {

            super(itemView);

            filler = (TextView) itemView.findViewById(R.id.textfiller_runcreation_interval);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
