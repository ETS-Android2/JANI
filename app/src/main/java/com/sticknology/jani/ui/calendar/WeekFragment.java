package com.sticknology.jani.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sticknology.jani.R;

//---------------------------------------------------------
//---------------------------------------------------------
//                 Currently Unused
//---------------------------------------------------------
//---------------------------------------------------------

public class WeekFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_week, container, false);

        return v;
    }

    public static WeekFragment newInstance(String text) {

        WeekFragment f = new WeekFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
