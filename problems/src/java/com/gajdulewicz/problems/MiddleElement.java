package com.gajdulewicz.problems;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by rafal on 06/08/14.
 */
public class MiddleElement {

    public static int middle(int a, int b, int c) {
        if (a == b || a == c) {
            return a;
        }
        if (b == c) {
            return b;
        }
        if (largerThanOneAndSmallerThanOne(a, b, c)) return a;
        if (largerThanOneAndSmallerThanOne(b, a, c)) return b;
        if (largerThanOneAndSmallerThanOne(c, a, b)) return c;

        return -1;
    }

    public static boolean largerThanOneAndSmallerThanOne(int a, int b, int c) {
        return (a > b && a < c) || (a < b && a > c);
    }


    @Test
    public void allEqual() {
        assertEquals(3, middle(3, 3, 3));
    }

    @Test
    public void twoEqual() {
        assertEquals(3, middle(3, 3, 2));
        assertEquals(3, middle(2, 3, 3));
        assertEquals(3, middle(3, 2, 3));
    }

    @Test
    public void allDifferent() {
        assertEquals(2, middle(1, 2, 3));
        assertEquals(2, middle(1, 3, 2));
        assertEquals(2, middle(2, 1, 3));
        assertEquals(2, middle(2, 3, 1));
        assertEquals(2, middle(3, 1, 2));
        assertEquals(2, middle(3, 2, 1));
    }
}
