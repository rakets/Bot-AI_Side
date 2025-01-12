package com.university.routing;

import com.university.routing.algorithms.*;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        // Список адресов
        List<String> adressPoint = new ArrayList<>();
        adressPoint.add("Campobasso, Italy, Via Campania, 17");
        adressPoint.add("Campobasso, Italy, Veriaffari Campobasso");
        adressPoint.add("Campobasso, Italy, Via San Giovanni Dei Gelsi, 37");
        adressPoint.add("Campobasso, Italy, Direzione Regionale del Molise e Comando Provinciale di Campobasso dei Vigili del Fuoco");
        adressPoint.add("Campobasso, Italy, Castello Monforte");
        adressPoint.add("Campobasso, Italy, Museo dei Misterи");
        adressPoint.add("Campobasso, Italy, Ristorante Pizzeria Villa dei Conti");
        adressPoint.add("Campobasso, Italy, Pianeta Fiorito");

        System.out.println("Список адресов: " + adressPoint);

        UseAlgorithms road = new UseAlgorithms();
//        road.genAlg(adressPoint);
//        road.chooseAlgorithm(adressPoint);

        road.aStar(adressPoint, adressPoint.get(0), adressPoint.get(6));
    }
}
