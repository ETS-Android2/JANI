package com.sticknology.jani.ui.placeHolders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sticknology.jani.R;

public class DayPHFragment extends Fragment {

    public DayPHFragment newInstance(int test){

        DayPHFragment f = new DayPHFragment();
        Bundle b = new Bundle();

        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.ph_fragment_day, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }
}
