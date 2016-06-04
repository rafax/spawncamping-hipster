package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by rafal on 04/08/14.
 */
public class SumToM {

    static int[] pairThatSumsToMQuadratic(int[] input, int m) {

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (i != j) {
                    if (input[i] == m - input[j]) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

    static int[] pairThatSumsToMNlogN(int[] input, int m) {
        Arrays.sort(input);
        for (int i = 0; i < input.length; i++) {
            int candidateInd = Arrays.binarySearch(input, m - input[i]);
            if (input[candidateInd] + input[i] == m) {
                if (candidateInd != i) {
                    return new int[]{i, candidateInd};
                } else {
                    candidateInd = Arrays.binarySearch(input, candidateInd + 1, input.length, m - input[i]);
                    if (input[candidateInd] + input[i] == m) {
                        return new int[]{i, candidateInd};
                    }
                }
            }
        }
        return null;
    }

    static int[] pairThatSumsToMLinear(int[] input, int m) {
        HashMap<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            if (seen.containsKey(m - input[i])) {
                return new int[]{seen.get(m - input[i]), i};
            } else {
                seen.put(input[i], i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] input = new int[10000000];
        Random r = new Random();
        for (int i = 0; i < input.length; i++) {
            input[i] = r.nextInt(1000);
        }
        int[] q, n, l;
        try (Timer t = new Timer("Quadratic")) {
            q = pairThatSumsToMQuadratic(input, 123);
        }

        try (Timer t = new Timer("linear")) {
            l = pairThatSumsToMLinear(input, 123);
        }
        try (Timer t = new Timer("nlogn")) {
            n = pairThatSumsToMNlogN(input, 123);
        }
        System.out.println(Arrays.toString(q) + ": " + input[q[0]] + " + " + input[q[1]]);
        System.out.println(Arrays.toString(n) + ": " + input[n[0]] + " + " + input[n[1]]);
        System.out.println(Arrays.toString(l) + ": " + input[l[0]] + " + " + input[l[1]]);
    }
}
