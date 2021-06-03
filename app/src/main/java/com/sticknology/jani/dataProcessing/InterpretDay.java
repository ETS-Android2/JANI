package com.sticknology.jani.dataProcessing;

import com.sticknology.jani.data.Run;
import com.sticknology.jani.data.TrainingDay;
import com.sticknology.jani.data.Workout;

import java.util.ArrayList;

public class InterpretDay {

    public String getStringDay(TrainingDay trainingDay){

        ArrayList<Workout> workouts = trainingDay.getTrainingDayWorkouts();
        String type = trainingDay.getTrainingDayType();
        String descriptor = trainingDay.getTrainingDayDescriptor();

        String startDay = type + "&#&" + descriptor;
        String build = "";
        InterpretWorkout interpretWorkout = new InterpretWorkout();
        for(int i = 0; i < workouts.size(); i++){
            build += interpretWorkout.getStringWorkout(workouts.get(i));
        }

        return startDay + "&#&" + build;
    }

    //TODO: GET THIS AND ALL OTHER INTERPRETERS FINISHED, THEN PASS CREATED TRAINING PLAN AS STRING THROUGH FRAGS
    public Workout getDayFromString(String dayString){

        String[] splitString = dayString.split("(&!&)");
        return new Workout(splitString[0], splitString[1], splitString[2]);
    }
}
