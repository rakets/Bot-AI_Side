package com.university.routing.algorithms;

import java.util.HashMap;
import java.util.List;

import com.university.routing.Map.*;
import com.university.routing.models.*;
import com.university.routing.algorithms.*;
import java.util.*;
public class UseAlgorithms {
    public void chooseAlgorithm(List<String> adress){
        if(adress.size() > 15){
            lSearch(adress);
        } else {
            genAlg(adress);
        }
    }
//------------------------ Genetic Algorithm --------------------------------
    public List<String> genAlg(List<String> adress){
        // Карта для хранения адресов и их координат
        Map<String, String> map = new HashMap<>();
        for (String adres : adress) {
            try {
                String coordinates = GeocodingService.getCoordinates(adres);
                map.put(adres, coordinates);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Координаты: " + map);

        // Инициализация графа
        List<String> points = new ArrayList<>(map.keySet());
        Graph graph = new Graph();

        // Заполнение графа рёбрами
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                String point1 = points.get(i);
                String point2 = points.get(j);

                String coord1 = map.get(point1);
                String coord2 = map.get(point2);

                try {
                    int distance = DistanceMatrixService.getDistance(coord1, coord2);
                    graph.addEdge(coord1, coord2, distance);
                    graph.addEdge(coord2, coord1, distance);
                } catch (Exception e) {
                    System.out.println("Ошибка при добавлении ребра между " + point1 + " и " + point2);
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Граф с рёбрами: " + graph);

        // Решение задачи TSP с помощью генетического алгоритма
        System.out.println("Запуск генетического алгоритма...");
        List<String> optimizedRoute = TSPGeneticSolver.solveTSP(graph, new ArrayList<>(map.values()));

// Убедимся, что маршрут начинается и заканчивается в Campobasso, Italy, Via Campania, 17
        String startPoint = map.get(adress.get(0));
// Если маршрут не начинается с заданной точки, переставим её в начало
        if (!optimizedRoute.get(0).equals(startPoint)) {
            optimizedRoute.remove(startPoint);
            optimizedRoute.add(0, startPoint);
        }
// Если маршрут не заканчивается заданной точкой, добавим её в конец
        if (!optimizedRoute.get(optimizedRoute.size() - 1).equals(startPoint)) {
            optimizedRoute.add(startPoint);
        }
// Печать оптимизированного маршрута
        System.out.println("Оптимизированный маршрут:");
        TSPGeneticSolver.printSolution(optimizedRoute, graph);
        System.out.println("Coordinate of road : " + optimizedRoute);

        List<String> roadPoints = new ArrayList<>();
// Сопоставление маршрута с исходными адресами
        System.out.println("Маршрут (адреса):");
        for (String coord : optimizedRoute) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().equals(coord)) {
                    System.out.println(entry.getKey());
                    roadPoints.add(entry.getKey());
                    break;
                }
            }
        }
//        System.out.println("Points of road : " + roadPoints);
        return roadPoints;
    }
//------------------------ Genetic Algorithm --------------------------------

//------------------------ A* ----------------------------------------------
//    public List<String> aStar(List<String> adress){
    public void aStar(List<String> adress, String startPoint, String finishPoint){
        // Карта для хранения адресов и их координат
        Map<String, String> map = new HashMap<>();
        for (String adres : adress) {
            try {
                String coordinates = GeocodingService.getCoordinates(adres);
                map.put(adres, coordinates);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Координаты: " + map);

        // Инициализация графа
        List<String> points = new ArrayList<>(map.keySet());
        Graph graph = new Graph();

        // Заполнение графа рёбрами
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                String point1 = points.get(i);
                String point2 = points.get(j);

                String coord1 = map.get(point1);
                String coord2 = map.get(point2);

                try {
                    int distance = DistanceMatrixService.getDistance(coord1, coord2);
                    graph.addEdge(coord1, coord2, distance);
                    graph.addEdge(coord2, coord1, distance);
                } catch (Exception e) {
                    System.out.println("Ошибка при добавлении ребра между " + point1 + " и " + point2);
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Граф с рёбрами: " + graph);

        // Определение начальной и конечной точки
//        String startNode = map.get(adress.get(0));
        String startNode = map.get(startPoint);
//        String goalNode = map.get(adress.get(adress.size()-1));
        String goalNode = map.get(finishPoint);

        // Вызов A* для поиска кратчайшего пути
        List<String> path = AStarAlgorithm.findShortestPath(graph, startNode, goalNode);
        System.out.println("Кратчайший путь: " + path);

        // Вывод маршрута с адресами
        System.out.println("Маршрут (адреса):");
        for (String coord : path) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().equals(coord)) {
                    System.out.println(entry.getKey());
                    break;
                }
            }
        }
    }
//------------------------ A* ----------------------------------------------

//------------------------ LocalSearch -------------------------
    public void lSearch(List<String> adress) {
        // Карта для хранения адресов и их координат
        Map<String, String> map = new HashMap<>();
        for (String adres : adress) {
            try {
                String coordinates = GeocodingService.getCoordinates(adres);
                map.put(adres, coordinates);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Координаты: " + map);

        // Инициализация графа
        List<String> points = new ArrayList<>(map.keySet());
        Graph graph = new Graph();

        // Заполнение графа рёбрами
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                String point1 = points.get(i);
                String point2 = points.get(j);

                String coord1 = map.get(point1);
                String coord2 = map.get(point2);

                try {
                    int distance = DistanceMatrixService.getDistance(coord1, coord2);
                    graph.addEdge(coord1, coord2, distance);
                    graph.addEdge(coord2, coord1, distance);
                } catch (Exception e) {
                    System.out.println("Ошибка при добавлении ребра между " + point1 + " и " + point2);
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Граф с рёбрами: " + graph);
        // Применение локального поиска для оптимизации пути
        List<String> optimizedPath = localSearch.applyLocalSearch(graph, new ArrayList<>(map.values()));

        // Удаление всех дополнительных посещений начальной точки, кроме первой и последней
        String startPoint = map.get("Campobasso, Italy, Via Campania, 17");
        optimizedPath.removeIf(point -> point.equals(startPoint) && !optimizedPath.get(0).equals(point) && !optimizedPath.get(optimizedPath.size() - 1).equals(point));

        // Убедиться, что маршрут начинается и заканчивается одной и той же точкой
        if (!optimizedPath.get(0).equals(startPoint)) {
            optimizedPath.add(0, startPoint);
        }
        if (!optimizedPath.get(optimizedPath.size() - 1).equals(startPoint)) {
            optimizedPath.add(startPoint);
        }

        System.out.println("Оптимизированный путь: " + optimizedPath);

        // Вывод начальной и конечной точки маршрута для проверки
        System.out.println("Начальная точка маршрута: " + optimizedPath.get(0));
        System.out.println("Конечная точка маршрута: " + optimizedPath.get(optimizedPath.size() - 1));

        // Печать маршрута с адресами
        System.out.println("Маршрут (адреса):");
//        localSearch.printRoad(adress, map, optimizedPath, graph);
        System.out.println(localSearch.printRoad(adress, map, optimizedPath, graph));
    }
//------------------------ LocalSearch -------------------------

}


