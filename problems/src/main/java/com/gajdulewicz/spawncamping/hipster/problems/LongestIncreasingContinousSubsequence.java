package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.Arrays;

public class LongestIncreasingContinousSubsequence {

    public static void main(String[] args) {
        int[] in = new int[]{1, 2, 3, 3, 2, 4, 6, 7};
        int[] max = longest(in);
        System.out.println(Arrays.toString(max));
    }

    private static int[] longest(int[] in) {
        int seqStart = 0;
        int seqLen = 1;
        int maxStart = 0;
        int maxLen = 1;
        for (int i = 1; i < in.length; i++) {
            if (in[i] >= in[i - 1]) {
                seqLen++;
                if (seqLen > maxLen) {
                    maxStart = seqStart;
                    maxLen = seqLen;
                }
            } else {
                seqStart = i;
                seqLen = 1;
            }
        }
        return Arrays.copyOfRange(in, maxStart, maxStart + maxLen);
    }
}
