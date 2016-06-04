package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.HashMap;

/**
 * Created by rafal on 06/08/14.
 */
public class RansomLetter {

    static boolean isItPossible(String magazine, String letter) {
        HashMap<Character, Integer> counts = new HashMap<>();
        int missingCount = 0;
        for (int i = 0; i < letter.length(); i++) {
            if (!counts.containsKey(letter.charAt(i))) {
                missingCount++;
                counts.put(letter.charAt(i), 1);
            }
            counts.put(letter.charAt(i), counts.get(letter.charAt(i)) + 1);
        }
        for (int i = 0; i < magazine.length() && missingCount > 0; i++) {
            char l = magazine.charAt(i);
            if (counts.containsKey(l)) {
                counts.put(l, counts.get(l) - 1);
                if (counts.get(l) == 0) {
                    missingCount--;
                    counts.remove(l);
                }
            }
        }
        if (!counts.isEmpty()) {
            System.out.println(counts);
        }
        return counts.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(RansomLetter.isItPossible("programming  interviews  are  weird shocch", "no  scheme"));
    }
}
