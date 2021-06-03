package com.sticknology.jani.dataProcessing;

import com.sticknology.jani.data.Workout;

public class InterpretWorkout {

    public String getStringWorkout(Workout workout){

        String name = workout.getWorkoutName();
        String type = workout.getWorkoutType();
        String descriptor = workout.getWorkoutDescriptor();

        return name + "|" + type + "|" + descriptor;
    }

    public Workout getWorkoutFromString(String workoutString){

        String[] splitString = workoutString.split("|");
        return new Workout(splitString[0], splitString[1], splitString[2]);
    }
}
