package maze;

import java.util.Scanner;

public class CLI {

    private static final Scanner scanner = new Scanner(System.in);

    public static void printMaze(boolean[][] maze, boolean[][] path) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j] ? path[i][j] ? "//" : "  " : !path[i][j] ? "\u2588\u2588" : "//");
            }
            System.out.println();
        }
    }

    public static void printMenu(boolean expanded) {
        System.out.println(!expanded ? "=== Menu ===\n" +
                "1. Generate a new maze\n" +
                "2. Load a maze\n" +
                "0. Exit" :
                "=== Menu ===\n" +
                        "1. Generate a new maze\n" +
                        "2. Load a maze\n" +
                        "3. Save the maze\n" +
                        "4. Display the maze\n" +
                        "5. Find the escape\n" +
                        "0. Exit");
    }

    public static int[] getIntInput() throws Exception {
        String input = scanner.nextLine();

        if (!input.matches("[0-9][0-9 ]*")) {
            throw new Exception("Incorrect option. Please try again");
        }

        String[] inputs = input.trim().split(" ");
        int[] output = new int[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            output[i] = Integer.parseInt(inputs[i]);
        }

        return output;
    }

    public static String getStringInput() {
        String input = scanner.nextLine();
        return input.trim();
    }

}
