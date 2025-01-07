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
    public static List<String> applyLocalSearch(Graph graph, List<String> path) {
        // Добавляем первую точку в конец пути, чтобы сделать его замкнутым
        path.add(path.get(0));

        // Оптимизация пути
        List<String> optimizedPath = optimizePath(graph, path);

        // Убираем дублирующуюся последнюю точку, если нужно
        optimizedPath.remove(optimizedPath.size() - 1);

        return optimizedPath;
    }
    public static boolean swapImprovesPath(Graph graph, List<String> path, int i, int j) {
        // Проверяем граничные условия
        if (i == j) {
            return false; // Обмен с собой не имеет смысла
        }

        // Рассчитываем текущее расстояние
        double currentDistance = calculateTotalDistance(graph, path);

        // Меняем вершины i и j местами
        Collections.swap(path, i, j);

        // Рассчитываем расстояние после обмена
        double newDistance = calculateTotalDistance(graph, path);

        // Меняем вершины обратно, чтобы сохранить исходный путь
        Collections.swap(path, i, j);

        // Возвращаем true, если новый путь короче текущего
        return newDistance < currentDistance;
    }
    private static double calculateTotalDistance(Graph graph, List<String> path) {
        double totalDistance = 0.0;
        for (int k = 0; k < path.size() - 1; k++) {
            String from = path.get(k);
            String to = path.get(k + 1);
            totalDistance += graph.getDistance(from, to);
        }
        return totalDistance;
    }
    //    public static void printRoad(List<String> adress, Map<String, String> map, List<String> coordinates, Graph graph){
    public static List<String> printRoad(List<String> adress, Map<String, String> map, List<String> coordinates, Graph graph) {
        List<String> road = new ArrayList<>();
        for (String coordinate : coordinates) {
            String key;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().equals(coordinate)) {
                    key = entry.getKey();
//                    System.out.println(coordinate + " " + adress.indexOf(key) + " " + key);
                    road.add(key);
                }
            }
        }
        int allDistance = 0; //общий адрес
        for (int i = 1; i <= coordinates.size() - 1; i++) {
            String currentPoint = coordinates.get(i);
            String previousPoint = coordinates.get(i - 1);
            System.out.println(graph.getAdjacencyList().get(previousPoint).get(currentPoint));
            allDistance += graph.getAdjacencyList().get(previousPoint).get(currentPoint);
        }
        System.out.println("All road : " + allDistance);
        return road;
    }
}

