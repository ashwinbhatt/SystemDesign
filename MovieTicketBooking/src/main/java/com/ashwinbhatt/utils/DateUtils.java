package com.ashwinbhatt.utils;

import java.util.Date;

public class DateUtils {

    public static boolean rangeOverlaps(Date start1, Date end1, Date start2, Date end2){
        if(start1.compareTo(start2)<=0 && end1.compareTo(start2)>=0){
            return true;
        }
        if(start1.compareTo(end2)<=0 && end1.compareTo(start2)>=0){
            return true;
        }
        if(start1.compareTo(start2)>=0 && end1.compareTo(end2)<=0){
            return true;
        }
        return false;
    }
}
