package com.university.routing.algorithms;

import com.university.routing.models.Graph;
import java.util.*;

public class localSearch { // Локальный поиск

    public static List<String> optimizePath(Graph graph, List<String> path) {
        boolean improvement = true;
        while (improvement) {
            improvement = false;
            for (int i = 1; i < path.size() - 2; i++) {
                for (int j = i + 1; j < path.size() - 1; j++) {
                    if (swapImprovesPath(graph, path, i, j)) {
                        Collections.swap(path, i, j);
                        improvement = true;
                    }
                }
            }
        }
        return path;
    }

    // Метод проверки улучшения маршрута
    private static boolean swapImprovesPath(Graph graph, List<String> path, int i, int j) {
        String from = path.get(i - 1);
        String to = path.get(j + 1);

        String currentI = path.get(i);
        String currentJ = path.get(j);

        int currentDistance = graph.getNeighbors(from).get(currentI) +
                graph.getNeighbors(currentJ).get(to);
        int swappedDistance = graph.getNeighbors(from).get(currentJ) +
                graph.getNeighbors(currentI).get(to);

        return swappedDistance < currentDistance;
    }



//    public static List<String> optimizeRoute(List<String> initialRoute, Map<String, Map<String, Double>> graph) {
//        List<String> currentRoute = new ArrayList<>(initialRoute);
//        double currentCost = calculateRouteCost(currentRoute, graph);
//
//        boolean improved;
//        do {
//            improved = false;
//            for (int i = 1; i < currentRoute.size() - 1; i++) {
//                for (int j = i + 1; j < currentRoute.size(); j++) {
//                    // Генерация соседнего маршрута (путем перестановки точек)
//                    List<String> newRoute = new ArrayList<>(currentRoute);
//                    Collections.swap(newRoute, i, j);
//
//                    double newCost = calculateRouteCost(newRoute, graph);
//                    if (newCost < currentCost) {
//                        currentRoute = newRoute;
//                        currentCost = newCost;
//                        improved = true;
//                    }
//                }
//            }
//        } while (improved);
//
//        return currentRoute;
//    }
//
//    private static double calculateRouteCost(List<String> route, Map<String, Map<String, Double>> graph) {
//        double cost = 0.0;
//        for (int i = 0; i < route.size() - 1; i++) {
//            cost += graph.get(route.get(i)).get(route.get(i + 1));
//        }
//        return cost;
//    }
}

