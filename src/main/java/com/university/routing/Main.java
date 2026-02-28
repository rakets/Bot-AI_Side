package com.university.routing;

import com.university.routing.algorithms.*;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        // Список адресов
        List<String> adressPoint = new ArrayList<>();
        adressPoint.add("Vitebsk, Belarus, ВГТУ");
        adressPoint.add("Vitebsk, Belarus, Volosovo");
        adressPoint.add("Vitebsk, Belarus, Sosnovka");
        adressPoint.add("Vitebsk, Belarus, Moskovskiy 39");
        adressPoint.add("Vitebsk, Belarus, Frunze 58");


        System.out.println("Список адресов: " + adressPoint);

        UseAlgorithms road = new UseAlgorithms();
//        road.genAlg(adressPoint);
//        road.chooseAlgorithm(adressPoint);

        road.aStar(adressPoint, adressPoint.get(0), adressPoint.get(3));
    }
}
