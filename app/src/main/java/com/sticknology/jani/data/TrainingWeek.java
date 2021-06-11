package com.sticknology.jani.data;

import java.util.ArrayList;

public class TrainingWeek {

    //Object Variables
    private final ArrayList<TrainingDay> mTrainingDays;
    private String mType;
    private String mDescriptor;
    private String mStartDay;

    //Object Creation Method
    public TrainingWeek(ArrayList<TrainingDay> trainingDays, String type,
                        String descriptor, String startDay){

        mTrainingDays = trainingDays;
        mType = type;
        mDescriptor = descriptor;
        mStartDay = startDay;
    }

    //Only Getters and Setters Below Here
    public ArrayList<TrainingDay> getTrainingWeekDays(){return mTrainingDays;}

    public String getTrainingWeekType(){return mType;}

    public void setTrainingWeekType(String type){mType = type;}

    public String getTrainingWeekDescriptor(){return mDescriptor;}

    public void setTrainingWeekDescriptor(String descriptor){mDescriptor = descriptor;}

    public String getTrainingWeekStartDay(){return mStartDay;}

    public void setTrainingWeekStartDay(String startDay){mStartDay = startDay;}
}
