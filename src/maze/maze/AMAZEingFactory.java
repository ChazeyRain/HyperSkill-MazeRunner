package maze.maze;

import java.io.*;

public class AMAZEingFactory {

    private static Maze maze = null;

    public static boolean isMazeLoaded() {
        return maze != null;
    }

    public static Maze getMaze(int width, int height) {
        maze = new Maze(width, height);
        return maze;
    }

    public static void saveMaze(String fileName) throws Exception {
        File file = new File(fileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new Exception("Can not create a maze because of some IOException. It happens...");
        }


        try (FileOutputStream fos = new FileOutputStream(file)) {
            try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                try (ObjectOutputStream ois = new ObjectOutputStream(bos)) {
                    ois.writeObject(maze);
                }
            }
        } catch (Exception e) {
            throw new Exception("Can not save file... Again...");
        }
    }

    public static Maze loadMaze(String fileName) throws Exception {
        File file = new File(fileName);

        if (!file.exists()) {
            throw new Exception("The file " + fileName + " does not exist");
        }

        Maze maze;

        try (FileInputStream fis = new FileInputStream(file)) {
            try (BufferedInputStream bis = new BufferedInputStream(fis)) {
                try (ObjectInputStream ois = new ObjectInputStream(bis)) {
                  maze = (Maze) ois.readObject();
                }
            }
        } catch (Exception e) {
            throw new Exception("Cannot load the maze. It has an invalid format");
        }

        AMAZEingFactory.maze = maze;

        return maze;
    }
}
