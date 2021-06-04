package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sticknology.jani.R;
import com.sticknology.jani.ui.create.ManageFragment;

public class EditOverviewFragment extends Fragment {

    private String mPlan;

    public static EditOverviewFragment newInstance(String plan) {

        EditOverviewFragment f = new EditOverviewFragment();
        Bundle b = new Bundle();
        b.putString("plan", plan);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        mPlan = this.getArguments().getString("plan");
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }
}
