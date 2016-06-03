package com.gajdulewicz.spawncamping.hipster.algo.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by rafal on 27/07/14.
 */
public class CountingSorter {

    public static int[] sort(int[] data, int max) {
        int[] result = new int[data.length];
        int[] counts = new int[max + 1];
        for (int i = 0; i < data.length; i++) {
            counts[data[i]]++;
        }
        System.out.println(Arrays.toString(counts));
        int total = 0;
        for (int i = 0; i < counts.length; i++) {
            int oldCount = counts[i];
            counts[i] = total;
            total += oldCount;
        }
        System.out.println(Arrays.toString(counts));
        for (int i = 0; i < data.length; i++) {
            result[counts[data[i]]] = data[i];
            counts[data[i]]++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(CountingSorter.sort(new int[]{5, 1, 2, 8, 1, 4}, 8)));
        System.out.println(Arrays.toString(CountingSorter.sort(randomListOfCappedBy(1000000), 1000000)));
    }

    private static int[] randomListOfCappedBy(int max) {
        List<Integer> result = IntStream.range(1, max)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(result);
        return result.stream().mapToInt(i -> i).toArray();
    }
}
