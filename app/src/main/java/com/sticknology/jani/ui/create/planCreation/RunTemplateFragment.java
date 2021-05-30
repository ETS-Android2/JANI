package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.sticknology.jani.R;
import com.sticknology.jani.ui.create.ManageFragment;

public class RunTemplateFragment extends Fragment {

    public static RunTemplateFragment newInstance(String text) {

        RunTemplateFragment f = new RunTemplateFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_runtemplate, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button newButton = getView().findViewById(R.id.button_newrun_template);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RunCreationFragment runCreationFragment = new RunCreationFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, runCreationFragment, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
