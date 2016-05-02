package com.gajdulewicz.problems;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by rafal on 23/07/14.
 * http://community.topcoder.com/stat?c=problem_statement&pm=2986&rd=5862
 */
public class Refactoring {


    int refactor(int n, int last) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int cnt = 0;
        for (int i = last; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                cnt += refactor(n / i, i) + 1;
            }
        }
        return cnt;
    }

    @Test
    public void itShouldSolveTheSampleProblems() {
        assertEquals(6, new Refactoring().refactor(24, 2));
        assertEquals(0, new Refactoring().refactor(9973, 2));
        assertEquals(295, new Refactoring().refactor(9240, 2));
        assertEquals(7389115, new Refactoring().refactor(1916006400, 2));
    }
}
