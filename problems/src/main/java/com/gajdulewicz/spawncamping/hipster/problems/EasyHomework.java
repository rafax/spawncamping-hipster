package com.gajdulewicz.spawncamping.hipster.problems;

/**
 * Created by rafal on 07/08/14.
 */
public class EasyHomework {
    enum Sign {
        POSITIVE, NEGATIVE, ZERO;

        public static Sign of(int n) {
            return n > 0 ? POSITIVE : n == 0 ? ZERO : NEGATIVE;
        }

        public Sign multiply(Sign s) {
            if (s == ZERO || this == ZERO) {
                return ZERO;
            } else if (this == POSITIVE) {
                return s;
            } else {
                return s == POSITIVE ? NEGATIVE : POSITIVE;
            }
        }

    }

    public static String determineSign(int[] a) {
        Sign current = Sign.of(a[0]);
        for (int i = 1; i < a.length; i++) {
            current = current.multiply(Sign.of(a[i]));
        }
        return current.toString();
    }

    public static void main(String[] args) {
        System.out.println("+ " + EasyHomework.determineSign(new int[]{5, 7, 2}));
        System.out.println("- " + EasyHomework.determineSign(new int[]{-5, 7, 2}));
        System.out.println("0 " + EasyHomework.determineSign(new int[]{5, 7, 2, 0}));
        System.out.println("0 " + EasyHomework.determineSign(new int[]{-5, 7, 2, 0}));
        System.out.println("+ " + EasyHomework.determineSign(new int[]{3, -14, 159, -26}));
        System.out.println("- " + EasyHomework.determineSign(new int[]{-1000000000}));
        System.out.println("P " + EasyHomework.determineSign(new int[]
                {123, -456, 789, -101112, 131415, 161718, 192021, 222324, 252627, 282930, 313233, 343536, 373839, 404142, 434445, 464748, 495051, 525354, 555657}));

    }
}
