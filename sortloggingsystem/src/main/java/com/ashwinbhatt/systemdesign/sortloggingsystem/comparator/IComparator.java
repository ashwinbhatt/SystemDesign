package com.ashwinbhatt.systemdesign.sortloggingsystem.comparator;

@FunctionalInterface
public interface IComparator<T> {

    public int compare(T a, T b);
}
