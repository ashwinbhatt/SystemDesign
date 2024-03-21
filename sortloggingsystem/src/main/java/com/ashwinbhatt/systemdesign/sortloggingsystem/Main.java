package com.ashwinbhatt.systemdesign.sortloggingsystem;

import com.ashwinbhatt.systemdesign.sortloggingsystem.comparator.IComparator;
import com.ashwinbhatt.systemdesign.sortloggingsystem.comparator.IntegerComparator;
import com.ashwinbhatt.systemdesign.sortloggingsystem.sortalgorithms.SortAlgorithmEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args) {
        try {
            IComparator<Integer> integerComparator = new IntegerComparator();

            List<Integer> arr1 = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1);
            List<Integer> arr2 = Arrays.asList();
            List<Integer> arr3 = Arrays.asList(1);
            List<Integer> arr4 = Arrays.asList(2, 1);


            log.info("Actual array: " + arr1);
            CollectionUtils.sort(arr1, integerComparator, SortAlgorithmEnum.QUICK_SORT);
            log.info("Sorted array: " + arr1);
//
//            log.info("Actual array: " + arr2);
//            CollectionUtils.sort(arr2, integerComparator, SortAlgorithmEnum.BUBBLE);
//            log.info("Sorted array: " + arr2);
//
//            log.info("Actual array: " + arr3);
//            CollectionUtils.sort(arr3, integerComparator, SortAlgorithmEnum.BUBBLE);
//            log.info("Sorted array: " + arr3);
//
//            log.info("Actual array: " + arr4);
//            CollectionUtils.sort(arr4, integerComparator, SortAlgorithmEnum.BUBBLE);
//            log.info("Sorted array: " + arr4);
        } catch (Exception e) {

        }


    }
}
