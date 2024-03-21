package com.ashwinbhatt.systemdesign.sortloggingsystem.sortalgorithms;

import com.ashwinbhatt.systemdesign.sortloggingsystem.comparator.IComparator;
import com.ashwinbhatt.systemdesign.sortloggingsystem.exceptions.SortAlgorithmException;
import lombok.extern.slf4j.Slf4j;

import static com.ashwinbhatt.systemdesign.sortloggingsystem.sortalgorithms.SortAlgorithmEnum.*;

@Slf4j
public class SortAlgorithmProvider {

    public static <T> ISortAlgorithm provideSortAlgorithm(IComparator<T> comparator, SortAlgorithmEnum sortAlgorithmEnum) throws SortAlgorithmException {
        switch (sortAlgorithmEnum) {
            case BUBBLE: {
                log.info("Initializing bubble sort algorithm class");
                BubbleSortAlgorithm<T> bubbleSortAlgorithm = new BubbleSortAlgorithm<>(BUBBLE, comparator);
                return bubbleSortAlgorithm;
            } case SELECTION_SORT: {
                log.info("Initializing selection sort algorithm class");
                SelectionSortAlgorithm<T> selectionSortAlgorithm = new SelectionSortAlgorithm<>(SELECTION_SORT, comparator);
                return selectionSortAlgorithm;
            } case QUICK_SORT: {
                log.info("Initializing quick sort algorithm class");
                QuickSortAlgorithm<T> quickSortAlgorithm = new QuickSortAlgorithm<>(QUICK_SORT, comparator);
                return quickSortAlgorithm;
            } default: {
                log.error("No such sorting algorithm as "+ sortAlgorithmEnum);
                throw new SortAlgorithmException("No such sorting algorithm as "+ sortAlgorithmEnum);
            }
        }
    }
}
