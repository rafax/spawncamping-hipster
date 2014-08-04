package com.gajdulewicz;

import java.util.Arrays;

/**
 * Created by rafal on 04/08/14.
 */
public class BinarySearch {

    public static int binarySearch(int[] input, int key) {
        int left = 0, right = input.length - 1;
        while (left <= right) {

            System.out.println(String.format("l: %s r: %s", left, right));
            int middle = left + (right - left) / 2;
            if (key > input[middle]) {
                left = middle + 1;
            } else if (key < input[middle]) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -(left + 1);
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5, 60, 70, 80, 90, 100};
        System.out.println(BinarySearch.binarySearch(input, 55));
        System.out.println(Arrays.binarySearch(input, 55));
    }
}
