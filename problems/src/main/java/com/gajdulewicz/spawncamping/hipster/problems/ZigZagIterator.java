package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ZigZagIterator {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 3, 4, 10}, {2, 5, 9, 11}, {6, 8, 12, 15}, {7, 13, 14, 16}};
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        IntStream res = zigZagIterate(matrix);
        System.out.println(Arrays.toString(res.toArray()));
    }

    private static IntStream zigZagIterate(int[][] matrix) {
        int x = 0, y = 0;
        int xsize = matrix.length, ysize = matrix[y].length;
        boolean downLeft = true;
        IntStream.Builder res = IntStream.builder();
        while (true) {
            res.add(matrix[y][x]);
            if (downLeft) {
                if (x > 0) {
                    if (y < ysize - 1) {
                        x--;
                        y++;
                    } else {
                        if (x == xsize - 1) {
                            break;
                        } else {
                            downLeft = false;
                            x++;
                        }
                    }
                } else {
                    downLeft = false;
                    if (y < ysize - 1) {
                        y++;
                    } else {
                        x++;
                    }
                }
            } else {
                if (y == 0) {
                    downLeft = true;
                    if (x == xsize - 1) {
                        y++;
                    } else {
                        x++;
                    }
                } else {
                    if (x < xsize - 1) {
                        x++;
                        y--;
                    } else {
                        if (y == ysize - 1) {
                            break;
                        } else {
                            downLeft = true;
                            y++;
                        }
                    }
                }
            }
        }
        return res.build();
    }
}
