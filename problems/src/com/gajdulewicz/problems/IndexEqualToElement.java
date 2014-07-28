package com.gajdulewicz.problems;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by rafal on 28/07/14.
 */
public class IndexEqualToElement {

    /* Returns i such that input[i] = i or -1 if no such index exists. */
    public static int find(int[] input) {
        for (int i = 0; i < input.length; i++) {
            if (i == input[i]) {
                return i;
            }
        }
        return -1;
    }

    /* Returns i such that input[i] = i or -1 if no such index exists. */
    public static int findSorted(int[] input) {
        for (int i = 0; i < input.length; ) {
            if (i == input[i]) {
                return i;
            } else {
                i = input[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int cap = 100000000;
        int[] input = Stream.concat(Stream.concat(IntStream.range(1, cap).boxed(), IntStream.of(cap - 1).boxed()), IntStream.range(cap * 2, cap * 3).boxed()).mapToInt(x -> x).toArray();
        int normal, sorted;
        try (Timer n = new Timer("Normal")) {
            normal = IndexEqualToElement.find(input);
        }
        try (Timer s = new Timer("Sorted")) {
            sorted = IndexEqualToElement.find(input);
        }
        System.out.println(normal + " vs " + sorted);
    }
}
