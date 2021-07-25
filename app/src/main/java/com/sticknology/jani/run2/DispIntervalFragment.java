package com.sticknology.jani.run2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.sticknology.jani.R;
import com.sticknology.jani.data2.Interval2;

import static com.sticknology.jani.run2.DispRun2Fragment.dispRun;

public class DispIntervalFragment extends Fragment {

    private int position;

    public static DispIntervalFragment newInstance(String editType, int intervalIndex) {

        Bundle args = new Bundle();
        args.putInt("intervalIndex", intervalIndex);
        DispIntervalFragment fragment = new DispIntervalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        position = getArguments().getInt("intervalIndex");
        return inflater.inflate(R.layout.run2_disp_interval, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Setting the label for the interval card
        TextView testLabel = getView().findViewById(R.id.run2_interval_label);
        testLabel.setText("Interval " + (position));

        //Get interval object for current position
        Interval2 cardInterval = dispRun.getIntervals().get(position);

        //Setting display data for the card
        TextView intervalDistance = getView().findViewById(R.id.run2_interval_distance);
        intervalDistance.setText(String.valueOf(cardInterval.getDistance().getDoubleDistance()));
        TextView intervalPace = getView().findViewById(R.id.run2_interval_field);
        intervalPace.setText(cardInterval.getPace().getDispString());
        TextView intervalEffort = getView().findViewById(R.id.run2_interval_effort);
        intervalEffort.setText(cardInterval.getEffort());

        //Setting on click listener for opening edit fragment
        CardView cardView = getView().findViewById(R.id.run2_disp_interval);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
