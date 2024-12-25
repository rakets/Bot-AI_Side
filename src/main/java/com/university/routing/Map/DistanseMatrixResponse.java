package com.university.routing.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

// POJO-a class for parsing JSON(for mapping JSON responses, the Google Distance Matrix API. These classes will be used by Jackson.)
@JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем неизвестные свойства
public class DistanseMatrixResponse {
    private List<String> destination_addresses;
    private List<String> origin_addresses;
    private List<Row> rows;

    public List<String> getDestinationAddresses() {
        return destination_addresses;
    }

    public void setDestinationAddresses(List<String> destination_addresses) {
        this.destination_addresses = destination_addresses;
    }

    public List<String> getOriginAddresses() {
        return origin_addresses;
    }

    public void setOriginAddresses(List<String> origin_addresses) {
        this.origin_addresses = origin_addresses;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public static class Row {
        private List<Element> elements;

        public List<Element> getElements() {
            return elements;
        }

        public void setElements(List<Element> elements) {
            this.elements = elements;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Element {
        private Distance distance;
        private Duration duration; // Using the custom Duration class

        public Distance getDistance() {
            return distance;
        }

        public void setDistance(Distance distance) {
            this.distance = distance;
        }

        public Duration getDuration() {
            return duration;
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Distance {
        private int value; // Distance value in meters
        private String text;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Duration { // Новый класс для десериализации duration
        private int value; // Duration value in seconds
        private String text;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
