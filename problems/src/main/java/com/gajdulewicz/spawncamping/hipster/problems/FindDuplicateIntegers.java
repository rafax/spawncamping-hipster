package com.gajdulewicz.spawncamping.hipster.problems;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class FindDuplicateIntegers {

    public static int count = 2 * 1000 * 1000 * 100;

    public static Random r = new Random();

    public static void main(String[] args) {
        int dup = r.nextInt();
        Duplicate duplicate = findDuplicate(IntStream.concat(IntStream.concat(IntStream.of(dup), IntStream.range(0, count)), IntStream.of(dup)));
        System.out.println(duplicate);
    }

    private static Duplicate findDuplicate(IntStream par) {
        int[] ints = par.toArray();
        System.out.println("Materialized");
        Set<Integer> all = new HashSet<>();
        System.out.println("Allocated BloomFilter");
        for (int i = 0; i < ints.length; i++) {
            int v = ints[i];
            if (!all.add(v)) {
                return new Duplicate(i, v);
            }
        }
        return null;
    }


    public static class Duplicate {
        private final int pos;
        private final int value;

        public Duplicate(int pos, int value) {
            this.pos = pos;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Duplicate at " + pos + " : " + value;
        }
    }

}
