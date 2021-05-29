package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.sticknology.jani.R;

public class EditWeekByWeekFragment extends Fragment {

    public static EditWeekByWeekFragment newInstance(String text) {

        EditWeekByWeekFragment f = new EditWeekByWeekFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_wbyw, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button newButton = getView().findViewById(R.id.button_wbyw);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Might not need enum, all transitions will be directly handled???
                PlanCreationActivity.currentTabSet = PlanCreationActivity.TABSET.TEMPLATES;

                PlanCreateInterFragment planCreateInterFragment = new PlanCreateInterFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, planCreateInterFragment, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
