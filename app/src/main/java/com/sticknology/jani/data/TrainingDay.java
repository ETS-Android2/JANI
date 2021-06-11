package com.sticknology.jani.data;

import java.util.ArrayList;

public class TrainingDay {

    //Component Variables
    private final ArrayList<Run> mRuns;
    private final ArrayList<Workout> mWorkouts;
    private String mType;
    private String mDescriptor;

    //Object Creation Method
    public TrainingDay(ArrayList<Run> runs, ArrayList<Workout> workouts, String type, String descriptor){

        mRuns = runs;
        mWorkouts = workouts;
        mType = type;
        mDescriptor = descriptor;
    }

    //Add and Remove for Run List
    public void removeRun(int pos){mRuns.remove(pos);}

    public void addRun(Run run){mRuns.add(run);}

    //Add and Remove for Workout List
    public void removeWorkout(int pos){mWorkouts.remove(pos);}

    public void addWorkout(Workout workout){mWorkouts.add(workout);}

    //Only Getters and Setters Below Here
    public ArrayList<Run> getTrainingDayRuns(){return mRuns;}

    public ArrayList<Workout> getTrainingDayWorkouts(){return mWorkouts;}

    public String getTrainingDayType(){return mType;}

    public void setTrainingDayType(String type){mType = type;}

    public String getTrainingDayDescriptor(){return mDescriptor;}

    public void setTrainingDayDescriptor(String dayDescriptor){mDescriptor = dayDescriptor;}
}
