package com.gajdulewicz.problems;

import java.util.Arrays;

/**
 * Created by rafal on 01/08/14.
 */
public class MergeSortedArrays {

    public static int[] merge(int[] a, int[] b) {
        int lastA = a.length - b.length - 1;
        int lastB = b.length - 1;

        int writeTo = a.length - 1;
        while (lastA >= 0 && lastB >= 0) {
            if (a[lastA] > b[lastB]) {
                a[writeTo] = a[lastA--];
            } else {
                a[writeTo] = b[lastB--];
            }
            writeTo--;
        }
        for (; lastB >= 0; lastB--) {
            a[writeTo--] = b[lastB];
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = new int[200], b = new int[100];
        for (int i = 0; i < 200; i++) {
            if (i % 2 == 0) {
                a[i / 2] = i;
            } else {
                b[i / 2] = i;
            }
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        MergeSortedArrays.merge(a, b);
        System.out.println(Arrays.toString(a));
    }
}
