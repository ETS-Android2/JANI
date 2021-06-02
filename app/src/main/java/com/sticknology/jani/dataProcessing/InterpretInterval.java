package com.sticknology.jani.dataProcessing;

import com.sticknology.jani.data.Interval;

public class InterpretInterval {

    public String getIntervalString(Interval interval){

        String distance = interval.getIntervalDistance();
        String time = interval.getIntervalTime();
        String pace = interval.getIntervalPace();
        String effort = interval.getIntervalEffort();

        return distance + "|" + time + "|" + pace + "|" + effort;
    }

    public Interval getIntervalObject(String input){

        String[] comp = input.split("|");
        String distance = comp[0];
        String time = comp[1];
        String pace = comp[2];
        String effort = comp[3];

        return new Interval(distance, time, pace, effort);
    }
}
