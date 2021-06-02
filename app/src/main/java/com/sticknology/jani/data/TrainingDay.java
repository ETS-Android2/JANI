package com.sticknology.jani.data;

import java.util.ArrayList;

public class TrainingDay {

    private final ArrayList<Run> mRuns;
    private final ArrayList<Workout> mWorkouts;
    private final String mType;
    private final String mDescriptor;

    public TrainingDay(ArrayList<Run> runs, ArrayList<Workout> workouts, String type, String descriptor){

        mRuns = runs;
        mWorkouts = workouts;
        mType = type;
        mDescriptor = descriptor;
    }

    public ArrayList<Run> getTrainingDayRuns(){
        return mRuns;
    }

    public ArrayList<Workout> getTrainingDayWorkouts(){
        return mWorkouts;
    }

    public String getTrainingDayType(){
        return mType;
    }

    public String getTrainingDayDescriptor(){
        return mDescriptor;
    }
}
