package com.gajdulewicz.problems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CountingSheep {
    public static void main(String[] args) {
        try {
            String in = args.length > 0 ? args[0] : "/Users/gajduler/Downloads/A-large-practice.in";
            List<String> cases = Files.readAllLines(Paths.get(in));
            int numCases = Integer.parseInt(cases.get(0));
            for (int i = 1; i <= numCases; i++) {
                int start = Integer.parseInt(cases.get(i));
                System.out.println(String.format("Case #%s: %s", i, solve(start)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solve(long start) {
        if(start==0){
            return "INSOMNIA";
        }
        Set<Integer> seen = new HashSet<>(10);
        int multiple = 1;
        while (true) {
            long val = start * multiple;
            List<Integer> digits = digits(val);
            digits.forEach(seen::add);
            if (seen.size() == 10) {
                return Long.toString(val);
            } else {
                multiple++;
            }
        }
    }

    private static List<Integer> digits(long val) {
        List<Integer> digits = new LinkedList<>();
        while (val > 0) {
            digits.add((int) (val % 10));
            val = val / 10;
        }
        return digits;
    }


}
