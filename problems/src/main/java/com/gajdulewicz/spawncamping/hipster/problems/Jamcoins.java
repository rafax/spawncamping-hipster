package com.gajdulewicz.spawncamping.hipster.problems;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Jamcoins {
    public static void main(String[] args) throws IOException {
        long num = Double.valueOf(Math.pow(2, 16)).longValue();
        long start = System.currentTimeMillis();
        final Set<Long> primes = sieveUnder(num);
        long end = System.currentTimeMillis();
        System.out.println(num + " took: " + (end - start) + "ms");
    }


    public static Set<Long> sieveUnder(long n) {
        BitSet bs = new BitSet();
        for (int i = 1; i < n; i++) {
            bs.set(i, true);
        }
        int nextSet = bs.nextSetBit(2);
        while (nextSet > 0 && nextSet <= n) {
            int curr;
            for (int i = 2; i * nextSet < n; i++) {
                curr = i * nextSet;
                bs.clear(curr);
            }
            nextSet = bs.nextSetBit(nextSet + 1);
        }
        return toSet(bs);
    }

    private static Set<Long> toSet(BitSet bs) {
        LinkedHashSet<Long> res = new LinkedHashSet<>();
        int next = bs.nextSetBit(0);
        while (next > 0) {
            res.add((long) next);
            next = bs.nextSetBit(next + 1);
        }
        return res;
    }


}
