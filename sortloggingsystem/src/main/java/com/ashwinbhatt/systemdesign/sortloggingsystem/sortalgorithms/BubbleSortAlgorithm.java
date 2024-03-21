package com.ashwinbhatt.systemdesign.sortloggingsystem.sortalgorithms;

import com.ashwinbhatt.systemdesign.sortloggingsystem.comparator.IComparator;
import com.ashwinbhatt.systemdesign.sortloggingsystem.exceptions.SortAlgorithmException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BubbleSortAlgorithm<T> extends ISortAlgorithm<T> {

    protected BubbleSortAlgorithm(SortAlgorithmEnum sortAlgorithmEnum, IComparator<T> comparator) {
        super(sortAlgorithmEnum, comparator);
    }

    public void sort(List<T> list) throws SortAlgorithmException {
        if(list == null) {
            log.error("Provided list is null!");
            throw new SortAlgorithmException("Provided list is empty");
        }
        int sz = list.size();
        if(sz == 1) {
            log.warn("Provided list is empty!");
            return;
        }
        for(int i=sz-2;i>-1;i--) {
            for(int start=0;start<=i;start++) {
                log.debug(String.format("Comparing element at <%s> with <%s>.", i, start));
                if(comparator.compare(list.get(start), list.get(start+1)) > 0) {
                    swap(list, start, start+1);
                }
            }
        }
    }

}
