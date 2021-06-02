package com.sticknology.jani.data;

import java.util.ArrayList;

public class Run {

    private final ArrayList<Interval> mIntervals;
    private final String mName;
    private final String mDescriptor;
    private final String mType;

    public Run(ArrayList<Interval> intervals, String name, String descriptor, String type){

        mIntervals = intervals;
        mName = name;
        mDescriptor = descriptor;
        mType = type;
    }

    public ArrayList<Interval> getRunIntervals(){
        return mIntervals;
    }

    public String getRunName(){return mName;}

    public String getRunType(){
        return mDescriptor;
    }

    public String getRunDescriptor(){
        return mType;
    }
}
