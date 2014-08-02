package com.gajdulewicz.algo.sort;

import java.util.*;

/**
 * Created by rafal on 01/08/14.
 */
public class QuickSorter<T extends Comparable> implements Sorter<T> {

    Random r = new Random();
    private T[] data;

    @Override
    public List<T> sort(List<T> input) {
        data = (T[]) new Comparable[input.size()];
        for (int i = 0; i < input.size(); i++) {
            data[i] = input.get(i);
        }
        quicksort(0, data.length - 1);
        return Arrays.asList(data);
    }

    private void quicksort(int low, int high) {
        int i = low, j = high;
        T pivot = data[low + (high - low) / 2];
        while (i <= j) {
            while (data[i].compareTo(pivot) < 0) {
                i++;
            }
            while (data[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private void exchange(int i, int j) {
        T tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    private static int TEST_SIZE = 1000000;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(TEST_SIZE);
        Random r = new Random();
        for (int i = 0; i < TEST_SIZE; i++) {
            list.add(r.nextInt(10 * TEST_SIZE));
        }
        List<Integer> qs = null, cs = null, ms = null, hs = null;
        for (int i = 0; i < 10; i++) {


            try (com.gajdulewicz.problems.Timer t = new com.gajdulewicz.problems.Timer("Collections")) {
                cs = new ArrayList<>(list);
                Collections.sort(cs);
            }
            try (com.gajdulewicz.problems.Timer t = new com.gajdulewicz.problems.Timer("Quick")) {
                qs = new QuickSorter<Integer>().sort(new ArrayList<Integer>(list));
            }
            try (com.gajdulewicz.problems.Timer t = new com.gajdulewicz.problems.Timer("Merge")) {
                ms = new MergeSorter<Integer>().sort(new ArrayList<>(list));
            }
            try (com.gajdulewicz.problems.Timer t = new com.gajdulewicz.problems.Timer("Heap")) {
                hs = new HeapSorter<Integer>().sort(new ArrayList<>(list));
            }
        }
    }
}
