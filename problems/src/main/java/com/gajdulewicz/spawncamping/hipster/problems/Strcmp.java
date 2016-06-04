package com.gajdulewicz.spawncamping.hipster.problems;

/**
 * Created by rafal on 14/05/16.
 */
public class Strcmp {

    public static int compare(String left, String right) {
        for (int i = 0; i < Math.min(left.length(), right.length()) + 1; i++) {
            Character a = null, b = null;
            if (i < left.length()) {
                a = left.charAt(i);
            }
            if (i < right.length()) {
                b = right.charAt(i);
            }
            if (a == null && b == null) {
                return 0;
            }
            if (a == null) {
                return -1;
            }
            if (b == null) {
                return 1;
            }
            if (a < b) {
                return -1;
            }
            if (a > b) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(compare("a", "abc"));
        System.out.println(compare("abc", "abc"));
        System.out.println(compare("def", "abc"));
    }
}
