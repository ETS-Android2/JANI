package com.sticknology.jani.ui.create.planCreation;

import com.sticknology.jani.data.ListCreation;
import com.sticknology.jani.data.TrainingDay;
import com.sticknology.jani.data.TrainingWeek;

import java.util.ArrayList;

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
}
