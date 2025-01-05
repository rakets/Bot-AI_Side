package com.university.routing.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph {    //Сlass for representing a graph
    private Map<String, Map<String, Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    //Adds an edge from the "from" vertex to the "to" vertex with the specified "weight"
    public void addEdge(String from, String to, int weight) {
        adjacencyList.putIfAbsent(from, new HashMap<>());
        adjacencyList.get(from).put(to, weight);
    }

    //Returns a dictionary of neighbors for a given node
    public Map<String, Integer> getNeighbors(String node) {
        return adjacencyList.getOrDefault(node, new HashMap<>());
    }

    //Returns the set of all vertices of the graph (keys from the adjacency)
    public Set<String> getNodes() {
        return adjacencyList.keySet();
    }

    @Override
    public String toString() {
        return "Graph : " + adjacencyList;
    }
}
