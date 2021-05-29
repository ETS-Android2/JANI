package com.sticknology.jani.ui.create.planCreation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.sticknology.jani.ui.create.ManageFragment;

public class EditOverviewFragment extends Fragment {

    public static EditOverviewFragment newInstance(String text) {

        EditOverviewFragment f = new EditOverviewFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
