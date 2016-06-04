package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal on 01/08/14.
 */
public class PowerSet {
    static List<List<Integer>> powerSet(int[] set) {

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, set.length); i++) {
            List<Integer> elem = new ArrayList<>();
            for (int k = i, index = 0; k > 0; k >>= 1) {
                if ((k & 1) == 1) {
                    elem.add(set[index]);
                }
                index++;
            }
            res.add(elem);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(PowerSet.powerSet(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}).size()
        );
    }
}
