package com.sticknology.jani.ui.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.ui.plan.PlanRevAdapter;

import java.util.ArrayList;

public class ManageFragment extends Fragment {

    ArrayList<TrainingPlan> mTrainingPlans;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_manage, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        RecyclerView revTrainingPlans = (RecyclerView) getView().findViewById(R.id.rev_manage);
        mTrainingPlans = TrainingPlan.createContactsList(20);
        PlanRevAdapter planRevAdapter = new PlanRevAdapter(mTrainingPlans);
        revTrainingPlans.setAdapter(planRevAdapter);
        revTrainingPlans.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.manage_nav_menu, menu);
    }

    public static ManageFragment newInstance(String text) {

        ManageFragment f = new ManageFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
