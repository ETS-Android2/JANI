package com.sticknology.jani.data2;

import com.sticknology.jani.data.Workout;

import java.util.ArrayList;

public class Run2 {

    private String mTitle, mNotes, mType, mLocation;
    private MyTime mTimeOfDay;
    private ArrayList<Interval2> mIntervals;
    private ArrayList<Workout> mWorkouts;

    //Object creation method
    public Run2(String title, String notes, String type, String location, MyTime timeOfDay,
                ArrayList<Interval2> intervals, ArrayList<Workout> workouts){

        mTitle = title;
        mNotes = notes;
        mType = type;
        mLocation = location;
        mTimeOfDay = timeOfDay;
        mIntervals = intervals;
        mWorkouts = workouts;
    }

    //Modify intervals and workouts in respective lists
    public void addInterval(Interval2 nextInterval){mIntervals.add(nextInterval);}

    public void removeInterval(int position){mIntervals.remove(position);}

    public void replaceInterval(Interval2 newInterval, int position){
        mIntervals.set(position, newInterval);
    }

    public void addWorkout(Workout workout){mWorkouts.add(workout);}

    public void removeWorkout(int position){mWorkouts.remove(position);}

    public void replaceWorkout(Workout newWorkout, int position){
        mWorkouts.set(position, newWorkout);
    }

    //Get basic run metrics (total distance & time, average pace) from intervals
    public double getDistance(){
        double totalDistance = 0;
        for(int i = 0; i < mIntervals.size(); i++){
            totalDistance += mIntervals.get(i).getDistance().getDoubleDistance();
        }
        return totalDistance;
    }

    public MyTime getTotalTime(){
        int seconds = 0;
        for(int i = 0; i < mIntervals.size(); i++){
            seconds += mIntervals.get(i).getTime().getTimeSeconds();
        }
        return new MyOperations().getTimeObject(seconds);
    }

    public MyTime getAveragePace(){
        int averageSeconds = (int) Math.round(getTotalTime().getTimeSeconds() / getDistance());
        return new MyOperations().getTimeObject(averageSeconds);
    }

    //Get selected effort interval list
    public ArrayList<Interval2> getSpecIntervals(String desiredType){
        ArrayList<Interval2> newIntervals = new ArrayList<>();
        for(int i = 0; i < mIntervals.size(); i++){
           Interval2 currentInterval = mIntervals.get(i);
           if(currentInterval.getEffort().equals(desiredType)) newIntervals.add(currentInterval);
        }
        return newIntervals;
    }


    //Only getters and setters below here
    public String getTitle(){return mTitle;}

    public void setTitle(String title){mTitle = title;}

    public String getNotes(){return mNotes;}

    public void setNotes(String notes){mNotes = notes;}

    public String getType(){return mType;}

    public void setTypes(String type){mType = type;}

    public String getLocation(){return mLocation;}

    public void setLocation(String location){mLocation = location;}

    public MyTime getTimeOfDay(){return mTimeOfDay;}

    public void setTimeOfDay(MyTime time){mTimeOfDay = time;}

    public ArrayList<Interval2> getIntervals(){return mIntervals;}

    public void setIntervals(ArrayList<Interval2> intervals){mIntervals = intervals;}

    public ArrayList<Workout> getWorkouts(){return mWorkouts;}

    public void setWorkouts(ArrayList<Workout> workouts){mWorkouts = workouts;}
}
