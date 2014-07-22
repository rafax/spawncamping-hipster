package com.gajdulewicz.algo.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rafal on 22/07/14.
 */
public class MergeSorter<T extends Comparable> implements Sorter<T> {

    List<T> mergeSort(List<T> input) {
        if (input.size() <= 1) {
            return input;
        }
        int middleInd = input.size() / 2;
        List<T> a = new ArrayList<>(middleInd), b = new ArrayList<>(middleInd);
        for (int i = 0; i < input.size(); i++) {
            if (i < middleInd) {
                a.add(input.get(i));
            } else {
                b.add(input.get(i));
            }
        }
        List<T> left = mergeSort(a);
        List<T> right = mergeSort(b);
        return merge(left, right);
    }

    @Override
    public List<T> sort(List<T> input) {
        return mergeSort(input);
    }

    @SuppressWarnings("unchecked")
    List<T> merge(List<T> left, List<T> right) {
        List<T> result = new LinkedList<>();
        while (!left.isEmpty() || !right.isEmpty()) {
            if (!left.isEmpty() && !right.isEmpty()) {
                if (left.get(0).compareTo(right.get(0)) < 0) {
                    result.add(left.remove(0));
                } else {
                    result.add(right.remove(0));
                }
            } else if (!left.isEmpty()) {
                result.add(left.remove(0));
            } else if (!right.isEmpty()) {
                result.add(right.remove(0));
            }
        }
        return result;
    }
}
