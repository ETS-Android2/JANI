package com.sticknology.jani.data;

import java.util.ArrayList;

public class Run {

    private final ArrayList<Interval> mIntervals;
    private final String mDescriptor;
    private final Types.RunTypeEnum mType;

    public Run(ArrayList<Interval> intervals, String descriptor, Types.RunTypeEnum type){

        mIntervals = intervals;
        mType = type;
        mDescriptor = descriptor;
    }

    public ArrayList<Run> createRunList(ArrayList<Interval>[] intervals, String[] descriptors,
                                             Types.RunTypeEnum[] types){

        ArrayList<Run> runArrayList = new ArrayList<Run>();

        for(int i = 0; i < descriptors.length; i++){
            runArrayList.add(new Run(intervals[i], descriptors[i], types[i]));
        }

        return runArrayList;
    }

    public ArrayList<Interval> getRunIntervals(){
        return mIntervals;
    }

    public String getRunType(){
        return mDescriptor;
    }

    public Types.RunTypeEnum getRunDescriptor(){
        return mType;
    }
}
