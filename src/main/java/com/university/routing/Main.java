package com.university.routing;

import com.university.routing.Map.DistanceMatrixService;
import com.university.routing.Map.GeocodingService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        try {
//            String coordinates = GeocodingService.getCoordinates("Campobasso, Italy, Centro del Molise");
//            System.out.println("Coordinates: " + coordinates);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        List<String> adress = new ArrayList<>();
        adress.add("Campobasso, Italy, Centro del Molise");
        adress.add("Campobasso, Italy, Veriaffari Campobasso");
        System.out.println(adress);

        for (int i = 0; i <= adress.size()-1; i++){
            try {
            String coordinates = GeocodingService.getCoordinates(adress.get(i));
            adress.set(i, coordinates);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(adress);

        try {
            int distance = DistanceMatrixService.getDistance(adress.get(0), adress.get(1));
            System.out.println("Distance (meters): " + distance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
