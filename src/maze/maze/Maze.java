package maze.maze;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Maze implements Serializable {

    private static final long serialVersionUID = 2L;

    private final boolean[][] cells;
    private final boolean[][] path;

    private Graph mazeGraph;

    int graphHeight;
    int graphWidth;

    private int start;
    private int finish;

    Maze(int width, int height) {
        cells = new boolean[height][width];
        path = new boolean[height][width];
        generateMaze(width, height);
    }

    private void generateMaze(int width, int height) {
        graphWidth = (width + 1) / 2;
        graphHeight = (height + 1) / 2;
        Graph graph = new Graph(graphWidth * graphHeight);

        Random random = new Random();

        for (int i = 0; i < graphHeight - 1; i++) {
            for (int j = 0; j < graphWidth - 1; j++) {
                graph.setEdgeWeight(i * graphWidth + j, i * graphWidth + j + 1, random.nextInt(5) + 1);
                graph.setEdgeWeight(i * graphWidth + j, (i + 1) * graphWidth + j, random.nextInt(5) + 1);
            }
        }

        graph.setEdgeWeight(graphHeight * graphWidth - 1, graphHeight * graphWidth - 2, random.nextInt(5) + 1);
        graph.setEdgeWeight(graphHeight * graphWidth - 1, graphHeight * graphWidth - graphWidth - 1, random.nextInt(5) + 1);

        mazeGraph = graph.getMinimalSpanningTree();

        for (int i = 0; i < graphHeight - 1; i++) {
            for (int j = 0; j < graphWidth - 1; j++) {
                cells[1 + i * 2][1 + j * 2] = true;

                if (mazeGraph.getEdgeWeight(i * graphWidth + j, i * graphWidth + j + 1) != 0) {
                    cells[i * 2 + 1][j * 2 + 2] = true;
                }
                if(mazeGraph.getEdgeWeight(i * graphWidth + j, (i + 1) * graphWidth + j) != 0){
                    cells[2 * i + 2][j * 2 + 1] = true;
                }
            }
        }

        for (int i = 0; i < width; i++) {
            cells[height - 1][i] = false;
        }

        for (int i = 0; i < height; i++) {
            cells[i][width - 1] = false;
        }

        start = random.nextInt(graphHeight - 1);
        finish = random.nextInt(graphHeight - 1);

        cells[1 + 2 * (start)][0] = true;
        cells[1 + 2 * (finish)][width - 1] = true;
    }

    public void generatePath() throws Exception {
        List<Integer> list = PathFinder.findPathAsList(mazeGraph, start * graphWidth, finish * graphWidth + graphWidth - 2);

        int x1;
        int x2;
        int y1;
        int y2;

        for (int i = 0; i < list.size() - 1; i++) {

            x1 = ((list.get(i)) / graphWidth) * 2 + 1;
            x2 = ((list.get(i + 1)) / graphWidth) * 2 + 1;

            y1 = (list.get(i) % graphWidth) * 2 + 1;
            y2 = (list.get(i + 1) % graphWidth) * 2 + 1;

            path[x1][y1] = true;
            path[x2][y2] = true;

            path[(x1 + x2)/2][(y1 + y2) / 2] = true;
        }

        path[1 + 2 * (start)][0] = true;
        path[1 + 2 * (finish)][path[0].length - 1] = true;
        path[1 + 2 * (finish)][path[0].length - 2] = true;

    }

    public boolean[][] getPath() {
        return path;
    }

    public boolean[][] getMaze() {
        return cells;
    }

}