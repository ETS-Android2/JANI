package com.sticknology.jani.data;

import java.util.ArrayList;

public class TrainingPlan {

    //Object Component Variables
    private final ArrayList<TrainingWeek> mTrainingWeeks;
    private String mName;
    private String mDescriptor;
    private String mStartDate;
    private String mEndDate;
    private String mGoal;

    //Object Creation Method
    public TrainingPlan(ArrayList<TrainingWeek> trainingWeeks, String name, String goal,
                        String descriptor, String startDate, String endDate){

        mTrainingWeeks = trainingWeeks;
        mName = name;
        mDescriptor = descriptor;
        mStartDate = startDate;
        mEndDate = endDate;
        mGoal = goal;
    }

    //Gets TrainingDay from Plan
    public TrainingDay getTrainingDay(int weekNumber, int dayPosition){
        return mTrainingWeeks.get(weekNumber).getTrainingWeekDays().get(dayPosition);
    }

    //Modifying TrainingWeek Arraylist
    public void addWeek(){
        EmptyObjects emptyObjects = new EmptyObjects();
        mTrainingWeeks.add(emptyObjects.createEmptyTrainingWeek());
    }

    public void removeWeek(int pos){mTrainingWeeks.remove(pos);}

    //Only getters and setters below here
    public ArrayList<TrainingWeek> getTrainingPlanWeeks(){return mTrainingWeeks;}

    public String getTrainingPlanName(){return mName;}

    public void setTrainingPlanName(String name){mName = name;}

    public String getTrainingPlanDescriptor(){return mDescriptor;}

    public void setTrainingPlanDescriptor(String descriptor){mDescriptor = descriptor;}

    public String getTrainingPlanGoal(){return mGoal;}

    public void setTrainingPlanGoal(String goal){mGoal = goal;}

    public String getTrainingPlanStartDate(){return mStartDate;}

    public void setTrainingPlanStartDate(String startDate){mStartDate = startDate;}

    public String getTrainingPlanEndDate(){return mEndDate;}

    public void setTrainingPlanEndDate(String endDate){mEndDate = endDate;}
}
