package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sticknology.jani.R;
import com.sticknology.jani.ui.create.ManageFragment;

public class EditOverviewFragment extends Fragment {

    public static EditOverviewFragment newInstance() {

        EditOverviewFragment f = new EditOverviewFragment();
        Bundle b = new Bundle();

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_overview, container, false);
    }
}
