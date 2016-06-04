package com.gajdulewicz.spawncamping.hipster.problems;

/**
 * Created by rafal on 08/08/14.
 */
public class ChangeGiving {

    public ChangeGiving(int[] denominations) {
        this.denominations = denominations;
    }

    int[] denominations;

    public int doIt(int change) {
        return giveChange(0, change);
    }

    public static int makeChange(int n, int denom) {
        int next_denom = 0;
        switch (denom) {
            case 25:
                next_denom = 10;
                break;
            case 10:
                next_denom = 5;
                break;
            case 5:
                next_denom = 1;
                break;
            case 1:
                return 1;
        }
        int ways = 0;
        for (int i = 0; i * denom <= n; i++) {
            ways += makeChange(n - i * denom, next_denom);
        }
        return ways;

    }

    private int giveChange(int ind, int change) {
        if (ind == denominations.length - 1) {
            return 1;
        }
        int ways = 0;
        for (int i = 0; i * denominations[ind] <= change; i++) {
            ways += giveChange(ind + 1, change - i * denominations[ind]);
        }
        return ways;
    }

    public static void main(String[] args) {
        System.out.println(new ChangeGiving(new int[]{25, 10, 5, 1}).doIt(100));
        System.out.println(ChangeGiving.makeChange(100, 25));
    }


}
