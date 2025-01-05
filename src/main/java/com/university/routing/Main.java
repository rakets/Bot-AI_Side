package com.university.routing;

import com.university.routing.Map.DistanceMatrixService;
import com.university.routing.Map.GeocodingService;

import com.university.routing.algorithms.AStarAlgorithm;
import com.university.routing.algorithms.localSearch;

import com.university.routing.models.Graph;

import java.util.*;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

//        try {
//            String coordinates = GeocodingService.getCoordinates("Campobasso, Italy, Centro del Molise");
//            System.out.println("Coordinates: " + coordinates);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//----------------------------------------------------------------------------------

        List<String> adress = new ArrayList<>();
        adress.add("Campobasso, Italy, Centro del Molise");
        adress.add("Campobasso, Italy, Veriaffari Campobasso");
        adress.add("Campobasso, Italy, Via Campania, 17");
        adress.add("Campobasso, Italy, Direzione Regionale del Molise e Comando Provinciale di Campobasso dei Vigili del Fuoco");

        adress.add("Campobasso, Italy, Castello Monforte");
        adress.add("Campobasso, Italy, Museo dei Misteri");
        adress.add("Campobasso, Italy, Ristorante Pizzeria Villa dei Conti");
        System.out.println(adress);

//-----------------------------------------------------------------------------------

//        for (int i = 0; i <= adress.size()-1; i++){
//            try {
//            String coordinates = GeocodingService.getCoordinates(adress.get(i));
//            adress.set(i, coordinates);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(adress);
//
//        try {
//            int distance = DistanceMatrixService.getDistance(adress.get(0), adress.get(1));
//            System.out.println("Distance (meters): " + distance);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

// -------------------------------------------------------------

//        List<String> adress = new ArrayList<>();
//        adress.add("A");
//        adress.add("B");
//        adress.add("C");
//        adress.add("D");
//        System.out.println(adress);

        Map<String, String> map = new HashMap<>();

        for(String adres : adress) {
//            int randomNumber = ThreadLocalRandom.current().nextInt(10, 50 + 1); // [min, max]
//            map.put(adres, String.valueOf(randomNumber));

//---------------------------------------------------------------------------------
            try{
                String coordinates = GeocodingService.getCoordinates(adres);
                map.put(adres, coordinates);
                System.out.println(adres + " " + coordinates);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
//---------------------------------------------------------------------------------
        System.out.println(map);

        Graph graph = new Graph();
//        Set<String> keySet = map.keySet();
//        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Collection<String> values = map.values();
        System.out.println(values);

//        --------------------------------------
//        for(String coordinat1 : keySet){
        for(String coordinat1 : values){
//            for(String coordinat2 : keySet){
            for(String coordinat2 : values){
                if(!coordinat1.equals(coordinat2)){
//                    int distanse = getDistans(coordinat1, coordinat2);
//                    graph.addEdge(coordinat1, coordinat2, distanse);

//                   ----------------------------
                    try {
                        int distanc = DistanceMatrixService.getDistance(coordinat1, coordinat2);
                        graph.addEdge(coordinat1, coordinat2, distanc);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
//                   ---------------------------
                }
            }
        }
        System.out.println(graph);

        // Применение алгоритма A* для нахождения пути между двумя точками
        String startNode = map.get(adress.get(0)); // пример начальной координаты
        System.out.println(startNode);
        String goalNode = map.get(adress.get(3)); // пример конечной координаты
        System.out.println(goalNode);

        List<String> initialPath = AStarAlgorithm.findShortestPath(graph, startNode, goalNode);
        System.out.println("Initial Path: " + initialPath);

        // Оптимизация пути с помощью локального поиска
        List<String> optimizedPath = localSearch.optimizePath(graph, initialPath);
        System.out.println("Optimized Path: " + optimizedPath);




        // Укажите начальную и конечную точки
//        String start = map.get(adress.get(0)); // Например, первая точка
//        System.out.println(start);
//        String goal = map.get(adress.get(adress.size()-1));  // Например, последняя точка
//        System.out.println(goal);

//        List<String> path = AStarAlgorithm.findShortestPath(graph, map, start, goal);
//        System.out.println("Shortest path: " + path);

    }

//    public static int getDistans(String firsPoint, String secondPoint){
//        int randomNumber = ThreadLocalRandom.current().nextInt(3, 10 + 1); // [min, max]
//        return randomNumber;
//    }

//        List<String> addresses = Arrays.asList(
//                "Campobasso, Italy, Centro del Molise",
//                "Campobasso, Italy, Veriaffari Campobasso",
//                "Campobasso, Italy, Via Campania, 17",
//                "Campobasso, Italy, Direzione Regionale del Molise e Comando Provinciale di Campobasso dei Vigili del Fuoco"
//        );
//
//        Map<String, String> map = new HashMap<>();
//        for (String address : addresses) {
//            try {
//                String coordinates = GeocodingService.getCoordinates(address);
//                map.put(address, coordinates);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        Graph graph = new Graph();
//        for (String coord1 : map.values()) {
//            for (String coord2 : map.values()) {
//                if (!coord1.equals(coord2)) {
//                    try {
//                        int distance = DistanceMatrixService.getDistance(coord1, coord2);
//                        graph.addEdge(coord1, coord2, distance);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        System.out.println(graph);
//
//        // Укажите начальную и конечную точки
//        String start = map.get(addresses.get(0)); // Например, первая точка
//        String goal = map.get(addresses.get(3));  // Например, последняя точка
//
//        List<String> path = AStarAlgorithm.findShortestPath(graph, map, start, goal);
//        System.out.println("Shortest path: " + path);
//    }
}
