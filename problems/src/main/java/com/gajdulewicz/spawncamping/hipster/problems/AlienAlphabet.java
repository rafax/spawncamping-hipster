package com.gajdulewicz.spawncamping.hipster.problems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

public class AlienAlphabet {
    public static void main(String[] args) {
        letterOrder(Arrays.asList("caa", "aaa", "aab"));
    }

    private static List<String> letterOrder(List<String> words) {
        List<LetterPair> pairs = new ArrayList<>();
        for (int i = 0; i < words.size() - 1; i++) {
            pairs.add(compare(words.get(i), words.get(i + 1)));
        }
        Multimap<String, String> graph = HashMultimap.create();
        System.out.println(pairs);
        pairs.forEach(p -> graph.put(Character.toString(p.from), Character.toString(p.to)));
        System.out.println(graph);
        List<String> order = topoSort(graph);
        System.out.println(order);
        return order;
    }

    private static List<String> topoSort(Multimap<String, String> graph) {
        List<String> letters = new ArrayList<>();
        graph.keys().forEach(l -> visit(l, new HashSet<>(), graph, letters));
        return letters;
    }

    private static List<String> visit(String v, Set<String> seen, Multimap<String, String> graph, List<String> order) {
        seen.add(v);
        graph.get(v).forEach(c -> {
            if (!seen.contains(c)) visit(c, seen, graph, order);
        });
        if(!order.contains(v)) {
            order.add(0, v);
        }
        seen.remove(v);
        return order;
    }

    private static LetterPair compare(String a, String b) {
        final int min = Math.min(a.length(), b.length());
        for (int i = 0; i < min; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return new LetterPair(a.charAt(i), b.charAt(i));
            }
        }
        throw new RuntimeException("WAT");
    }


    public static class LetterPair {
        private final char from;
        private final char to;

        public LetterPair(char from, char to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return String.format("%s -> %s", from, to);
        }
    }
}
