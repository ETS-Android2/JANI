package com.sticknology.jani.data;

import java.lang.reflect.Array;
import java.util.ArrayList;

//Includes methods for creating an array list made up of data as while as a method for creating a "default" or clean list
//For each type: Interval, Run, Day, Week, and TrainingPlan
//TODO: Need to make above statement actually correct
public class ListCreation {

    public ArrayList<Interval> createIntervalList(String[] distances, String[] paces, String[] times,
                                                  String[] efforts){
        ArrayList<Interval> intervalArrayList = new ArrayList<Interval>();

        for(int i = 0; i < distances.length; i++){
            intervalArrayList.add(new Interval(distances[i], paces[i], times[i], efforts[i]));
        }

        return intervalArrayList;
    }

    public ArrayList<Interval> createEmptyInterval(){

        ArrayList<Interval> intervalArrayList = new ArrayList<Interval>();

        intervalArrayList.add(new Interval("", "", "", ""));

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

    public ArrayList<Run> createEmptyRunList(){

        ArrayList<Run> runArrayList = new ArrayList<Run>();
        runArrayList.add(new Run(createEmptyInterval(), "", "", ""));
        return runArrayList;
    }

    public ArrayList<Workout> createWorkoutList(String[] name, String[] type, String[] descriptor){
        ArrayList<Workout> workoutArrayList = new ArrayList<Workout>();
        for(int i = 0; i < name.length; i++){
            workoutArrayList.add(new Workout(name[i], type[i], descriptor[i]));
        }
        return workoutArrayList;
    }

    public ArrayList<Workout> createEmptyWorkoutList(){
        ArrayList<Workout> workoutArrayList = new ArrayList<Workout>();
        workoutArrayList.add(new Workout("", "", ""));
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

    public ArrayList<TrainingDay> createEmptyTrainingDayList(){

        ArrayList<TrainingDay> trainingDayArrayList = new ArrayList<TrainingDay>();
        trainingDayArrayList.add(new TrainingDay(createEmptyRunList(), createEmptyWorkoutList(), "", ""));
        return trainingDayArrayList;
    }

    //TODO: Might want to move this someplace else??
    public TrainingWeek createEmptyTrainingWeek(){
        ArrayList<TrainingDay> trainingDayArrayList = createEmptyTrainingDayList();
        for(int i = 0; i < 6; i++){
            ArrayList<TrainingDay> additionalDay = createEmptyTrainingDayList();
            trainingDayArrayList.addAll(additionalDay);
        }
        TrainingWeek trainingWeek = new TrainingWeek(trainingDayArrayList, "", "", "");
        return trainingWeek;
    }

}
