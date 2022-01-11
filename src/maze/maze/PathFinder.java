package maze.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PathFinder {

    private static boolean[] visitedNodes;
    private static int holyGrailNode;
    private static Graph maze;

    public static Graph findPath(Graph maze, int node1, int node2) throws Exception {
        visitedNodes = new boolean[maze.getNodesCount()];
        holyGrailNode = node2;

        PathFinder.maze = maze;

        Graph result = new Graph(maze.getNodesCount());

        List<Integer> path = getSubPath(node1);

        if(path == null) {
            throw new Exception("Something went wrong");
        }

        for (int i = 0; i < path.size() - 1; i++) {
            result.setEdgeWeight(path.get(i), path.get(i + 1), 1);
        }

        return result;
    }


    public static List<Integer> findPathAsList(Graph maze, int node1, int node2) throws Exception {
        visitedNodes = new boolean[maze.getNodesCount()];
        holyGrailNode = node2;

        PathFinder.maze = maze;

        List<Integer> path = getSubPath(node1);

        if(path == null) {
            throw new Exception("Something went wrong");
        }

        return path;
    }


    public static List<Integer> getSubPath(int startNode) {
        List<Integer> path = new ArrayList<>();
        path.add(startNode);

        Map<Integer, Double> neighborNodes = maze.getNeighborNodes(startNode);

        for (Map.Entry<Integer, Double> entry : neighborNodes.entrySet()) {
            if (!visitedNodes[entry.getKey()]) {

                visitedNodes[entry.getKey()] = true;

                if (entry.getKey() == holyGrailNode) {
                    path.add(holyGrailNode);
                    return path;
                }

                List<Integer> subPath = getSubPath(entry.getKey());
                if (subPath != null) {
                    path.addAll(subPath);
                    return path;
                }
            }
        }

        return null;
    }

}
