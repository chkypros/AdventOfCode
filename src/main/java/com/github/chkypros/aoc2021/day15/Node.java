package com.github.chkypros.aoc2021.day15;

import com.github.chkypros.aoc2021.day5.Point;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {
    private final Set<Node> neighbours = new HashSet<>();
    private final Point position;
    private final int risk;

    private Node(Point position, int risk) {
        this.position = position;
        this.risk = risk;
    }

    public static Node atPosition(Point position, int risk) {
        return new Node(position, risk);
    }

    public static Node atPosition(int x, int y, int risk) {
        return atPosition(Point.of(x, y), risk);
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

    public int getRisk() {
        return risk;
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
