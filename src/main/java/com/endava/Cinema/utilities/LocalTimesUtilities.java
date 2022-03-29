package com.endava.Cinema.utilities;

import java.time.LocalTime;

public class LocalTimesUtilities {
    public static String sumLocalTimes(LocalTime localTime, LocalTime localTime2){
        Integer hour = localTime.getHour() + localTime2.getHour();
        Integer minutes = localTime.getMinute() + localTime2.getMinute();
        if (minutes >= 60){
            minutes -=60;
            hour +=1;
        }
        if (minutes<10 && hour >=10){
            System.out.println("aca");
            return hour+":0"+minutes;
        }
        else if(minutes<10 && hour<10){
            return "0"+hour+":0"+minutes;
        }
        else if(minutes>=10 && hour<10){
            return "0"+hour+":"+minutes;
        }
        return hour +":"+ minutes;
    }
}

