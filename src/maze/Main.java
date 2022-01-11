package maze;

import maze.maze.AMAZEingFactory;
import maze.maze.Maze;

public class Main {

    private static boolean isRunning = true;

    private static Maze maze = null;

    public static void main(String[] args) {

        while (isRunning) {
            try {
                execution();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    public static void execution() throws Exception {
        CLI.printMenu(maze != null);

        String input = CLI.getStringInput();

        int[] intInput;

        switch (input) {
            case "0":
                isRunning = false;
                break;
            case "1":
                System.out.println("Enter the size of a new maze");
                intInput = CLI.getIntInput();
                maze = AMAZEingFactory.getMaze(intInput[0], intInput[0]);
                CLI.printMaze(maze.getMaze(), maze.getPath());
                break;
            case "2":
                input = CLI.getStringInput();
                maze = AMAZEingFactory.loadMaze(input);
                break;
            default:
                if (maze != null) {
                    switch (input){
                        case "3":
                            input = CLI.getStringInput();
                            AMAZEingFactory.saveMaze(input);
                            break;
                        case "4":
                            CLI.printMaze(maze.getMaze(), maze.getPath());
                            break;
                        case "5":
                            //call method that will find an escape
                            maze.generatePath();
                            CLI.printMaze(maze.getMaze(), maze.getPath());
                            break;
                        default:
                            System.out.println("Incorrect option. Please try again");
                    }
                } else {
                    System.out.println("Incorrect option. Please try again");
                }
        }

    }
}
