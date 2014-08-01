package com.gajdulewicz.problems;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by rafal on 31/07/14.
 */
public class Pathfinder {

    static class DirectedGraph {
        Set<String> vertices;
        Map<String, List<String>> edges;

        DirectedGraph() {
            vertices = new HashSet<>();
            edges = new HashMap<>();
        }

        public void addVertex(String v) {
            vertices.add(v);
            edges.put(v, new ArrayList<>());
        }

        public void addEdge(String from, String to) {
            edges.get(from).add(to);
        }

        public List<String> edgesFrom(String source) {
            return edges.get(source);
        }
    }

    public static Stream<String> dfs(DirectedGraph g, String source, Function<String, Boolean> stop) {
        Set<String> visited = new HashSet<>();
        return dfs(g, source, visited, stop);
    }

    private static Stream<String> dfs(DirectedGraph g, String source, Set<String> visited, Function<String, Boolean> stop) {
        if (source == null) {
            return Stream.empty();
        }
        visited.add(source);
        System.out.println(source);
        if (g.edgesFrom(source) != null)
            g.edgesFrom(source).stream().filter(n -> !visited.contains(n)).forEach(n -> dfs(g, n, visited, stop));
        else
            System.out.println(source + "has no edges?");
        return visited.stream();
    }

    public static boolean hasPath(DirectedGraph g, String from, String to) {
        Set<String> visited = new HashSet<>();
        dfs(g, from, visited, s -> visited.contains(to));
        return visited.contains(to);
    }

    public static void main(String[] args) {
        DirectedGraph g = new DirectedGraph();
        g.addVertex("1");
        g.addVertex("2");
        g.addVertex("3");
        g.addVertex("4");
        g.addEdge("1", "2");
        g.addEdge("2", "3");
        g.addEdge("3", "5");
        g.addEdge("4", "3");
        System.out.println(hasPath(g, "1", "4"));
    }
}
