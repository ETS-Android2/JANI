package com.sticknology.jani.data;

import java.util.ArrayList;

public class Run {

    //Component Variables
    private final ArrayList<Interval> mIntervals;
    private final String mName;
    private final String mDescriptor;
    private final String mType;

    //Object Creation Method
    public Run(ArrayList<Interval> intervals, String name, String descriptor, String type){

        mIntervals = intervals;
        mName = name;
        mDescriptor = descriptor;
        mType = type;
    }

    //Only Getters and Setters Below Here
    public ArrayList<Interval> getRunIntervals(){return mIntervals;}

    public String getRunName(){return mName;}

    public String getRunType(){return mDescriptor;}

    public String getRunDescriptor(){return mType;}
}
