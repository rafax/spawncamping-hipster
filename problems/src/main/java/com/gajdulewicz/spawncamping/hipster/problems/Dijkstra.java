package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.Arrays;

public class Dijkstra {
    public static void main(String[] args) {
        boolean[] grid = new boolean[5 * 5];
        Arrays.fill(grid, true);
        grid[1 * 5 + 1] = false;
        grid[2 * 5 + 2] = false;
        grid[3 * 5 + 3] = false;
        grid[1 * 5 + 4] = false;

        System.out.println(Arrays.toString(grid));


        solve(grid, 4, 0, 0, 4);
    }

    private static int solve(boolean[] grid, int x1, int y1, int x2, int y3) {
        return 42;
    }
}
