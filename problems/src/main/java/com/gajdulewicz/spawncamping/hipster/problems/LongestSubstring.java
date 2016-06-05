package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.*;
import java.util.stream.IntStream;

public class LongestSubstring {

    public static void main(String[] args) {
        Integer limit = 400;
        if (args.length > 0) {
            limit = Integer.parseInt(args[0]);
        }
        //Set<String> sub = longestSubstringDynamic("abab", "baba");
        String a = longString(limit) + "ggg" + longString(limit);
        String b = longString(limit) + "ggg" + longString(limit);
        Set<String> sub = longestSubstringDynamic(a, b);
        System.out.println(sub);
    }

    private static String longString(Integer limit) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < limit; i++) {
            res.append(UUID.randomUUID());
        }
        return res.toString();
    }

    private static Set<String> longestSubstringDynamic(String a, String b) {
        int[][] len = new int[a.length()][b.length()];
        int maxLen = 0;
        Set<String> maxes = new HashSet<>();
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    if (i == 0 || j == 0) {
                        len[i][j] = 1;
                    } else {
                        len[i][j] = len[i - 1][j - 1] + 1;
                        if (len[i][j] > maxLen) {
                            maxLen = len[i][j];
                            maxes.clear();
                        }
                        if (len[i][j] == maxLen && maxLen <= i + 1) {
                            maxes.add(a.substring(i - maxLen + 1, i + 1));
                        }
                    }
                }
            }
        }
        return maxes;
    }
}
