package com.university.routing.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DistanceMatrixService { // calculating distances between points
private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static int getDistance(String origin, String destination) throws Exception {
        apiKey key = new apiKey();
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" +
                URLEncoder.encode(origin, "UTF-8") +
                "&destinations=" + URLEncoder.encode(destination, "UTF-8") +
                "&key=" + key.readApiKey();

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

//        Parsing the JSON response using Jackson
        DistanseMatrixResponse responseObject = OBJECT_MAPPER.readValue(response.toString(), DistanseMatrixResponse.class);

//        Extracting the distance from the response
        if (!responseObject.getRows().isEmpty() && !responseObject.getRows().get(0).getElements().isEmpty()) {
//            System.out.println(response.toString());
            return responseObject.getRows().get(0).getElements().get(0).getDistance().getValue();
        } else {
            throw new RuntimeException("Invalid response from Google Distance Matrix API");
        }
    }
}

