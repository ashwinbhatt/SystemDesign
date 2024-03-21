package com.ashwinbhatt.systemdesign.sortloggingsystem.sortalgorithms;

import com.ashwinbhatt.systemdesign.sortloggingsystem.comparator.IComparator;
import com.ashwinbhatt.systemdesign.sortloggingsystem.exceptions.SortAlgorithmException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SelectionSortAlgorithm<T> extends ISortAlgorithm<T> {

    protected SelectionSortAlgorithm(SortAlgorithmEnum sortAlgorithmEnum, IComparator<T> comparator) {
        super(sortAlgorithmEnum, comparator);
    }

    @Override
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
        for(int i=0;i<sz-1;i++) {
            int minIdx = i;
            for(int j = i+1;j<sz;j++) {
                log.debug(String.format("Comparing element at <%s> with <%s>.", i, j));
                if(comparator.compare(list.get(i), list.get(j)) > 0) {
                    minIdx = j;
                }
            }
            swap(list, i, minIdx);
        }
    }
}
