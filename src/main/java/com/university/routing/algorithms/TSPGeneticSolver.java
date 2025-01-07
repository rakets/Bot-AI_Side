package com.university.routing.algorithms;

import com.university.routing.models.Graph;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TSPGeneticSolver {
    // Параметры GA
    private static final int POPULATION_SIZE = 100;
    private static final int GENERATIONS = 1000;
    private static final double MUTATION_RATE = 0.02;
    private static final int SELECTION_SIZE = POPULATION_SIZE / 2; // Размер отобранной популяции

    public static List<String> solveTSP(Graph graph, List<String> points) {
        List<List<String>> population = initializePopulation(points);
        Random random = new Random();

        for (int generation = 0; generation < GENERATIONS; generation++) {
            // Оценка приспособленности
            Map<List<String>, Integer> fitnessMap = evaluateFitness(graph, population);

            // Селекция
            List<List<String>> populationRoutes = new ArrayList<>(population);
            List<List<String>> selectedPopulation = selectPopulation(populationRoutes, SELECTION_SIZE);

            // Кроссовер
            List<List<String>> nextGeneration = new ArrayList<>();
            while (nextGeneration.size() < POPULATION_SIZE) {
                List<String> parent1 = selectedPopulation.get(random.nextInt(selectedPopulation.size()));
                List<String> parent2 = selectedPopulation.get(random.nextInt(selectedPopulation.size()));
                nextGeneration.addAll(crossover(parent1, parent2));
            }

            // Применяем A* и Local Search
            for (List<String> individual : nextGeneration) {
                // Улучшаем маршрут с помощью A*
                individual = applyAStar(graph, individual);
                // Применяем Local Search для улучшения маршрута
                applyLocalSearch(graph, individual);
            }

            // Мутация
            for (List<String> individual : nextGeneration) {
                if (random.nextDouble() < MUTATION_RATE) {
                    mutate(individual);
                }
            }

            population = nextGeneration;
        }

        // Возврат лучшего решения
        Map<List<String>, Integer> finalFitness = evaluateFitness(graph, population);
        return getBestSolution(finalFitness);
    }


//    public static List<String> solveTSP(Graph graph, List<String> points) {
//        List<List<String>> population = initializePopulation(points);
//        Random random = new Random();
//
//        // Размер отобранной популяции
//        int selectionSize = POPULATION_SIZE / 2;
//
//        for (int generation = 0; generation < GENERATIONS; generation++) {
//            // Оценка приспособленности
//            Map<List<String>, Integer> fitnessMap = evaluateFitness(graph, population);
//
//            // Селекция
//            List<List<String>> populationRoutes = new ArrayList<>(population);
//            List<List<String>> selectedPopulation = selectPopulation(populationRoutes, selectionSize);
//
//            // Кроссовер
//            List<List<String>> nextGeneration = new ArrayList<>();
//            while (nextGeneration.size() < POPULATION_SIZE) {
//                List<String> parent1 = selectedPopulation.get(random.nextInt(selectedPopulation.size()));
//                List<String> parent2 = selectedPopulation.get(random.nextInt(selectedPopulation.size()));
//                nextGeneration.addAll(crossover(parent1, parent2));
//            }
//            // Мутация
//            for (List<String> individual : nextGeneration) {
//                if (random.nextDouble() < MUTATION_RATE) {
//                    mutate(individual);
//                }
//            }
//            population = nextGeneration;
//        }
//
//        // Возврат лучшего решения
//        Map<List<String>, Integer> finalFitness = evaluateFitness(graph, population);
//        return getBestSolution(finalFitness);
//    }

    private static List<List<String>> initializePopulation(List<String> points) {
        List<List<String>> population = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            List<String> individual = new ArrayList<>(points);
            Collections.shuffle(individual);
            population.add(individual);
        }
        return population;
    }

    private static Map<List<String>, Integer> evaluateFitness(Graph graph, List<List<String>> population) {
        Map<List<String>, Integer> fitnessMap = new HashMap<>();
        for (List<String> individual : population) {
            int distance = calculateTotalDistance(graph, individual);
            fitnessMap.put(individual, distance);
        }
        return fitnessMap;
    }

    private static List<List<String>> selectPopulation(List<List<String>> population, int selectionSize) {
        if (population.isEmpty()) {
            throw new IllegalArgumentException("Population must not be empty.");
        }
        if (selectionSize <= 0) {
            throw new IllegalArgumentException("Selection size must be positive.");
        }

        List<List<String>> selected = new ArrayList<>();
        for (int i = 0; i < selectionSize; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(population.size());
            selected.add(population.get(randomIndex));
        }
        return selected;
    }

    private static List<List<String>> crossover(List<String> parent1, List<String> parent2) {
        int size = parent1.size();
        Random random = new Random();
        int start = random.nextInt(size);
        int end = random.nextInt(size - start) + start;

        // Создание ребенка
        List<String> child1 = new ArrayList<>(Collections.nCopies(size, null));
        List<String> child2 = new ArrayList<>(Collections.nCopies(size, null));

        for (int i = start; i <= end; i++) {
            child1.set(i, parent1.get(i));
            child2.set(i, parent2.get(i));
        }

        fillRemainingGenes(parent1, child1, start, end);
        fillRemainingGenes(parent2, child2, start, end);

        return Arrays.asList(child1, child2);
    }

    private static void fillRemainingGenes(List<String> parent, List<String> child, int start, int end) {
        int size = parent.size();
        int index = (end + 1) % size;

        for (String gene : parent) {
            if (!child.contains(gene)) {
                child.set(index, gene);
                index = (index + 1) % size;
            }
        }
    }

    private static void mutate(List<String> individual) {
        Random random = new Random();
        int i = random.nextInt(individual.size());
        int j = random.nextInt(individual.size());
        Collections.swap(individual, i, j);
    }

    private static int calculateTotalDistance(Graph graph, List<String> path) {
        int totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String from = path.get(i);
            String to = path.get(i + 1);
            totalDistance += graph.getNeighbors(from).getOrDefault(to, Integer.MAX_VALUE);
        }
        return totalDistance;
    }

    private static List<String> getBestSolution(Map<List<String>, Integer> fitnessMap) {
        return fitnessMap.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(Collections.emptyList());
    }

    // Метод для применения A*
    private static List<String> applyAStar(Graph graph, List<String> path) {
        List<String> improvedPath = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            String start = path.get(i);
            String goal = path.get(i + 1);
            List<String> partialPath = AStarAlgorithm.findShortestPath(graph, start, goal);
            improvedPath.addAll(partialPath.subList(1, partialPath.size()));  // Чтобы избежать дублирования точки
        }
        return improvedPath;
    }

    // Метод для применения Local Search
    private static void applyLocalSearch(Graph graph, List<String> path) {
        localSearch.optimizePath(graph, path);
    }

    public static void printSolution(List<String> solution, Graph graph) {
        int totalDistance = calculateTotalDistance(graph, solution);

        System.out.println("Лучший найденный маршрут:");
        for (int i = 0; i < solution.size() - 1; i++) {
            System.out.println(solution.get(i) + " -> " + solution.get(i + 1));
        }
        System.out.println("Общее расстояние: " + totalDistance);
    }
}
