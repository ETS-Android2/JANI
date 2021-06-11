package com.sticknology.jani.data;

public class Workout {

    //Object Variables
    private String mName;
    private String mType;
    private String mDescriptor;

    //Object Creation Method
    public Workout(String name, String type, String descriptor){

        mName = name;
        mType = type;
        mDescriptor = descriptor;
    }

    //Only Getters and Setters Below Here
    public String getWorkoutName(){return mName;}

    public void setWorkoutName(String name){mName = name;}

    public String getWorkoutType(){return mType;}

    public void setWorkoutType(String type){mType = type;}

    public String getWorkoutDescriptor(){return mDescriptor;}

    public void setWorkoutDescriptor(String descriptor){mDescriptor = descriptor;}
}
