package com.gajdulewicz.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rafal on 16/05/16.
 */
public class WildcardExpansion {

    public static List<String> expansions(String in) {
        List<String> res = new LinkedList<String>();
        int n = countQuestionMarks(in);
        for (int i = 0; i < Math.pow(2, n); i++) {
            String v = leftPad(n, Integer.toBinaryString(i));
            StringBuilder sb = new StringBuilder();
            int mark = 0;
            for (int j = 0; j < in.length(); j++) {
                if (in.charAt(j) == '?') {
                    sb.append(v.charAt(mark++));
                } else sb.append(in.charAt(j));
            }
            res.add(sb.toString());
        }
        return res;
    }

    private static String leftPad(int n, String s) {
        StringBuilder sb = new StringBuilder();
        int diff = n - s.length();
        for (int i = 0; i < diff; i++) {
            sb.append("0");
        }
        sb.append(s);
        return sb.toString();
    }

    private static int countQuestionMarks(String in) {
        int cnt = 0;
        List<String> parts = new ArrayList<>();
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) == '?') {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        expansions("?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?0?");
        long end = System.currentTimeMillis();
        System.out.println(String.format("Took %d ms", (end - start)));
    }
}
