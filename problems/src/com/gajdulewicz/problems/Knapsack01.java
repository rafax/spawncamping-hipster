package com.gajdulewicz.problems;

import java.util.*;

/**
 * Created by rafal on 30/07/2014.
 */
public class Knapsack01 {

    private final List<Integer> itemValues;
    private final List<Integer> itemCosts;
    private final Map<Integer, Integer> maxValueForWeight;

    public Knapsack01(List<Integer> value, List<Integer> cost) {
        this.itemValues = value;
        this.itemCosts = cost;
        maxValueForWeight = new HashMap<>();
    }

    public int maxValueForLimit(int w) {
        return calcValueForLimit(w, new ArrayList<>(itemValues), new ArrayList<>(itemCosts));
    }

    private int calcValueForLimit(int w, ArrayList<Integer> values, ArrayList<Integer> costs) {
        if (w <= 0) {
            return 0;
        }
        if (maxValueForWeight.containsKey(w)) {
            return maxValueForWeight.get(w);
        }
        int oneLess = calcValueForLimit(w - 1, values, costs);
        int maxWithOneSwapped = Integer.MIN_VALUE, swappedIndex = -1;
        for (int i = 0; i < values.size(); i++) {
            if (w - costs.get(i) >= 0) {
                int candidate = calcValueForLimit(w - costs.get(i), values, costs) + values.get(i);
                if (candidate > maxWithOneSwapped) {
                    maxWithOneSwapped = candidate;
                    swappedIndex = i;
                }
            }
        }
        if (maxWithOneSwapped > oneLess) {
            values.remove(swappedIndex);
            costs.remove(swappedIndex);
        }
        maxValueForWeight.put(w, Math.max(oneLess, maxWithOneSwapped));
        return maxValueForWeight.get(w);
    }

    public static void main(String[] args) {
        System.out.println(new Knapsack01(Arrays.asList(3, 2, 1, 8), Arrays.asList(1, 1, 1, 4)).maxValueForLimit(3));
    }
}
