package com.sticknology.jani.data;

import java.util.ArrayList;

public class TrainingDay {

    private final ArrayList<Run> mRuns;
    private final ArrayList<Workout> mWorkouts;
    private final Types.TrainingDayTypeEnum mType;
    private final String mDescriptor;

    public TrainingDay(ArrayList<Run> runs, ArrayList<Workout> workouts,
                       Types.TrainingDayTypeEnum type, String descriptor){

        mRuns = runs;
        mWorkouts = workouts;
        mType = type;
        mDescriptor = descriptor;
    }

    public ArrayList<TrainingDay> createTrainingDayList(ArrayList<Run>[] runs,
                                                        ArrayList<Workout>[] workouts,
                                                        Types.TrainingDayTypeEnum[] types,
                                                        String[] descriptors){

        ArrayList<TrainingDay> trainingDayArrayList = new ArrayList<TrainingDay>();

        for(int i = 0; i < types.length; i++){
            trainingDayArrayList.add(new TrainingDay(runs[i], workouts[i], types[i], descriptors[i]));
        }

        return trainingDayArrayList;
    }

    public ArrayList<Run> getTrainingDayRuns(){
        return mRuns;
    }

    public ArrayList<Workout> getTrainingDayWorkouts(){
        return mWorkouts;
    }

    public Types.TrainingDayTypeEnum getTrainingDayType(){
        return mType;
    }

    public String getTrainingDayDescriptor(){
        return mDescriptor;
    }
}
