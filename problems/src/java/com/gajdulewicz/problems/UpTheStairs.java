package com.gajdulewicz.problems;

import java.util.Arrays;

/**
 * Created by rafal on 01/08/14.
 */
public class UpTheStairs {

    private static int waysToScale(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 4;
        }
        return waysToScale(n - 1) + waysToScale(n - 2)  + waysToScale(n - 3) ;
    }

    public static int countWaysDP(int n, int[] map) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (map[n] > -1) {
            return map[n];
        } else {
            map[n] = countWaysDP(n - 1, map) + countWaysDP(n - 2, map) +
                    countWaysDP(n - 3, map);
            return map[n];
        }
    }

    public static int countWays(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else {
            return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
        }
    }

    public static void main(String[] args) {
        int steps = 10;
        int[] map = new int[steps + 1];
        Arrays.fill(map, -1);
        System.out.println(UpTheStairs.waysToScale(steps));
        System.out.println(UpTheStairs.countWays(steps));
        System.out.println(UpTheStairs.countWaysDP(steps, new int[steps + 1]));

    }


}
