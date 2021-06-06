package com.sticknology.jani.ui.create.planCreation;

import com.sticknology.jani.data.ListCreation;
import com.sticknology.jani.data.TrainingDay;
import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.data.TrainingWeek;
import com.sticknology.jani.data.Workout;

import java.util.ArrayList;
import java.util.List;

public class WByWDataHandler {

    /*
    Essentially a workaround to "fix" the training week object before attempting to cast it back to a string
    Fixes the object/ensures it is string writeable and then returns a string version of the fixed object
    This ensures that in the process of editing the week, no interior list positions were left without a
    placeholder which could cause issues in string casting
    */
    public TrainingWeek fixTrainingWeek(TrainingWeek trainingWeekObject){

        ArrayList<TrainingDay> trainingDayList = trainingWeekObject.getTrainingWeekDays();
        for(int i = 0; i < 7; i++){
            if(trainingDayList.get(i).getTrainingDayWorkouts().size() == 0){
                trainingDayList.get(i).getTrainingDayWorkouts().add(new ListCreation().createEmptyWorkoutList().get(0));
            }
        }

        return trainingWeekObject;
    }

    public void printFilledPlan(TrainingPlan trainingPlan){
        System.out.println("BEGIN FILLED PLAN PRINTOUT: ");
        for(int i = 0; i < trainingPlan.getTrainingPlanWeeks().size(); i++){
            for(int u = 0; u < 7; u++){
                ArrayList<Workout> workouts = trainingPlan.getTrainingDay(i, u).getTrainingDayWorkouts();
                if(!workouts.get(0).getWorkoutName().equals(":;:")){
                    System.out.println("WORKOUT FOUND: " + workouts.get(0).getWorkoutName() + " at week " + i + " and day " + u);
                }
            }
        }
    }

    public void printFilledWeek(TrainingWeek trainingWeek){
        System.out.println("BEGIN FILLED WEEK PRINTOUT");
        for(int u = 0; u < 7; u++){
            ArrayList<Workout> workouts = trainingWeek.getTrainingWeekDays().get(u).getTrainingDayWorkouts();
            if(!workouts.get(0).getWorkoutName().equals(":;:")){
                System.out.println("WORKOUT FOUND: " + workouts.get(0).getWorkoutName()  + " at day " + u);
            }
        }
    }
}
