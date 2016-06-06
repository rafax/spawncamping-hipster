package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.Arrays;
import java.util.Random;

public class ZeroMatrix {

    private static final Random r = new Random();

    public static void main(String[] args) {
        int[][] m = new int[5][5];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = r.nextInt(10);
            }
        }
        for (int i = 0; i < m.length; i++) {
            System.out.println(Arrays.toString(m[i]));
        }
        int[][] zeroed = zero(m);
        System.out.println("After");
        for (int i = 0; i < m.length; i++) {
            System.out.println(Arrays.toString(zeroed[i]));
        }
    }

    private static int[][] zero(int[][] m) {
        boolean[] cols = new boolean[m.length];
        boolean[] rows = new boolean[m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 0) {
                    cols[i] = true;
                    rows[j] = true;
                }
            }
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (cols[i] || rows[j]) {
                    m[i][j] = 0;
                }
            }
        }
        return m;
    }
}
