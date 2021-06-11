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

public class MonthFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_month, container, false);

        return v;
    }

    public static MonthFragment newInstance(String text) {

        MonthFragment f = new MonthFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
