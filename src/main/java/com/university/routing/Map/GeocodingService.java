package com.university.routing.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GeocodingService { // Class for converting addresses to coordinates
    public static String getCoordinates(String address) throws Exception {
        apiKey key = new apiKey();

        String address1 = "Vitebks, Belarus, Moskovskiy 39";
        System.out.println("adress: " + address1);

        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                URLEncoder.encode(address1, StandardCharsets.UTF_8) +
                "&key=" + key.readApiKey();
        System.out.println("url: " + url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();

        System.out.println("response: " + response);

        String inputLine;
        while ((inputLine = in.readLine()) != null) response.append(inputLine);
        in.close();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(response.toString());

        // Логируем полный JSON
        System.out.println("Geocoding response for: " + address);
        System.out.println(json.toPrettyString());

        // Проверка статуса
        String status = json.path("status").asText();
        if (!"OK".equals(status)) {
            System.out.println("❌ Geocoding failed for: " + address + " | Status: " + status);
            return null;
        }

        JsonNode results = json.path("results");
        if (results.isEmpty()) {
            System.out.println("❌ No results found for: " + address);
            return null;
        }

        JsonNode location = results.get(0).path("geometry").path("location");
        double lat = location.path("lat").asDouble();
        double lng = location.path("lng").asDouble();

        return lat + "," + lng;
    }
}
