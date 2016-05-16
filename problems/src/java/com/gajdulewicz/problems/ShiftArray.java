package com.gajdulewicz.problems;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by rafal on 30/05/16.
 */
public class ShiftArray {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        shift(IntStream.range(1, 100000000).toArray(), 10000);
        long end = System.currentTimeMillis();
        System.out.println("shift took " + (end - start));
        start = System.currentTimeMillis();
        shiftSmart(IntStream.range(1, 100000000).toArray(), 1000);
        end = System.currentTimeMillis();
        System.out.println("shiftSmart took " + (end - start));
    }

    private static int[] shift(int[] ints, int offset) {
        final int length = ints.length;
        int[] res = new int[length];
        System.arraycopy(ints, length - offset, res, 0, offset);
        System.arraycopy(ints, 0, res, 0, length - offset);
        return res;
    }

    private static int[] shiftSmart(int[] ints, int offset) {
        final int length = ints.length;
        int[] tmp = new int[offset];
        System.arraycopy(ints, length - 1 - offset, tmp, 0, offset);
        for (int i = offset; i + offset < length; i++) {
            ints[i] = ints[i - offset];
        }
        System.arraycopy(tmp, 0, ints, 0, offset);
        return ints;
    }
}
