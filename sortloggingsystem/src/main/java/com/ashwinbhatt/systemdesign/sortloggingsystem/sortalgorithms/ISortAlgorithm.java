package com.ashwinbhatt.systemdesign.sortloggingsystem.sortalgorithms;

import com.ashwinbhatt.systemdesign.sortloggingsystem.comparator.IComparator;
import com.ashwinbhatt.systemdesign.sortloggingsystem.exceptions.SortAlgorithmException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Slf4j
public abstract class ISortAlgorithm<T> {

    private final SortAlgorithmEnum sortAlgorithmEnum;
    protected final IComparator<T> comparator;


    protected ISortAlgorithm(SortAlgorithmEnum sortAlgorithmEnum, IComparator<T> comparator) {
        this.sortAlgorithmEnum = sortAlgorithmEnum;
        this.comparator = comparator;
    }

    public abstract void sort(List<T> list) throws SortAlgorithmException;


    public void swap(List<T> list, int i, int j) {
        log.debug(String.format("Swapping element at position <%s>, <%s>", i, j));
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
