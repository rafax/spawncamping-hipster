package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * Created by rafal on 16/05/16.
 */
public class WildcardExpansion {

    public static void expansions(String in, Function<String, Integer> c) {
        ExecutorService executorService = Executors.newWorkStealingPool();
        int n = countQuestionMarks(in);
        for (int i = 0; i < Math.pow(2, n); i++) {
            final int num = i;
            executorService.execute(() -> {
                String v = Integer.toBinaryString(num);
                StringBuilder sb = new StringBuilder();
                int mark = 0;
                for (int j = 0; j < in.length(); j++) {
                    if (in.charAt(j) == '?') {
                        if (mark < n - v.length())
                            sb.append('0');
                        else {
                            sb.append(v.charAt(mark - (n - v.length())));
                        }
                        mark++;
                    } else sb.append(in.charAt(j));
                }
                c.apply(sb.toString());
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int countQuestionMarks(String in) {
        int cnt = 0;
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) == '?') {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        String in = "?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?00?0?0?0";
        if (args.length > 0) {
            in = args[0];
        }
        final AtomicInteger cnt = new AtomicInteger();
        long start = System.currentTimeMillis();

        expansions(in, s -> 0);
        long end = System.currentTimeMillis();
        System.out.println(String.format("Took %d ms", (end - start)));
    }
}
