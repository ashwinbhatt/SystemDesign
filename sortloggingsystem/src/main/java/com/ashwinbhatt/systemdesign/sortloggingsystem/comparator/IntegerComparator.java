package com.ashwinbhatt.systemdesign.sortloggingsystem.comparator;

public class IntegerComparator implements IComparator<Integer> {

    public int compare(Integer a, Integer b) {
        return Integer.compare(a, b);
    }

}
