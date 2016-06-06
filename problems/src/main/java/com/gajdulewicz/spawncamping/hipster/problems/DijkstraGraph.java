package com.gajdulewicz.spawncamping.hipster.problems;

import com.gajdulewicz.spawncamping.hipster.ds.WeightedGraph;

import java.util.*;

public class DijkstraGraph {
    public static void main(String[] args) {
        String start = "1", end = "5";
        WeightedGraph g = new WeightedGraph();
        g.addEdge(start, "2", 14);
        g.addEdge(start, "3", 7);
        g.addEdge(start, "6", 14);
        g.addEdge("2", end, 10);
        g.addEdge("3", end, 11);
        g.addEdge("4", end, 8);
        List<String> path = findPath(g, start, end);
        System.out.println(path);
    }

    private static List<String> findPath(WeightedGraph g, String start, String end) {
        TreeSet<String> unvisited = new TreeSet<>(g.getNodes());
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        for (String node : unvisited) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        while (!unvisited.isEmpty()) {
            String curr = unvisited.pollFirst();
            for (WeightedGraph.Edge edge : g.edgesFrom(curr)) {
                int d = distances.get(edge.from) + edge.weight;
                if (d < distances.get(edge.to)) {
                    distances.put(edge.to, d);
                    prev.put(edge.to, edge.from);
                }
            }
        }
        System.out.println(distances);
        System.out.println(prev);
        LinkedList<String> path = new LinkedList<>();
        path.add(end);
        String curr = prev.get(end);
        while (true) {
            path.add(curr);
            curr = prev.get(curr);
            if (curr == null) {
                break;
            }
        }
        Collections.reverse(path);
        return path;
    }


}
