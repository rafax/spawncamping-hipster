package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by rafal on 06/08/14.
 */
public class SortGroupingAnagrams {

    static List<String> sort(List<String> input) {
        Collections.sort(input, (a, b) -> sorted(a).compareTo(sorted(b)));
        return input;
    }

    static String sorted(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }


    public static void main(String[] args) {
        System.out.println(SortGroupingAnagrams.sort(Arrays.asList(
                "ajjko", "koza", "jajko", "serek", "okaz"
        )));
    }
}
