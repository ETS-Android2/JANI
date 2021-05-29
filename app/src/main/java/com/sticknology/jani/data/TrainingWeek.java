package com.sticknology.jani.data;

import java.util.ArrayList;

public class TrainingWeek {

    private final ArrayList<TrainingDay> mTrainingDays;
    private final Types.TrainingWeekTypeEnum mType;
    private final String mDescriptor;
    private final int mStartDay;

    public TrainingWeek(ArrayList<TrainingDay> trainingDays, Types.TrainingWeekTypeEnum type,
                        String descriptor, int startDay){

        mTrainingDays = trainingDays;
        mType = type;
        mDescriptor = descriptor;
        mStartDay = startDay;
    }

    public ArrayList<TrainingWeek> createTrainingWeekList(ArrayList<TrainingDay>[] trainingDays,
                                                          Types.TrainingWeekTypeEnum[] types,
                                                          String[] descriptors, int[] startDays){

        ArrayList<TrainingWeek> trainingWeekArrayList = new ArrayList<TrainingWeek>();

        for(int i = 0; i < descriptors.length; i++){
            trainingWeekArrayList.add(new TrainingWeek(trainingDays[i], types[i], descriptors[i], startDays[i]));
        }

        return trainingWeekArrayList;
    }

    public ArrayList<TrainingDay> getTrainingWeekDays(){
        return mTrainingDays;
    }

    public Types.TrainingWeekTypeEnum getTrainingWeekType(){
        return mType;
    }

    public String getTrainingWeekDescriptor(){
        return mDescriptor;
    }

    public int getTrainingWeekStartDay(){
        return mStartDay;
    }
}
