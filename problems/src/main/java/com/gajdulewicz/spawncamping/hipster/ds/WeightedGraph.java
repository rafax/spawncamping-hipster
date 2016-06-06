package com.gajdulewicz.spawncamping.hipster.ds;

import com.google.common.collect.*;

import java.util.*;

public class WeightedGraph {
    private Set<String> nodes;
    private Multimap<String, Edge> edges;

    public WeightedGraph() {
        nodes = new HashSet<>();
        edges = HashMultimap.create();
    }

    public void addEdge(String from, String to, int weight) {
        nodes.add(from);
        nodes.add(to);
        edges.put(from, new Edge(from, to, weight));
    }

    public Collection<Edge> edgesFrom(String from) {
        return edges.get(from);
    }

    public Set<String> getNodes() {
        return new HashSet<>(nodes);
    }

    public static class Edge {
        public final String from;
        public final String to;
        public final int weight;

        public Edge(String from, String to, int weight) {

            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }


}
