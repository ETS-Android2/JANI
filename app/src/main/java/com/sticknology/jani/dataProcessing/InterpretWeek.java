package com.sticknology.jani.dataProcessing;

import com.sticknology.jani.data.TrainingDay;
import com.sticknology.jani.data.TrainingWeek;

import java.util.ArrayList;

public class InterpretWeek {

    public String getStringTrainingWeek(TrainingWeek trainingWeek){

        ArrayList<TrainingDay> trainingDays = trainingWeek.getTrainingWeekDays();
        String type = trainingWeek.getTrainingWeekType();
        String descriptor = trainingWeek.getTrainingWeekDescriptor();
        String startDay = trainingWeek.getTrainingWeekStartDay();

        String baseBuild = type + "&/&" + descriptor + "&/&" + startDay;

        String dayBuild = "";
        InterpretDay interpretDay = new InterpretDay();
        for(int i = 0; i < trainingDays.size(); i++){
            dayBuild += interpretDay.getStringDay(trainingDays.get(i));
            if(i < trainingDays.size()-1){
                dayBuild += "&%&";
            }
        }

        return baseBuild + "&/&" + dayBuild;
    }

    public TrainingWeek getWeekFromString(String weekString){

        String[] weekSplit = weekString.split("(&/&)");
        String type = weekSplit[0];
        String descriptor = weekSplit[1];
        String startDay = weekSplit[2];
        String[] dayArray = weekSplit[3].split("(&%&)");

        InterpretDay interpretDay = new InterpretDay();
        ArrayList<TrainingDay> trainingDayArrayList = new ArrayList<>();
        for(int i = 0; i < dayArray.length; i++){
            trainingDayArrayList.add(interpretDay.getDayFromString(dayArray[i]));
        }

        return new TrainingWeek(trainingDayArrayList, type, descriptor, startDay);
    }
}
