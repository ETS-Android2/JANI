package com.sticknology.jani.dataProcessing;

import com.sticknology.jani.data.Interval;

public class InterpretInterval {

    public String getIntervalString(Interval interval){

        return interval.getIntervalDistance() + "&<&" + interval.getIntervalTime() + "&<&"
                + interval.getIntervalPace() + "&<&" + interval.getIntervalEffort();
    }

    public Interval getIntervalObject(String input){

        String[] comp = input.split("(&<&)");

        return new Interval(comp[0], comp[1], comp[2], comp[3]);
    }
}
