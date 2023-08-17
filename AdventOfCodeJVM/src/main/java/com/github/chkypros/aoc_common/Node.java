package com.github.chkypros.aoc_common;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {
    private final Set<Node> neighbours = new HashSet<>();
    private final Point position;
    private final int cost;

    private Node(Point position, int cost) {
        this.position = position;
        this.cost = cost;
    }

    public static Node atPosition(Point position, int cost) {
        return new Node(position, cost);
    }

    public static Node atPosition(int x, int y, int cost) {
        return atPosition(Point.of(x, y), cost);
    }

    public void addNeighbour(Node neighbour) {
        neighbours.add(neighbour);
    }

    public Set<Node> getNeighbours() {
        return neighbours;
    }

    public Point getPosition() {
        return position;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return position.equals(node.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
