package com.ashwinbhatt.systemdesign.sortloggingsystem.sortalgorithms;

import com.ashwinbhatt.systemdesign.sortloggingsystem.comparator.IComparator;
import com.ashwinbhatt.systemdesign.sortloggingsystem.exceptions.SortAlgorithmException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class QuickSortAlgorithm<T> extends ISortAlgorithm<T>{

    protected QuickSortAlgorithm(SortAlgorithmEnum sortAlgorithmEnum, IComparator comparator) {
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
        sort(list, 0, sz-1);
    }

    public void sort(List<T> list, int l, int r) {
        if(l>=r) {
            return;
        }
        T piv = list.get(r);
        log.debug(String.format("Pivot value <%s>", piv));
        int j=l-1;
        for(int i=l;i<=r;i++) {
            // curr val <= piv
            log.debug(String.format("Comparing value at position <%s> with pivot", i));
            if(comparator.compare(list.get(i), piv) <= 0) {
                swap(list, ++j, i);
            }
        }
        sort(list, l, j-1);
        sort(list, j+1, r);
    }
}
