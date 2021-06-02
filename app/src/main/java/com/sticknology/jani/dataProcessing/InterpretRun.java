package com.sticknology.jani.dataProcessing;

import com.sticknology.jani.data.Interval;
import com.sticknology.jani.data.ListCreation;
import com.sticknology.jani.data.Run;

import java.util.ArrayList;

public class InterpretRun {

    public String getStringRun(Run run){

        String name = run.getRunName();
        String description = run.getRunDescriptor();
        String type = run.getRunType();
        ArrayList<Interval> intervalList = run.getRunIntervals();

        InterpretInterval interpretInterval = new InterpretInterval();

        String build = name + "|" + description + "|" + type + "\n";

        for(int i = 0; i < intervalList.size(); i++){

            build += interpretInterval.getIntervalString(intervalList.get(i)) + "\n";
        }

        return build;
    }

    public Run getObjectRun(String runString){

        String[] buildArray = runString.split("\n");

        String[] identifier = buildArray[0].split("|");
        String name = identifier[0];
        String descriptor = identifier[1];
        String type = identifier[2];

        String[] distanceArray = new String[buildArray.length-1];
        String[] paceArray = new String[buildArray.length-1];
        String[] timeArray = new String[buildArray.length-1];
        String[] effortArray = new String[buildArray.length-1];

        for (int i = 1; i < buildArray.length; i++) {
            String[] intervalArray = buildArray[i].split("|");
            distanceArray[i] = intervalArray[0];
            paceArray[i] = intervalArray[1];
            timeArray[i] = intervalArray[2];
            effortArray[i] = intervalArray[3];
        }

        ListCreation listCreation = new ListCreation();
        ArrayList<Interval> intervalArrayList = listCreation.createIntervalList(distanceArray,
                paceArray, timeArray, effortArray);

        return new Run(intervalArrayList, name, descriptor, type);
    }
}
