package com.sticknology.jani.data;

import java.util.ArrayList;

public class Workout {

    private final Types.WorkoutTypeEnum mType;
    private final String mDescriptor;

    public Workout(Types.WorkoutTypeEnum type, String descriptor){

        mType = type;
        mDescriptor = descriptor;
    }

    public ArrayList<Workout> createWorkoutList(Types.WorkoutTypeEnum[] types, String[] descriptors) {

            ArrayList<Workout> workoutArrayList = new ArrayList<Workout>();

            for (int i = 0; i < types.length; i++) {
                workoutArrayList.add(new Workout(types[i], descriptors[i]));
            }

            return workoutArrayList;
    }

    public Types.WorkoutTypeEnum getWorkoutType(){
        return mType;
    }

    public String getWorkoutDescriptor(){
        return mDescriptor;
    }
}
