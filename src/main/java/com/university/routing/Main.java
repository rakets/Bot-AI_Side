//package com.university.routing;
//
//import com.university.routing.Map.DistanceMatrixService;
//import com.university.routing.Map.GeocodingService;
//
//import com.university.routing.algorithms.AStarAlgorithm;
//import com.university.routing.algorithms.localSearch;
//
//import com.university.routing.models.Graph;
//
//import java.util.*;
//
//import java.util.concurrent.ThreadLocalRandom;
//
//public class Main {
//    public static void main(String[] args) {
//        List<String> adress = new ArrayList<>();
//        adress.add("Campobasso, Italy, Via Campania, 17");
//        adress.add("Campobasso, Italy, Veriaffari Campobasso");
//        adress.add("Campobasso, Italy, Via Campania, 15");
//        adress.add("Campobasso, Italy, Direzione Regionale del Molise e Comando Provinciale di Campobasso dei Vigili del Fuoco");
//
//        adress.add("Campobasso, Italy, Castello Monforte");
//        adress.add("Campobasso, Italy, Museo dei Misteri");
//        adress.add("Campobasso, Italy, Ristorante Pizzeria Villa dei Conti");
//        adress.add("Campobasso, Italy, Pianeta Fiorito");
//
//        adress.add("Campobasso, Italy, Hotel Rinascimento");
//        adress.add("Campobasso, Italy, Contrada Colle Arso, 35/B");
//        adress.add("Campobasso, Italy, MORGIA - Climbing Experience");
//        adress.add("Campobasso, Italy, 4 Queens Brewery");
//
//        adress.add("Campobasso, Italy, Comune di Oratino");
//        adress.add("Campobasso, Italy, Decathlon Campobasso");
//        adress.add("Campobasso, Italy, Contrada Serrecchie, 3");
//        adress.add("Campobasso, Italy, Via Vittorio Alfieri, 80");
//
//        adress.add("Campobasso, Italy, Responsible Research Hospital");
//        adress.add("Campobasso, Italy, Celiaco.M S.R.L");
//        adress.add("Campobasso, Italy, Fisio Dinamic Gym");
//        adress.add("Campobasso, Italy, Via Monte Grappa, 43");
//
//        Map<String, String> map = new HashMap<>();
//
//        for (String adres : adress) {
//            try {
//                String coordinates = GeocodingService.getCoordinates(adres);
//                map.put(adres, coordinates);
////                System.out.println(adres + " " + coordinates);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
////---------------------------------------------------------------------------------
//        System.out.println(map);
//
//        Graph graph = new Graph();
//        Collection<String> valuesPoint = map.keySet();
//        List<String> values = new ArrayList<>(valuesPoint);
//        System.out.println(values);
//
//        for (String coordinat1 : values) {
//            for (String coordinat2 : values) {
//                if (!coordinat1.equals(coordinat2)) {
//                    try {
//                        int distanc = DistanceMatrixService.getDistance(map.get(coordinat1), map.get(coordinat2));
//                        graph.addEdge(map.get(coordinat1), map.get(coordinat2), distanc);
////                        System.out.println(coordinat1 + " / " + coordinat2 + " : " + distanc);
//                        System.out.println(values.indexOf(coordinat1) + " I " + values.indexOf(coordinat2) + " : " + distanc);
//                    } catch (IllegalArgumentException e) {
//                        System.out.println("Ошибка при добавлении ребра: " + e.getMessage());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        System.out.println(graph);
//
//        // Применение алгоритма A* для нахождения пути между двумя точками
//        String startNode = map.get(adress.get(5)); // пример начальной координаты
//        System.out.println(startNode);
//        String goalNode = map.get(adress.get(17)); // пример конечной координаты
//        System.out.println(goalNode);
//
//        List<String> initialPath = AStarAlgorithm.findShortestPath(graph, startNode, goalNode);
//        System.out.println("Initial Path: " + initialPath);
//
//        // Оптимизация пути с помощью локального поиска
//        List<String> optimizedPath = localSearch.optimizePath(graph, initialPath);
//        System.out.println("Optimized Path: " + optimizedPath); //оптимизированный путь
//        localSearch.printRoad(adress, map, optimizedPath, graph); //печать пути
//    }
//}

