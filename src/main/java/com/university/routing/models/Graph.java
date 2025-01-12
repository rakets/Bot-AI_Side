package com.university.routing.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph {    //Сlass for representing a graph
    private Map<String, Map<String, Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public Map<String, Map<String, Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    //Adds an edge from the "from" vertex to the "to" vertex with the specified "weight"
    public void addEdge(String from, String to, int weight) {
//        adjacencyList.putIfAbsent(from, new HashMap<>());
//        adjacencyList.get(from).put(to, weight);

        // Убедитесь, что для вершины "from" существует пустой список смежных вершин
        adjacencyList.putIfAbsent(from, new HashMap<>());

        // Проверка, существует ли уже обратное ребро "to -> from"
        if (adjacencyList.containsKey(to) && adjacencyList.get(to).containsKey(from)) {
            int existingWeight = adjacencyList.get(to).get(from);
//            System.out.println(existingWeight);
            System.out.println("Добавлено такое же ребро " + existingWeight + " между " + from + " и " + to + " вместо " + weight);

            // Устанавливаем такое же значение для "from -> to"
            adjacencyList.get(from).put(to, existingWeight);
        } else {
            // Если обратного ребра нет, добавляем ребро с указанным весом
            adjacencyList.get(from).put(to, weight);
        }
    }

    // Метод для получения расстояния между двумя вершинами
    public int getDistance(String from, String to) {
        return adjacencyList.getOrDefault(from, new HashMap<>()).getOrDefault(to, Integer.MAX_VALUE);
    }

    //    public void addEdge(String from, String to, int weight) {
//        adjacencyList.putIfAbsent(from, new HashMap<>());
//        adjacencyList.putIfAbsent(to, new HashMap<>());
//        adjacencyList.get(from).put(to, weight);
//        adjacencyList.get(to).put(from, weight);  // добавляем обратное ребро
//    }
    //Returns a dictionary of neighbors for a given node
//    public Map<String, Integer> getNeighbors(String node) {
////        System.out.println("Соседи : " + adjacencyList.getOrDefault(node, new HashMap<>()));
////        return adjacencyList.getOrDefault(node, new HashMap<>());
////    }
    public Map<String, Integer> getNeighbors(String node) {
        Map<String, Integer> neighbors = adjacencyList.getOrDefault(node, new HashMap<>());
        if (neighbors.isEmpty()) {
            System.out.println("У вершины " + node + " нет соседей.");
        } else {
            System.out.println("Соседи вершины " + node + ": " + neighbors);
        }
        return neighbors;
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
