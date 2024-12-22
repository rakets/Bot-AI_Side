package com.university.routing;

import com.university.routing.Map.GeocodingService;

public class Main {
    public static void main(String[] args) {
        try {
            String coordinates = GeocodingService.getCoordinates("Campobasso, Italy, Centro del Molise");
            System.out.println("Coordinates: " + coordinates);
//            System.out.println("token: " + GeocodingService.readApiKey() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
