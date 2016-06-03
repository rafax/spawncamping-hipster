package com.gajdulewicz.spawncamping.hipster.problems;

/**
 * Created by rafal on 08/08/14.
 */
public class FrogJumps {

    static int howManyOptions(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 4;
        }

        return howManyOptions(n - 1) + howManyOptions(n - 2) + howManyOptions(n - 3);
    }

    public static void main(String[] args) {
        System.out.println(FrogJumps.howManyOptions(5));
    }
}
