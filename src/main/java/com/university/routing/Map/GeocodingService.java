package com.university.routing.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GeocodingService { // Class for converting addresses to coordinates
//    private static final String API_KEY;

    public static String getCoordinates(String address) throws Exception {  //method for get coordinates of address
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                URLEncoder.encode(address, StandardCharsets.UTF_8) + "&key=" + readApiKey();

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parse JSON using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(response.toString());
        JsonNode location = json.path("results").get(0).path("geometry").path("location");

        double lat = location.path("lat").asDouble();
        double lng = location.path("lng").asDouble();

        return lat + "," + lng;
    }
    public static String readApiKey() throws IOException {            //method for reading API-key
        try (BufferedReader reader = new BufferedReader(new FileReader("D:/WSPA/erasmus/Subject Molise University/Automated software delivery/API_token_cloud_google.txt"))) {
            return reader.readLine();
        }
    }
}
