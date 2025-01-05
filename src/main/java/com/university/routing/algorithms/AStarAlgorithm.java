package com.university.routing.algorithms;

import com.university.routing.models.Graph;
import com.university.routing.models.Node;

import java.util.*;

public class AStarAlgorithm{
    public static List<String> findShortestPath(Graph graph, String start, String goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<String> closedSet = new HashSet<>();

        Map<String, Integer> gCosts = new HashMap<>();
        Map<String, String> cameFrom = new HashMap<>();

        gCosts.put(start, 0);
        openSet.add(new Node(start, 0, heuristic(start, goal)));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.getId().equals(goal)) {
                return reconstructPath(cameFrom, goal);
            }

            closedSet.add(current.getId());

            for (Map.Entry<String, Integer> neighbor : graph.getNeighbors(current.getId()).entrySet()) {
                if (closedSet.contains(neighbor.getKey())) continue;

                int tentativeGCost = gCosts.get(current.getId()) + neighbor.getValue();
                if (tentativeGCost < gCosts.getOrDefault(neighbor.getKey(), Integer.MAX_VALUE)) {
                    gCosts.put(neighbor.getKey(), tentativeGCost);
                    cameFrom.put(neighbor.getKey(), current.getId());
                    openSet.add(new Node(neighbor.getKey(), tentativeGCost, heuristic(neighbor.getKey(), goal)));
                }
            }
        }

        return Collections.emptyList(); // Путь не найден
    }

    // Метод эвристической оценки
    private static int heuristic(String current, String goal) {
        // Реализуйте функцию для оценки расстояния между current и goal
        return 0;
    }

    // Метод восстановления пути
    private static List<String> reconstructPath(Map<String, String> cameFrom, String goal) {
        List<String> path = new ArrayList<>();
        String current = goal;
        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}

//package com.university.routing.algorithms;
//
//import com.university.routing.models.Graph;
//
//import java.util.*;
//
//public class AStarAlgorithm{
//
//    public static List<String> findShortestPath(Graph graph, Map<String, String> coordinates, String start, String goal) {
//        // Очередь с приоритетом для открытых узлов
//        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(Node::getFCost));
//        Set<String> closedList = new HashSet<>();
//        Map<String, Node> nodes = new HashMap<>();
//
//        // Инициализация начального узла
//        Node startNode = new Node(start, 0, heuristic(coordinates.get(start), coordinates.get(goal)));
//        openList.add(startNode);
//        nodes.put(start, startNode);
//
//        while (!openList.isEmpty()) {
//            Node current = openList.poll();
//            closedList.add(current.getId());
//
//            // Если мы достигли цели
//            if (current.getId().equals(goal)) {
//                return reconstructPath(current);
//            }
//
//            // Обрабатываем соседей
//            for (Map.Entry<String, Integer> neighborEntry : graph.getNeighbors(current.getId()).entrySet()) {
//                String neighborId = neighborEntry.getKey();
//                int edgeCost = neighborEntry.getValue();
//
//                if (closedList.contains(neighborId)) continue;
//
//                double tentativeG = current.gCost + edgeCost;
//                Node neighbor = nodes.getOrDefault(
//                        neighborId,
//                        new Node(neighborId, Double.MAX_VALUE, heuristic(coordinates.get(neighborId), coordinates.get(goal)))
//                );
//
//                if (tentativeG < neighbor.gCost) {
//                    neighbor.gCost = tentativeG;
//                    neighbor.setParent(current);
//                    if (!openList.contains(neighbor)) openList.add(neighbor);
//                    nodes.put(neighborId, neighbor);
//                }
//            }
//        }
//
//        return Collections.emptyList(); // Путь не найден
//    }
//
//    // Вспомогательный метод: эвристика (Евклидово расстояние)
//    private static double heuristic(String coord1, String coord2) {
//        String[] c1 = coord1.split(",");
//        String[] c2 = coord2.split(",");
//
//        double lat1 = Double.parseDouble(c1[0].trim());
//        double lon1 = Double.parseDouble(c1[1].trim());
//        double lat2 = Double.parseDouble(c2[0].trim());
//        double lon2 = Double.parseDouble(c2[1].trim());
//
//        // Евклидово расстояние
//        return Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(lon1 - lon2, 2));
//    }
//
//    // Вспомогательный метод: восстановление пути
//    private static List<String> reconstructPath(Node current) {
//        List<String> path = new ArrayList<>();
//        while (current != null) {
//            path.add(current.getId());
//            current = current.getParent();
//        }
//        Collections.reverse(path);
//        return path;
//    }
//
//    // Класс для узла
//    private static class Node {
//        private String id;
//        private double gCost; // Стоимость пути от начальной точки
//        private double hCost; // Эвристическая оценка
//        private Node parent;
//
//        public Node(String id, double gCost, double hCost) {
//            this.id = id;
//            this.gCost = gCost;
//            this.hCost = hCost;
//        }
//
//        public double getFCost() {
//            return gCost + hCost;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public Node getParent() {
//            return parent;
//        }
//
//        public void setParent(Node parent) {
//            this.parent = parent;
//        }
//    }
//}
