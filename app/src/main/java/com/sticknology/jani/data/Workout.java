package com.sticknology.jani.data;

import java.util.ArrayList;

public class Workout {

    private final String mName;
    private final String mType;
    private final String mDescriptor;

    public Workout(String name, String type, String descriptor){

        mName = name;
        mType = type;
        mDescriptor = descriptor;
    }

    public String getWorkoutName(){return mName;}

    public String getWorkoutType(){
        return mType;
    }

    public String getWorkoutDescriptor(){
        return mDescriptor;
    }
}
