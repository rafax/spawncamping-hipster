package com.gajdulewicz.spawncamping.hipster.problems;

import java.math.BigInteger;

public class ModExp {

    public static void main(String[] args) {
        BigInteger base = BigInteger.valueOf(6047);
        BigInteger pow = BigInteger.valueOf(7879);
        BigInteger mod = BigInteger.valueOf(6053);
        long start = System.nanoTime();
        BigInteger naive = naive(base, pow, mod);
        long end = System.nanoTime();
        System.out.println(naive + " Naive took: " + (end - start) / 1000000);
        start = System.nanoTime();
        BigInteger builtin = builtin(base, pow, mod);
        end = System.nanoTime();
        System.out.println(builtin + " builtin took: " + (end - start) / 1000000);
        start = System.nanoTime();
        BigInteger smarter = smarter(base, pow, mod);
        end = System.nanoTime();
        System.out.println(smarter + "smarter took: " + (end - start) / 1000000);
    }

    public static BigInteger naive(BigInteger base, BigInteger pow, BigInteger mod) {
        return base.pow(pow.intValueExact()).mod(mod);
    }

    public static BigInteger builtin(BigInteger base, BigInteger pow, BigInteger mod) {
        return base.modPow(pow, mod);
    }

    public static BigInteger smarter(BigInteger base, BigInteger pow, BigInteger mod) {
        if (mod.equals(BigInteger.ONE)) return BigInteger.ZERO;
        BigInteger c = BigInteger.ONE;
        while (pow.compareTo(BigInteger.ZERO) > 0) {
            if (pow.testBit(0)) {
                c = (c.multiply(base)).mod(mod);
            }
            pow = pow.shiftRight(1);
            base = (base.pow(2)).mod(mod);
        }
        return c;
    }
}
