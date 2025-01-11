package com.university.routing.models;

public class Node implements Comparable<Node>{
    private String id;
    private int gCost; // Cost from start to this node
    private int hCost; // Heuristic (estimated cost to goal)

    public Node(String id, int gCost, int hCost) {
        this.id = id;
        this.gCost = gCost;
        this.hCost = hCost;
    }

    public int getFCost() {
        return gCost + hCost;
    }

    public String getId() {
        return id;
    }
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.getFCost(), other.getFCost());
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return id.equals(node.id);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
