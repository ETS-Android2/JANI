package com.sticknology.jani.dataProcessing;

import android.content.Context;

import com.sticknology.jani.data.Interval;
import com.sticknology.jani.data.Run;

import java.util.ArrayList;

public class InterpretRun {

    public String getStringRun(Run run){

        //Add Base Run Information
        String runBuild = run.getRunName() + "&@&" + run.getRunDescriptor() + "&@&"
                + run.getRunType() + "&@&";

        //Add Interval List to String
        InterpretInterval interpretInterval = new InterpretInterval();
        ArrayList<Interval> intervalList = run.getRunIntervals();
        for(int i = 0; i < intervalList.size(); i++){
            runBuild += interpretInterval.getIntervalString(intervalList.get(i));
            if(i < intervalList.size()-1){
                runBuild += "&>&";
            }
        }

        return runBuild;
    }

    public Run getObjectRun(String runString){

        String[] runArray = runString.split("(&@&)");

        //Interpret Interval String
        String[] intervalArray = runArray[3].split("(&>&)");
        InterpretInterval interpretInterval = new InterpretInterval();
        ArrayList<Interval> intervalArrayList = new ArrayList<>();
        for(int i = 0; i < intervalArray.length; i++){
            intervalArrayList.add(interpretInterval.getIntervalObject(intervalArray[i]));
        }

        return new Run(intervalArrayList, runArray[0], runArray[1], runArray[2]);
    }

    public ArrayList<Run> getRunTemplates(Context context){

        String tps = new StandardReadWrite().readFileToString("run_templates.txt", context);

        String[] runTemplates = tps.split("\n");
        ArrayList<Run> runObjects = new ArrayList<>();

        //i starts at 2 because there are two initial blank lines in the file
        for(int i = 2; i < runTemplates.length; i++){
            runObjects.add(getObjectRun(runTemplates[i]));
        }

        return runObjects;
    }
}
