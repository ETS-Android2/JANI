package com.sticknology.jani.data2;

import static com.sticknology.jani.data2.Distance.defaultUnit;

public class MyOperations {

    //Convert from comparable time form back to object
    public MyTime getTimeObject(int duration){
        int hours = duration / 3600;
        int minutes = duration % 3600;
        int seconds = duration % 60;
        return new MyTime(hours, minutes, seconds);
    }

    //Get MyTime object from string
    public MyTime getTimeObjectString(String input){
        //Gets time portion and then splits on colon
        String[] colonSplit = input.split(" ")[0].split(":");
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if(colonSplit.length == 2){
            minutes = Integer.parseInt(colonSplit[0]);
            seconds = Integer.parseInt(colonSplit[1]);
        } else if(colonSplit.length == 3){
            hours = Integer.parseInt(colonSplit[0]);
            minutes = Integer.parseInt(colonSplit[1]);
            seconds = Integer.parseInt(colonSplit[2]);
        }
        return new MyTime(hours, minutes, seconds);
    }

    //Get missing item from trio of distance, pace, and time
    public Distance getMissingDistance(MyTime pace, MyTime time){
        int div = time.getTimeSeconds() / pace.getTimeSeconds();
        return new Distance(div, defaultUnit);
    }

    public MyTime getMissingTime(MyTime pace, Distance distance){
        int paceSeconds = pace.getTimeSeconds();
        int result = ((int) Math.round(paceSeconds * distance.getDoubleDistance()));
        return getTimeObject(result);
    }

    public MyTime getMissingPace(MyTime time, Distance distance){
        int timeSeconds = time.getTimeSeconds();
        int div = (int) Math.round(timeSeconds / distance.getDoubleDistance());
        return getTimeObject(div);
    }
}
