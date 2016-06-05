package com.gajdulewicz.spawncamping.hipster.ds;

import java.util.*;

public class DirectedGraph {
    Set<String> vertices;
    Map<String, List<String>> edges;

    public DirectedGraph() {
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