package com.university.routing;

import com.university.routing.Map.DistanceMatrixService;
import com.university.routing.Map.GeocodingService;

import com.university.routing.algorithms.TSPGeneticSolver;
import com.university.routing.models.Graph;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Список адресов
        List<String> adress = new ArrayList<>();
        adress.add("Campobasso, Italy, Via Campania, 17");
        adress.add("Campobasso, Italy, Veriaffari Campobasso");
        adress.add("Campobasso, Italy, Via Campania, 15");
        adress.add("Campobasso, Italy, Direzione Regionale del Molise e Comando Provinciale di Campobasso dei Vigili del Fuoco");
        adress.add("Campobasso, Italy, Castello Monforte");
        adress.add("Campobasso, Italy, Museo dei Misteri");
        adress.add("Campobasso, Italy, Ristorante Pizzeria Villa dei Conti");
        adress.add("Campobasso, Italy, Pianeta Fiorito");

//        adress.add("Campobasso, Italy, Hotel Rinascimento");
//        adress.add("Campobasso, Italy, Contrada Colle Arso, 35/B");
//        adress.add("Campobasso, Italy, MORGIA - Climbing Experience");
//        adress.add("Campobasso, Italy, 4 Queens Brewery");

//        adress.add("Campobasso, Italy, Comune di Oratino");
//        adress.add("Campobasso, Italy, Decathlon Campobasso");
//        adress.add("Campobasso, Italy, Contrada Serrecchie, 3");
//        adress.add("Campobasso, Italy, Via Vittorio Alfieri, 80");
//
//        adress.add("Campobasso, Italy, Responsible Research Hospital");
//        adress.add("Campobasso, Italy, Celiaco.M S.R.L");
//        adress.add("Campobasso, Italy, Fisio Dinamic Gym");
//        adress.add("Campobasso, Italy, Via Monte Grappa, 43");

        // Карта для хранения адресов и их координат
//        Map<String, String> map = new HashMap<>();
//        for (String adres : adress) {
//            try {
//                String coordinates = GeocodingService.getCoordinates(adres);
//                map.put(adres, coordinates);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("Координаты: " + map);

        // Создание графа
//        Graph graph = new Graph();
//        List<String> points = new ArrayList<>(map.keySet());
//        for (String point1 : points) {
//            for (String point2 : points) {
//                if (!point1.equals(point2)) {
//                    try {
//                        int distance = DistanceMatrixService.getDistance(map.get(point1), map.get(point2));
//                        graph.addEdge(map.get(point1), map.get(point2), distance);
//                    } catch (IllegalArgumentException e) {
//                        System.out.println("Ошибка при добавлении ребра: " + e.getMessage());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
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

        // Сохраняем порядок добавления точек в List
        List<String> points = new ArrayList<>(map.keySet());

        // Инициализация графа с сохранением порядка
        Graph graph = new Graph();

        // Добавление рёбер в граф с сохранением порядка
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                String point1 = points.get(i);
                String point2 = points.get(j);

                // Получаем координаты для точек
                String coord1 = map.get(point1);
                String coord2 = map.get(point2);

                try {
                    // Получаем расстояние между точками
                    int distance = DistanceMatrixService.getDistance(coord1, coord2);

                    // Добавляем рёбра в граф (с сохранением порядка)
                    graph.addEdge(coord1, coord2, distance);
                    graph.addEdge(coord2, coord1, distance); // Обратное ребро, так как граф неориентированный
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

        // Печать оптимизированного маршрута
        System.out.println("Оптимизированный маршрут:");
        TSPGeneticSolver.printSolution(optimizedRoute, graph);

        // Сопоставление маршрута с исходными адресами
        System.out.println("Маршрут (адреса):");
        for (String coord : optimizedRoute) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().equals(coord)) {
                    System.out.println(entry.getKey());
                    break;
                }
            }
        }
    }
}

