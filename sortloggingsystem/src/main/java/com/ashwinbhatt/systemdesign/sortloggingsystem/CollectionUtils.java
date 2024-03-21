package com.ashwinbhatt.systemdesign.sortloggingsystem;

import com.ashwinbhatt.systemdesign.sortloggingsystem.comparator.IComparator;
import com.ashwinbhatt.systemdesign.sortloggingsystem.exceptions.SortAlgorithmException;
import com.ashwinbhatt.systemdesign.sortloggingsystem.sortalgorithms.ISortAlgorithm;
import com.ashwinbhatt.systemdesign.sortloggingsystem.sortalgorithms.SortAlgorithmEnum;
import com.ashwinbhatt.systemdesign.sortloggingsystem.sortalgorithms.SortAlgorithmProvider;

import java.util.List;

public class CollectionUtils {


    public static <T> void sort(List<T> list, IComparator<T> comparator, SortAlgorithmEnum sortAlgorithmEnum) throws SortAlgorithmException {
        ISortAlgorithm sortAlgorithm = SortAlgorithmProvider.provideSortAlgorithm(comparator, sortAlgorithmEnum);
        sortAlgorithm.sort(list);
    }

}
