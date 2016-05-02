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
        values = new ArrayList<>(values);
        costs = new ArrayList<>(costs);
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

    static List<Integer> votes = Arrays.asList(9, 3, 10, 6, 55, 9, 7, 3, 3, 27, 15, 4, 4, 21, 11, 7, 6, 8, 9, 4, 10, 12, 17, 10, 6, 11, 3, 5, 5, 4, 15, 5, 31, 15, 3, 20, 7, 7, 21, 4, 8, 3, 11, 34, 5, 3, 13, 11, 5, 10, 3);

    public static void main(String[] args) {
        System.out.println(new Knapsack01(new ArrayList<Integer>(votes), new ArrayList<Integer>(votes)).maxValueForLimit(239));
    }
}
