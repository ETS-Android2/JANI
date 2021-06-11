package com.sticknology.jani.dataProcessing;

import com.sticknology.jani.data.Run;
import com.sticknology.jani.data.TrainingDay;
import com.sticknology.jani.data.Workout;

import java.util.ArrayList;

public class InterpretDay {

    public String getStringDay(TrainingDay tDay){

        //Start Build String
        String build = tDay.getTrainingDayType() + "&#&" + tDay.getTrainingDayDescriptor() + "&#&";

        //Add Workout List to String
        ArrayList<Workout> workouts = tDay.getTrainingDayWorkouts();
        InterpretWorkout interpretWorkout = new InterpretWorkout();
        for(int i = 0; i < workouts.size(); i++){
            build += interpretWorkout.getStringWorkout(workouts.get(i));
            if (i < workouts.size()-1) build += "&_&";
        }

        //Divider Between WorkoutList and RunList
        build += "&#&";

        //Add Run List to String
        ArrayList<Run> runs = tDay.getTrainingDayRuns();
        InterpretRun interpretRun = new InterpretRun();
        for(int i = 0; i < runs.size(); i++){
            build += interpretRun.getStringRun(runs.get(i));
            if (i < workouts.size()-1) build += "&&&";
        }

        return build;
    }

    public TrainingDay getDayFromString(String dayString){

        //Perform Regex Operations to Split Up Data Types into Arrays
        String[] splitString = dayString.split("(&#&)");
        String type = splitString[0];
        String descriptor = splitString[1];
        String[] workoutStringArray = splitString[2].split("(&_&)");
        String[] runStringArray = splitString[3].split("(&&&)");

        //Form ArrayList of Workouts
        ArrayList<Workout> workoutArrayList = new ArrayList<>();
        InterpretWorkout interpretWorkout = new InterpretWorkout();
        for(int i = 0; i < workoutStringArray.length; i++){
            workoutArrayList.add(interpretWorkout.getWorkoutFromString(workoutStringArray[i]));
        }

        //Form ArrayList of Runs
        ArrayList<Run> runArrayList = new ArrayList<>();
        InterpretRun interpretRun = new InterpretRun();
        for(int i = 0; i < runStringArray.length; i++){
            runArrayList.add(interpretRun.getObjectRun(runStringArray[i]));
        }

        return new TrainingDay(runArrayList, workoutArrayList, type, descriptor);
    }
}
