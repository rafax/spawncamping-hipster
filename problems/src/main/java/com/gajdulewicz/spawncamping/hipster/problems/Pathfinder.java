package com.gajdulewicz.spawncamping.hipster.problems;

import com.gajdulewicz.spawncamping.hipster.ds.DirectedGraph;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by rafal on 31/07/14.
 */
public class Pathfinder {

    public static Stream<String> dfs(DirectedGraph g, String source, Function<String, Boolean> stop) {
        Set<String> visited = new HashSet<>();
        return dfs(g, source, visited, stop);
    }

    private static Stream<String> dfs(DirectedGraph g, String source, Set<String> visited, Function<String, Boolean> stop) {
        if (source == null) {
            return Stream.empty();
        }
        visited.add(source);
        if (g.edgesFrom(source) != null)
            g.edgesFrom(source).stream().filter(n -> !visited.contains(n)).forEach(n -> dfs(g, n, visited, stop));
        else
            System.out.println(source + " has no edges?");
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
        g.addEdge("3", "4");
        System.out.println(hasPath(g, "1", "4"));
    }
}
