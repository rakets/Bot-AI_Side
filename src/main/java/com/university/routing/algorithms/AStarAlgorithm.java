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
//        return 0; // Dijkstra-like behavior
        String[] currentCoords = current.split(",");
        String[] goalCoords = goal.split(",");
        double x1 = Double.parseDouble(currentCoords[0]);
        double y1 = Double.parseDouble(currentCoords[1]);
        double x2 = Double.parseDouble(goalCoords[0]);
        double y2 = Double.parseDouble(goalCoords[1]);
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
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


