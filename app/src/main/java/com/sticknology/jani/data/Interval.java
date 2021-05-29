package com.sticknology.jani.data;

import java.util.ArrayList;

public class Interval {

    private final Float mDistance;
    private final String mPace;
    private final String mTime;
    private final Types.IntervalEffortEnum mEffort;

    public Interval(Float distance, String pace, String time, Types.IntervalEffortEnum effort){

        mDistance = distance;
        mPace = pace;
        mTime = time;
        mEffort = effort;
    }

    public ArrayList<Interval> createIntervalList(Float[] distances, String[] paces, String[] times,
                                                  Types.IntervalEffortEnum[] efforts){

        ArrayList<Interval> intervalArrayList = new ArrayList<Interval>();

        for(int i = 0; i < distances.length; i++){
            intervalArrayList.add(new Interval(distances[i], paces[i], times[i], efforts[i]));
        }

        return intervalArrayList;
    }

    public Float getIntervalDistance(){
        return mDistance;
    }

    public String getIntervalPace(){
        return mPace;
    }

    public String getIntervalTime(){
        return mTime;
    }

    public Types.IntervalEffortEnum getIntervalEffort(){
        return mEffort;
    }
}
