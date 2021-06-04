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
        String buildWorkout = "";
        InterpretWorkout interpretWorkout = new InterpretWorkout();
        for(int i = 0; i < workouts.size(); i++){
            buildWorkout += interpretWorkout.getStringWorkout(workouts.get(i));
            if(i < workouts.size()+1){
                buildWorkout += "&_&";
            }
        }

        return startDay + "&#&" + buildWorkout;
    }

    //TODO: GET THIS AND ALL OTHER INTERPRETERS FINISHED, THEN PASS CREATED TRAINING PLAN AS STRING THROUGH FRAGS
    public TrainingDay getDayFromString(String dayString){

        String[] splitString = dayString.split("(&#&)");
        String type = splitString[0];
        String descriptor = splitString[1];
        String[] workoutStringArray = splitString[2].split("(&_&)");

        ArrayList<Workout> workoutArrayList = new ArrayList<>();
        InterpretWorkout interpretWorkout = new InterpretWorkout();
        for(int i = 0; i < workoutStringArray.length; i++){
            workoutArrayList.add(interpretWorkout.getWorkoutFromString(workoutStringArray[i]));
        }

        ArrayList<Run> runArrayList = new ArrayList<>();

        return new TrainingDay(runArrayList, workoutArrayList, type, descriptor);
    }
}
