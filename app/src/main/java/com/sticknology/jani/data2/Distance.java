package com.sticknology.jani.data2;

public class Distance {

    public enum DUnits {M, MILES, KM}
    public static DUnits defaultUnit = DUnits.MILES;

    private double mDDouble;
    private DUnits mDUnit;

    //Object creation method
    public Distance(double unitDistance, DUnits distanceUnit){

        mDDouble = unitDistance;
        mDUnit = distanceUnit;
    }

    //Convert distance between units
    public void unitConversion(DUnits desiredUnit){

        if (mDUnit == DUnits.KM && desiredUnit == DUnits.M){
            mDDouble = mDDouble * 1000;
        }
        else if (mDUnit == DUnits.KM && desiredUnit == DUnits.MILES){
            mDDouble = (mDDouble / 1.6 * 100) / 100.0;
        }
        else if (mDUnit == DUnits.M && desiredUnit == DUnits.KM){
            mDDouble = Math.round(mDDouble / 10) / 100.0;
        }
        else if (mDUnit == DUnits.M && desiredUnit == DUnits.MILES){
            mDDouble = Math.round(mDDouble / 16) / 100.0;
        }
        else if (mDUnit == DUnits.MILES && desiredUnit == DUnits.M){
            mDDouble = mDDouble * 1600;
        }
        else if (mDUnit == DUnits.MILES && desiredUnit == DUnits.KM){
            mDDouble = mDDouble * 1.6;
        }
        mDUnit = desiredUnit;
    }

    //Getters and Setters only below here
    public double getDoubleDistance(){return mDDouble;}

    public void setDoubleDistance(double distance){mDDouble = distance;}

    public DUnits getDistanceUnit(){return mDUnit;}

    public void setDistanceUnit(DUnits distanceUnit){mDUnit = distanceUnit;}

    //Returns proper string to display along with distance based on enum
    public String getDistanceUnitString(DUnits distanceUnit){
        if (distanceUnit == DUnits.KM) return "km";
        else if (distanceUnit == DUnits.MILES) return "miles";
        else if (distanceUnit == DUnits.M) return "m";
        else return "";
    }
}
