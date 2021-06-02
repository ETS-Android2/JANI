package com.sticknology.jani.data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListCreation {

    public ArrayList<Interval> createIntervalList(String[] distances, String[] paces, String[] times,
                                                  String[] efforts){

        ArrayList<Interval> intervalArrayList = new ArrayList<Interval>();

        for(int i = 0; i < distances.length; i++){
            intervalArrayList.add(new Interval(distances[i], paces[i], times[i], efforts[i]));
        }

        return intervalArrayList;
    }

    public ArrayList<Run> createRunList(ArrayList<Interval>[] intervals, String[] name,
                                        String[] descriptors, String[] types){

        ArrayList<Run> runArrayList = new ArrayList<Run>();

        for(int i = 0; i < name.length; i++){
            runArrayList.add(new Run(intervals[i], name[i], descriptors[i], types[i]));
        }

        return runArrayList;
    }

    public ArrayList<Workout> createWorkoutList(String[] names, String[] types, String[] descriptors) {

        ArrayList<Workout> workoutArrayList = new ArrayList<Workout>();

        for (int i = 0; i < types.length; i++) {
            workoutArrayList.add(new Workout(names[i], types[i], descriptors[i]));
        }

        return workoutArrayList;
    }

    public ArrayList<TrainingDay> createTrainingDayList(ArrayList<Run>[] runs,
                                                        ArrayList<Workout>[] workouts,
                                                        String[] types,
                                                        String[] descriptors){

        ArrayList<TrainingDay> trainingDayArrayList = new ArrayList<TrainingDay>();

        for(int i = 0; i < runs.length; i++){
            trainingDayArrayList.add(new TrainingDay(runs[i], workouts[i], types[i], descriptors[i]));
        }

        return trainingDayArrayList;
    }

}
