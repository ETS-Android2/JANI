package com.sticknology.jani.dataProcessing;

import com.sticknology.jani.data.TrainingDay;
import com.sticknology.jani.data.TrainingWeek;

import java.util.ArrayList;

public class InterpretWeek {

    public String getStringTrainingWeek(TrainingWeek trainingWeek){

        String build = trainingWeek.getTrainingWeekType() + "&/&" +
                trainingWeek.getTrainingWeekDescriptor() + "&/&"
                + trainingWeek.getTrainingWeekStartDay() + "&/&";

        //Create String From TrainingDay List
        InterpretDay interpretDay = new InterpretDay();
        ArrayList<TrainingDay> trainingDays = trainingWeek.getTrainingWeekDays();
        for(int i = 0; i < trainingDays.size(); i++){
            build += interpretDay.getStringDay(trainingDays.get(i));
            if(i < trainingDays.size()-1){
                build += "&%&";
            }
        }

        return build;
    }

    public TrainingWeek getWeekFromString(String weekString){

        String[] weekSplit = weekString.split("(&/&)");

        //Create TrainingDay List
        InterpretDay interpretDay = new InterpretDay();
        ArrayList<TrainingDay> trainingDayArrayList = new ArrayList<>();
        String[] dayArray = weekSplit[3].split("(&%&)");
        for(int i = 0; i < dayArray.length; i++){
            trainingDayArrayList.add(interpretDay.getDayFromString(dayArray[i]));
        }

        return new TrainingWeek(trainingDayArrayList, weekSplit[0], weekSplit[1], weekSplit[2]);
    }
}
