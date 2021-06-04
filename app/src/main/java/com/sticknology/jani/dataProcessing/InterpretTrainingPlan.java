package com.sticknology.jani.dataProcessing;

import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.data.TrainingWeek;

import java.util.ArrayList;

public class InterpretTrainingPlan {

    public String getStringFromTrainingPlan(TrainingPlan trainingPlan){

        String name = trainingPlan.getTrainingPlanName();
        String descriptor = trainingPlan.getTrainingPlanDescriptor();
        String startDate = trainingPlan.getTrainingPlanStartDate();
        String endDate = trainingPlan.getTrainingPlanEndDate();
        String goal = trainingPlan.getTrainingPlanGoal();

        String startBuild = name + "&`&" + descriptor + "&`&" + startDate + "&`&" + endDate + "&`&" + goal;

        ArrayList<TrainingWeek> trainingWeeks = trainingPlan.getTrainingPlanWeeks();
        String buildWeeks = "";
        InterpretWeek interpretWeek = new InterpretWeek();
        for(int i = 0; i < trainingWeeks.size(); i++){
            buildWeeks += interpretWeek.getStringTrainingWeek(trainingWeeks.get(i));
            if(i < trainingWeeks.size()-1){
                buildWeeks += "&,&";
            }
        }

        return startBuild + "&`&" + buildWeeks;
    }

    public TrainingPlan getTrainingPlanFromString(String stringPlan){

        String[] planComps = stringPlan.split("(&`&)");
        String name = planComps[0];
        String descriptor = planComps[1];
        String startDate = planComps[2];
        String endDate = planComps[3];
        String goal = planComps[4];

        String[] weekArray = planComps[5].split("(&,&)");
        InterpretWeek interpretWeek = new InterpretWeek();
        ArrayList<TrainingWeek> trainingWeeks = new ArrayList<>();
        for(int i = 0; i < weekArray.length; i++){
            trainingWeeks.add(interpretWeek.getWeekFromString(weekArray[i]));
        }

        return new TrainingPlan(trainingWeeks, name, goal, descriptor, startDate, endDate);
    }
}
