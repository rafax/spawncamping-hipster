package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.Arrays;

/**
 * Created by rafal on 03/08/14.
 */
public class StairsMultiplication {


    public static void main(String[] args) {
        System.out.println(Arrays.toString(solveN2(new int[]{2, 3, 4, 5})));
        System.out.println(Arrays.toString(solve(new int[]{2, 3, 4, 5})));

    }

    private static int[] solve(int[] input) {
        int[] res = new int[input.length];
        Arrays.fill(res, 1);
        for (int i = 1; i < input.length; i++) {
            res[i] *= input[i - 1] * res[i - 1];
        }
        System.out.println(Arrays.toString(res));
        int[] tmp = new int[input.length];
        Arrays.fill(tmp, 1);
        for (int i = input.length - 2; i >= 0; i--) {
            tmp[i] = input[i + 1] * tmp[i + 1];
        }
        for (int i = input.length - 2; i >= 0; i--) {
            res[i] *= tmp[i];
        }
        return res;
    }

    private static int[] solveN2(int[] input) {
        int[] res = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            res[i] = 1;
            for (int j = 0; j < input.length; j++) {
                if (i != j) {
                    res[i] *= input[j];
                }
            }
        }
        return res;
    }
}
