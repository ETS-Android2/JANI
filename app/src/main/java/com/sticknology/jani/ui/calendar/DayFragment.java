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

public class DayFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_day, container, false);

        return v;
    }

    public static DayFragment newInstance(String text) {

        DayFragment f = new DayFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
