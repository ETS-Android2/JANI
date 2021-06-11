package com.sticknology.jani.dataProcessing;

import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.data.TrainingWeek;

import java.util.ArrayList;

public class InterpretTrainingPlan {

    public String getStringFromTrainingPlan(TrainingPlan tPlan){

        //Initial Details
        String build = tPlan.getTrainingPlanName() + "&`&" + tPlan.getTrainingPlanDescriptor()
                + "&`&" + tPlan.getTrainingPlanStartDate() + "&`&" + tPlan.getTrainingPlanEndDate()
                + "&`&" + tPlan.getTrainingPlanGoal() + "&`&";

        //TrainingWeek List
        ArrayList<TrainingWeek> trainingWeeks = tPlan.getTrainingPlanWeeks();
        InterpretWeek interpretWeek = new InterpretWeek();
        for(int i = 0; i < trainingWeeks.size(); i++){
            build += interpretWeek.getStringTrainingWeek(trainingWeeks.get(i));
            if(i < trainingWeeks.size()-1){
                build += "&,&";
            }
        }

        return build;
    }

    public TrainingPlan getTrainingPlanFromString(String stringPlan){

        String[] pComp = stringPlan.split("(&`&)");

        //Get Training Week List
        String[] weekArray = pComp[5].split("(&,&)");
        InterpretWeek interpretWeek = new InterpretWeek();
        ArrayList<TrainingWeek> trainingWeeks = new ArrayList<>();
        for(int i = 0; i < weekArray.length; i++){
            trainingWeeks.add(interpretWeek.getWeekFromString(weekArray[i]));
        }

        return new TrainingPlan(trainingWeeks, pComp[0], pComp[4], pComp[1], pComp[2], pComp[3]);
    }
}
