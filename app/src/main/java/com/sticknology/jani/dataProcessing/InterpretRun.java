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

        String runBuild = name + "&@&" + description + "&@&" + type;

        String intervalBuild = "";
        InterpretInterval interpretInterval = new InterpretInterval();
        ArrayList<Interval> intervalList = run.getRunIntervals();
        for(int i = 0; i < intervalList.size(); i++){

            intervalBuild += interpretInterval.getIntervalString(intervalList.get(i));
            if(i < intervalList.size()-1){
                intervalBuild += "&>&";
            }
        }

        return runBuild + "&@&" + intervalBuild;
    }

    public Run getObjectRun(String runString){

        String[] runArray = runString.split("(&@&)");

        String name = runArray[0];
        String descriptor = runArray[1];
        String type = runArray[2];

        InterpretInterval interpretInterval = new InterpretInterval();
        ArrayList<Interval> intervalArrayList = new ArrayList<>();
        String[] intervalArray = runArray[3].split("(&>&)");
        for(int i = 0; i < intervalArray.length; i++){
            intervalArrayList.add(interpretInterval.getIntervalObject(intervalArray[i]));
        }

        return new Run(intervalArrayList, name, descriptor, type);
    }
}
