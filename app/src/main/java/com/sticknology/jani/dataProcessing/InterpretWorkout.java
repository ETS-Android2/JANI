package com.sticknology.jani.dataProcessing;

import android.content.Context;

import com.sticknology.jani.data.Workout;

import java.util.ArrayList;

public class InterpretWorkout {

    public String getStringWorkout(Workout workout){

        String name = workout.getWorkoutName();
        String type = workout.getWorkoutType();
        String descriptor = workout.getWorkoutDescriptor();

        return name + "&!&" + type + "&!&" + descriptor;
    }

    public Workout getWorkoutFromString(String workoutString){

        String[] splitString = workoutString.split("(&!&)");
        return new Workout(splitString[0], splitString[1], splitString[2]);
    }

    public ArrayList<Workout> getWorkoutTemplates(Context context){

        StandardReadWrite standardReadWrite = new StandardReadWrite();
        String templates = standardReadWrite.readFileToString("workout_templates.txt", context);
        String[] workoutTemplates = templates.split("\n");
        ArrayList<Workout> workoutObjects = new ArrayList<>();

        for(int i = 0; i < workoutTemplates.length; i++){
            System.out.println(workoutTemplates[i]);
            String[] workoutStrings = workoutTemplates[i].split("(&!&)");
            if(workoutStrings.length == 3) {
                Workout nextTemplate = new Workout(workoutStrings[0], workoutStrings[1], workoutStrings[2]);
                workoutObjects.add(nextTemplate);
            }
        }

        return workoutObjects;
    }
}
