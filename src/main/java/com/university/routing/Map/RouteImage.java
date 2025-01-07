//package com.university.routing.Map;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class RouteImageWithPoints {
//    public static void main(String[] args) throws Exception {
//        apiKey key = new apiKey();
//        String apiKey = key.readApiKey(); // Вставьте ваш API ключ
//
//        // Список точек
//        List<String> points = Arrays.asList(
//                "37.773972,-122.431297", // Начальная точка
//                "37.772837,-122.424317", // Промежуточная точка 1
//                "37.773000,-122.425000", // Промежуточная точка 2
//                "37.774159,-122.417907"  // Конечная точка
//        );
//
//        try {
//            // Шаг 1: Формируем параметры для Directions API
//            String origin = points.get(0); // Первая точка - начальная
//            String destination = points.get(points.size() - 1); // Последняя точка - конечная
//            String waypoints = points.subList(1, points.size() - 1) // Промежуточные точки
//                    .stream()
//                    .collect(Collectors.joining("|")); // Объединяем точки через "|"
//
//            // Формируем URL для Directions API
//            String directionsUrl = String.format(
//                    "https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&waypoints=%s&key=%s",
//                    origin, destination, waypoints, apiKey
//            );
//
//            // Шаг 2: Выполняем запрос к Directions API
//            String response = sendHttpRequest(directionsUrl);
//            String encodedPolyline = extractPolylineWithJackson(response);
//
//            // Шаг 3: Формируем URL для Static Maps API
//            String staticMapUrl = String.format(
//                    "https://maps.googleapis.com/maps/api/staticmap?size=600x400&path=enc:%s&key=%s",
//                    encodedPolyline, apiKey
//            );
//
//            System.out.println("Static Map URL: " + staticMapUrl);
//
//            // Вы можете открыть этот URL в браузере или использовать его для загрузки изображения
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Метод для выполнения HTTP-запроса
//    private static String sendHttpRequest(String urlString) throws Exception {
//        URL url = new URL(urlString);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String inputLine;
//        StringBuilder content = new StringBuilder();
//
//        while ((inputLine = in.readLine()) != null) {
//            content.append(inputLine);
//        }
//
//        in.close();
//        conn.disconnect();
//
//        return content.toString();
//    }
//
//    // Метод для извлечения закодированной линии Polyline из JSON-ответа Directions API с использованием Jackson
//    private static String extractPolylineWithJackson(String jsonResponse) {
//        String polyline = "";
//
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(jsonResponse);
//            JsonNode routes = root.get("routes");
//
//            if (routes.isArray() && routes.size() > 0) {
//                JsonNode route = routes.get(0);
//                JsonNode overviewPolyline = route.get("overview_polyline");
//                polyline = overviewPolyline.get("points").asText();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return polyline;
//    }
//}
