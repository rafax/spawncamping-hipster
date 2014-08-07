package com.gajdulewicz.problems;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by rafal on 06/08/14.
 */
public class NewArenaPassword {

    public int minChange(String oldPassword, int K) {
        int n = oldPassword.length();
        int res = 0;
        for (int i = 0; i < n - K && i < K; i++) {
            int[] counts = new int[26];
            int t = 0;
            for (int j = i; j < n; j += n - K) {
                counts[oldPassword.charAt(j) - 'a']++;
                t++;
            }
            res += t - maxElem(counts);
        }
        return res;

    }

    private int maxElem(int[] counts) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > max) {
                max = counts[i];
            }
        }
        return max;
    }

    @Test
    public void verify1() {
        assertEquals(3, new NewArenaPassword().minChange("topcoderopen", 5));
    }

    @Test
    public void verify2() {
        assertEquals(0, new NewArenaPassword().minChange("puyopuyo", 4));
    }

    @Test
    public void verify3() {
        assertEquals(1, new NewArenaPassword().minChange("loool", 3));
    }

    @Test
    public void verify4() {
        assertEquals(5, new NewArenaPassword().minChange("amavckdkz", 7));
    }
}
