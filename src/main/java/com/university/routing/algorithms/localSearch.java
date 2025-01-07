package com.university.routing.algorithms;

import com.university.routing.models.Graph;
import java.util.*;

public class localSearch { // Локальный поиск

//    public static List<String> optimizePath(Graph graph, List<String> path) {
//        boolean improvement = true;
//        while (improvement) {
//            improvement = false;
//            for (int i = 1; i < path.size() - 2; i++) {
//                for (int j = i + 1; j < path.size() - 1; j++) {
//                    if (swapImprovesPath(graph, path, i, j)) {
//                        Collections.swap(path, i, j);
//                        improvement = true;
//                    }
//                }
//            }
//        }
//        return path;
//    }
public static List<String> optimizePath(Graph graph, List<String> path) {
    boolean improvement = true;
    int iteration = 0; // Счетчик итераций
    int maxIterations = 1000; // Ограничение на количество итераций

    while (improvement && iteration < maxIterations) {
        improvement = false;
        iteration++;
        System.out.println("Итерация: " + iteration); // Логируем номер итерации

        List<String> previousPath = new ArrayList<>(path); // Сохраняем текущий путь

        for (int i = 1; i < path.size() - 2; i++) {
            for (int j = i + 1; j < path.size() - 1; j++) {
                System.out.println("Проверяем замену: " + path.get(i) + " и " + path.get(j)); // Логируем проверяемые вершины

                if (swapImprovesPath(graph, path, i, j)) {
                    Collections.swap(path, i, j);
                    improvement = true;
                    System.out.println("Заменено: " + path.get(i) + " и " + path.get(j)); // Логируем замену
                }
            }
        }

        if (path.equals(previousPath)) {
            System.out.println("Путь не изменился, завершаем оптимизацию."); // Логируем остановку
            break;
        }
    }

    if (iteration >= maxIterations) {
        System.out.println("Достигнут предел итераций."); // Уведомление о достижении лимита
    }

    return path;
}



    // Метод проверки улучшения маршрута
//    private static boolean swapImprovesPath(Graph graph, List<String> path, int i, int j) {
//        String from = path.get(i - 1);
//        String to = path.get(j + 1);
//
//        String currentI = path.get(i);
//        String currentJ = path.get(j);
//
//        int currentDistance = graph.getNeighbors(from).get(currentI) +
//                graph.getNeighbors(currentJ).get(to);
//        int swappedDistance = graph.getNeighbors(from).get(currentJ) +
//                graph.getNeighbors(currentI).get(to);
//        return swappedDistance < currentDistance;
//    }
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

    public static void printRoad(List<String> adress, Map<String, String> map, List<String> coordinates, Graph graph){
        for (String coordinate : coordinates){
            String key;
            for(Map.Entry<String, String> entry : map.entrySet()){
                if(entry.getValue().equals(coordinate)){
                    key = entry.getKey();
                    System.out.println(coordinate + " " + adress.indexOf(key) + " " + key);
                }
            }
        }

        int allDistance = 0; //общий адрес
        for(int i = 1; i <= coordinates.size()-1; i++){
            String currentPoint = coordinates.get(i);
            String previousPoint = coordinates.get(i-1);
            System.out.println(graph.getAdjacencyList().get(previousPoint).get(currentPoint));
            allDistance += graph.getAdjacencyList().get(previousPoint).get(currentPoint);
        }
        System.out.println("All road : " + allDistance);
    }
}

