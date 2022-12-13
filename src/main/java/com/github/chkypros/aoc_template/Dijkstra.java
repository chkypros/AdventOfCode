package com.github.chkypros.aoc_template;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Dijkstra {
    private final Set<Node> queue = new HashSet<>();
    private final long[][] totalCost;

    public Dijkstra(Node[][] nodes, Point source) {

        this.totalCost = new long[nodes.length][nodes[0].length];
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                this.totalCost[i][j] = Integer.MAX_VALUE;
                this.queue.add(nodes[i][j]);
            }
        }
        this.totalCost[source.row][source.column] = 0;
    }

    public void findShortestPaths() {
        while (!queue.isEmpty()) {
            Node minTotalCostNode = getNextMinTotalCostNode();
            queue.remove(minTotalCostNode);

            minTotalCostNode.getNeighbours()
                .forEach(node -> updateTotalCost(node, minTotalCostNode));
        }
    }

    private Node getNextMinTotalCostNode() {
        return queue.stream().min(Comparator.comparingLong(this::getTotalCost)).get();
    }

    private void updateTotalCost(Node node, Node minTotalCostNode) {
        long alternativeCost = getTotalCost(minTotalCostNode) + node.getCost();
        if (alternativeCost < getTotalCost(node)) {
            setTotalCost(node, alternativeCost);
        }
    }

    private long getTotalCost(Node node) {
        return totalCost[node.getPosition().row][node.getPosition().column];
    }

    private void setTotalCost(Node node, long cost) {
        totalCost[node.getPosition().row][node.getPosition().column] = cost;
    }

    public Long getDistanceOf(Point destination) {
        return totalCost[destination.row][destination.column];
    }

    // Dijkstra's pseudo-code shamelessly copied from https://brilliant.org/wiki/dijkstras-short-path-finder/
/*
function Dijkstra(Graph, source):
       dist[source]  := 0                     // Distance from source to source is set to 0
       for each vertex v in Graph:            // Initializations
           if v ≠ source
               dist[v]  := infinity           // Unknown distance function from source to each node set to infinity
           add v to Q                         // All nodes initially in Q

      while Q is not empty:                  // The main loop
          v := vertex in Q with min dist[v]  // In the first run-through, this vertex is the source node
          remove v from Q

          for each neighbor u of v:           // where neighbor u has not yet been removed from Q.
              alt := dist[v] + length(v, u)
              if alt < dist[u]:               // A shorter path to u has been found
                  dist[u]  := alt            // Update distance of u

      return dist[]
  end function
*/
}
