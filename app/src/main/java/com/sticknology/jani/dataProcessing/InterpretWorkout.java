package com.sticknology.jani.dataProcessing;

import android.content.Context;

import com.sticknology.jani.data.Workout;

import java.util.ArrayList;

public class InterpretWorkout {

    public String getStringWorkout(Workout workout){

        return workout.getWorkoutName() + "&!&" + workout.getWorkoutType() + "&!&"
                + workout.getWorkoutDescriptor();
    }

    public Workout getWorkoutFromString(String workoutString){

        String[] splitString = workoutString.split("(&!&)");
        return new Workout(splitString[0], splitString[1], splitString[2]);
    }

    public ArrayList<Workout> getWorkoutTemplates(Context c){

        String temps = new StandardReadWrite().readFileToString("workout_templates.txt", c);

        String[] workoutTemplates = temps.split("\n");
        ArrayList<Workout> workoutObjects = new ArrayList<>();
        //Starting i = 1 because of blank line at start of file
        for(int i = 2; i < workoutTemplates.length; i++){
            System.out.println(workoutTemplates[i]);
            String[] wStrings = workoutTemplates[i].split("(&!&)");
            workoutObjects.add(new Workout(wStrings[0], wStrings[1], wStrings[2]));
        }

        return workoutObjects;
    }
}
