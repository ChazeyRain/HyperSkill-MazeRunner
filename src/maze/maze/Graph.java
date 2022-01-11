package maze.maze;

import java.io.Serializable;
import java.util.*;

public class Graph implements Serializable {

    private final int nodes;
    private final List<Map<Integer, Double>> adjList;


    public Graph(int nodes) {
        this.nodes = nodes;
       adjList = new ArrayList<>(nodes);

        for (int i = 0; i < nodes; i++) {
            adjList.add(new HashMap<>());
        }

    }


    public int getNodesCount() {
        return nodes;
    }


    public void setEdgeWeight(int node1, int node2, double weight) {
        adjList.get(node1).put(node2, weight);
        adjList.get(node2).put(node1, weight);
    }


    public double getEdgeWeight(int node1, int node2) {
        return adjList.get(node1).getOrDefault(node2, 0D);
    }


    public List<Map<Integer, Double>> getAdjList() {
        return adjList;
    }


    public Map<Integer, Double> getNeighborNodes(int node) {
        return adjList.get(node);
    }


    public Graph getMinimalSpanningTree() {

        Set<Integer> connectedNodes = new TreeSet<>();
        connectedNodes.add(0);

        Graph spanningTree = new Graph(this.nodes);

        while (connectedNodes.size() != this.nodes) {

            double minWeight = Double.POSITIVE_INFINITY;
            int node1 = -1;
            int node2 = -1;

            for (Integer node : connectedNodes) {

                for (Map.Entry<Integer, Double> entry : adjList.get(node).entrySet()) {
                    if (!connectedNodes.contains(entry.getKey()) && minWeight > entry.getValue()) {
                        node1 = node;
                        node2 = entry.getKey();
                        minWeight = entry.getValue();
                    }
                }
            }

            connectedNodes.add(node2);

            spanningTree.setEdgeWeight(node1, node2, minWeight);
        }

        return spanningTree;
    }
}
