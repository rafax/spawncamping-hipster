package com.gajdulewicz.spawncamping.hipster.problems;

/**
 * Created by rafal on 07/08/14.
 */
public class BitPalindrome {
    static boolean isPalindrome(int n) {
        boolean res = true;
        int bitLength = bitLength(n) + 1;
        System.out.println("bl of " + n + "=" + bitLength);
        for (int i = 0; i < bitLength; i++) {
            int a = n & (1 << (bitLength - i - 1)), b = (n & (1 << i));
            System.out.println(((a != 0) ? 1 : 0) + " " + ((b != 0) ? 1 : 0));
            if ((a != 0) != (b != 0)) {
                res = false;
            }
        }
        return res;
    }

    private static int bitLength(int num) {
        return (int) Math.floor(Math.log(num)) + 1;
    }

    public static void main(String[] args) {
        int i = 21;
        System.out.println(Integer.toBinaryString(i) + " isp: " + isPalindrome(i));
    }
}
