package com.sticknology.jani.data;

import java.util.ArrayList;

public class TrainingPlanActual {

    private final ArrayList<TrainingWeek> mTrainingWeeks;
    private final String mName;
    private final String mDescriptor;

    public TrainingPlanActual(ArrayList<TrainingWeek> trainingWeeks, String name, String descriptor){

        mTrainingWeeks = trainingWeeks;
        mName = name;
        mDescriptor = descriptor;
    }

    public ArrayList<TrainingPlanActual> createTrainingPlanActualArrayList(
            ArrayList<TrainingWeek>[] trainingWeeks, String[] names, String[] descriptors){

        ArrayList<TrainingPlanActual> trainingPlanActualArrayList = new ArrayList<TrainingPlanActual>();

        for(int i = 0; i < names.length; i++){
            trainingPlanActualArrayList.add(new TrainingPlanActual(trainingWeeks[i], names[i], descriptors[i]));
        }

        return trainingPlanActualArrayList;
    }

    public ArrayList<TrainingWeek> getTrainingPlanWeeks(){
        return mTrainingWeeks;
    }

    public String getTrainingPlanName(){
        return mName;
    }

    public String getTrainingPlanDescriptor(){
        return mDescriptor;
    }
}
