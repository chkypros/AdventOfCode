package com.github.chkypros.aoc2021.day12;

import java.util.HashSet;
import java.util.Set;

public class Cave {
    private final String name;
    private final Set<Cave> connections = new HashSet<>();

    public Cave(String name) {
        this.name = name;
    }

    public static Cave withName(String name) {
        return new Cave(name);
    }

    public void addConnection(Cave cave) {
        connections.add(cave);
    }

    public String getName() {
        return name;
    }

    public Set<Cave> getConnections() {
        return connections;
    }

    @Override
    public String toString() {
        return "Cave{" +
                "name='" + name + '\'' +
                '}';
    }
}
