package com.sticknology.jani.run2;

import com.sticknology.jani.data.Workout;
import com.sticknology.jani.data2.MyTime;

import java.util.ArrayList;

public class Run2 {

    private String mTitle, mNotes, mType;
    private MyTime mTimeOfDay;
    private ArrayList<Interval2> mIntervals;
    private ArrayList<Workout> mWorkouts;

    //Object creation method
    public Run2(String title, String notes, String type, MyTime timeOfDay,
                ArrayList<Interval2> intervals, ArrayList<Workout> workouts){

        mTitle = title;
        mNotes = notes;
        mType = type;
        mTimeOfDay = timeOfDay;
        mIntervals = intervals;
        mWorkouts = workouts;
    }

    //Only getters and setters below here

}
