package com.sticknology.jani.data2;

public class MyTime {

    private int mHours, mMinutes, mSeconds;

    public MyTime(int hour, int minutes, int seconds){

        mHours = hour;
        mMinutes = minutes;
        mSeconds = seconds;
    }

    //Convert to comparable form
    public int getTimeSeconds(){
        return mHours*3600 + mMinutes*60 + mSeconds;
    }

    //Only getters and setters below here
    public int getHours(){return mHours;}

    public void setHours(int hours){mHours = hours;}

    public int getMinutes(){return mMinutes;}

    public void setMinutes(int minutes){mMinutes = minutes;}

    public int getSeconds(){return mSeconds;}

    public void setSeconds(int seconds){mSeconds = seconds;}
}
