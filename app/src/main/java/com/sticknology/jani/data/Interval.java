package com.sticknology.jani.data;

public class Interval {

    //Component Variables
    private String mDistance;
    private String mPace;
    private String mTime;
    private String mEffort;

    //Construction Method
    public Interval(String distance, String pace, String time, String effort){

        mDistance = distance;
        mPace = pace;
        mTime = time;
        mEffort = effort;
    }

    //Getters and Setters Below Here
    public String getIntervalDistance(){return mDistance;}

    public void setIntervalDistance(String distance){mDistance = distance;}

    public String getIntervalPace(){return mPace;}

    public void setIntervalPace(String pace){mPace = pace;}

    public String getIntervalTime(){return mTime;}

    public void setIntervalTime(String time){mTime = time;}

    public String getIntervalEffort(){return mEffort;}

    public void setIntervalEffort(String effort){mEffort = effort;}
}
