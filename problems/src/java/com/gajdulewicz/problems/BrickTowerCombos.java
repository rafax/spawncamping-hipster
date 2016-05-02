package com.gajdulewicz.problems;

import java.util.HashMap;

/**
 * Created by rafal on 07/08/2014.
 */
public class BrickTowerCombos {

    public static int count(int n) {
        HashMap<Integer, Integer> memo = new HashMap<>();
        memo.put(1, 1);
        memo.put(2, 2);
        memo.put(3, 4);
        memo.put(4, 8);
        return count(memo, n);
    }

    private static int count(HashMap<Integer, Integer> memo, int n) {
        if (!memo.containsKey(n)) {
            int optionsForN = count(memo, n - 1)  + count(n - 2)  + count(n - 3) + count(n - 4) ;
            memo.put(n, optionsForN);
        }
        return memo.get(n);
    }

    public static void main(String[] args) {
        System.out.println(BrickTowerCombos.count(5));
    }
}
