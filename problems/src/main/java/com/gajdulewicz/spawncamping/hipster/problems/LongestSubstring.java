package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LongestSubstring {

    public static void main(String[] args) {
        Integer limit = 400;
        if (args.length > 0) {
            limit = Integer.parseInt(args[0]);
        }
        String a = longString(limit) + "ggg" + longString(limit);
        String b = longString(limit) + "ggg" + longString(limit);
        long start = System.currentTimeMillis();
        Set<String> sub = longestSubstringDynamic(a, b);
        long end = System.currentTimeMillis();
        System.out.println("Found in " + (end - start) + " " + sub);
    }

    private static String longString(Integer limit) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < limit; i++) {
            res.append(UUID.randomUUID());
        }
        return res.toString();
    }

    private static Set<String> longestSubstringDynamic(String a, String b) {
        int[][] len = new int[2][b.length()];
        int maxLen = 0;
        Set<String> maxes = new HashSet<>();
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    if (i == 0 || j == 0) {
                        len[1][j] = 1;
                    } else {
                        len[1][j] = len[0][j - 1] + 1;
                        if (len[1][j] > maxLen) {
                            maxLen = len[1][j];
                            maxes.clear();
                        }
                        if (len[1][j] == maxLen && maxLen <= i + 1) {
                            maxes.add(a.substring(i - maxLen + 1, i + 1));
                        }
                    }
                }
            }
            System.arraycopy(len[1], 0, len[0], 0, len[0].length);
            Arrays.fill(len[1], 0);
        }
        return maxes;
    }
}
