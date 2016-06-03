package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rafal on 01/08/14.
 */
public class StringPermutations {

    public static List<String> generate(String s) {
        if (s.length() == 1) {
            return Arrays.asList(s);
        }
        List<String> res = new ArrayList<>();
        String first = s.substring(0, 1);
        String rec = s.substring(1);
        for (String p : generate(rec)) {
            res.add(first + p);
            for (int i = 1; i < p.length(); i++) {
                res.add(p.substring(0, i) + first + p.substring(i));
            }
            res.add(p + first);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(StringPermutations.generate("dupajasiuu").size());
    }
}
