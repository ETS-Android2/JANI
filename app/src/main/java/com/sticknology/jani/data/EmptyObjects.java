package com.sticknology.jani.data;

import java.util.ArrayList;

public class EmptyObjects {

    //Create Empty Objects and Lists to be used in creating default states of each object

    public ArrayList<Interval> createEmptyInterval(){

        ArrayList<Interval> intervalArrayList = new ArrayList<Interval>();
        intervalArrayList.add(new Interval(":;:", " ", " ", " "));

        return intervalArrayList;
    }

    public ArrayList<Run> createEmptyRunList(){

        ArrayList<Run> runArrayList = new ArrayList<Run>();
        runArrayList.add(new Run(createEmptyInterval(), ":;:", " ", " "));

        return runArrayList;
    }

    public ArrayList<Workout> createEmptyWorkoutList(){

        ArrayList<Workout> workoutArrayList = new ArrayList<Workout>();
        workoutArrayList.add(new Workout(":;:", " ", " "));

        return workoutArrayList;
    }

    public ArrayList<TrainingDay> createEmptyTrainingDayList(){

        ArrayList<TrainingDay> trainingDayArrayList = new ArrayList<TrainingDay>();
        trainingDayArrayList.add(new TrainingDay(createEmptyRunList(), createEmptyWorkoutList(),
                ":;:", " "));

        return trainingDayArrayList;
    }

    public TrainingWeek createEmptyTrainingWeek(){

        ArrayList<TrainingDay> tDayList = createEmptyTrainingDayList();

        for(int i = 0; i < 6; i++){
            ArrayList<TrainingDay> additionalDay = createEmptyTrainingDayList();
            tDayList.addAll(additionalDay);
        }

        TrainingWeek tWeek = new TrainingWeek(tDayList, ":;:", " ", " ");

        return tWeek;
    }

    public ArrayList<TrainingWeek> createEmptyTrainingWeekList(){

        ArrayList<TrainingWeek> trainingWeekArrayList = new ArrayList<TrainingWeek>();
        trainingWeekArrayList.add(createEmptyTrainingWeek());

        return trainingWeekArrayList;
    }

    public TrainingPlan createEmptyTrainingPlan(){

        TrainingPlan trainingPlan = new TrainingPlan(createEmptyTrainingWeekList(), ":;:",
                " ", " ", " ", " ");

        return trainingPlan;
    }
}
