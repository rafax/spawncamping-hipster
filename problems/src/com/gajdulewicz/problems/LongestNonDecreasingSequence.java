package com.gajdulewicz.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by rafal on 29/07/14.
 */
public class LongestNonDecreasingSequence {

    public static List<Integer> calculate(List<Integer> input) {
        int max = 0, lastSequenceIndex = -1;
        List<Integer> lengths = new ArrayList<>();
        List<Integer> prevIndices = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            lengths.add(1);
            prevIndices.add(-1);
        }
        for (int i = 1; i < input.size(); i++) {
            int length = 0, prevIndex = -1;
            for (int j = 0; j < i; j++) {

                if (input.get(j) <= input.get(i) && lengths.get(j) + 1 > length) {
                    length = lengths.get(j) + 1;
                    prevIndex = j;
                }
            }
            lengths.add(i, length);
            prevIndices.add(i, prevIndex);
            if (lengths.get(i) > max) {
                max = length;
                lastSequenceIndex = i;
            }
        }
        List<Integer> result = new ArrayList<>();
        while (lastSequenceIndex != -1) {
            result.add(input.get(lastSequenceIndex));
            lastSequenceIndex = prevIndices.get(lastSequenceIndex);
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(LongestNonDecreasingSequence.calculate(Arrays.asList(1, 2, 3, 6, 5, 4, 5)));

    }


}
