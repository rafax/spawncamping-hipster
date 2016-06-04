package com.gajdulewicz.spawncamping.hipster.problems;

public class BinarySearch {

    // returns index of x in elems or -1 if not found
    public static int find(int[] elems, int x) {
        return find(elems, 0, elems.length - 1, x);
    }

    private static int find(int[] elems, int start, int end, int x) {
        if (start + 1 == end) return x == elems[start] ? start : x == elems[end] ? end : -1;
        int mid = start + (end - start) / 2;
        if (x == elems[mid]) return mid;
        if (x < elems[mid]) return find(elems, start, mid, x);
        return find(elems, mid, end, x);
    }

    public static void main(String[] args) {
        System.out.println(find(new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9
        }, 4));

        System.out.println(find(new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9
        }, 9));

        System.out.println(find(new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9
        }, 1));

        System.out.println(find(new int[]{
                1, 2, 3, 5, 6, 7, 8, 9
        }, 4));
    }
}
