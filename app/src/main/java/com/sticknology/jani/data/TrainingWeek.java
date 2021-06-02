package com.sticknology.jani.data;

import java.util.ArrayList;

public class TrainingWeek {

    private final ArrayList<TrainingDay> mTrainingDays;
    private final String mType;
    private final String mDescriptor;
    private final String mStartDay;

    public TrainingWeek(ArrayList<TrainingDay> trainingDays, String type,
                        String descriptor, String startDay){

        mTrainingDays = trainingDays;
        mType = type;
        mDescriptor = descriptor;
        mStartDay = startDay;
    }

    public ArrayList<TrainingDay> getTrainingWeekDays(){
        return mTrainingDays;
    }

    public String getTrainingWeekType(){
        return mType;
    }

    public String getTrainingWeekDescriptor(){
        return mDescriptor;
    }

    public String getTrainingWeekStartDay(){
        return mStartDay;
    }
}
