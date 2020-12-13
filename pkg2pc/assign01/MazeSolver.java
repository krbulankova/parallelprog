package pkg2pc.assign01;

import java.util.ArrayList;

/**
 * Assignment 01 - Implement parallel maze solving algorithm using JAVA Concurrent API
 * <p>
 * use maze.isNorth(x,y), maze.isSouth(x,y), etc to check if there is wall in exact direction
 * use maze.setVisited(x,y) and maze.isVisited(x,y) to check is you already have been at particular cell in the maze
 *
 * @author Janis
 */
public class MazeSolver {
    private final static Object lock = new Object();

    Maze maze;

    //Current position
    int x;
    int y;


    public MazeSolver(Maze m) {
        this.maze = m;
    }

    /**
     * Solve maze starting from initial positions
     *
     * @param x starting position in x direction
     * @param y starting position in y direction
     */
    public void solveMaze(int x, int y) {
        if (this.maze.isVisited(30, 30)) return;

        synchronized (lock) {
            this.x = x;
            this.y = y;
        }

        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);

        this.maze.setVisited(x, y);
        StdDraw.show(30);

        // Checks all available directions from current cell
        ArrayList<Integer> available = new ArrayList<>();
        if (!this.maze.isSouth(x, y) && !this.maze.isVisited(x, y - 1)) available.add(0);
        if (!this.maze.isNorth(x, y) && !this.maze.isVisited(x, y + 1)) available.add(1);
        if (!this.maze.isWest(x, y) && !this.maze.isVisited(x - 1, y)) available.add(2);
        if (!this.maze.isEast(x, y) && !this.maze.isVisited(x + 1, y)) available.add(3);

        this.maze.setVisited(x, y);

        //   Iteratively call recursive function in separate thread for every available direction, but
        // call recursive function in this thread if list element is the last one
        for (int i = 0; i < available.size(); i++) {
            Thread thread;

            switch (available.get(i)) {
                case 0:
                    if (i == available.size() - 1) solveMaze(x, y - 1);
                    else {
                        thread = new Thread(() -> solveMaze(x, y - 1));
                        thread.start();
                    }
                    break;

                case 1:
                    if (i == available.size() - 1) solveMaze(x, y + 1);
                    else {
                        thread = new Thread(() -> solveMaze(x, y + 1));
                        thread.start();
                    }
                    break;

                case 2:
                    if (i == available.size() - 1) solveMaze(x - 1, y);
                    else {
                        thread = new Thread(() -> solveMaze(x - 1, y));
                        thread.start();
                    }
                    break;

                case 3:
                    if (i == available.size() - 1) solveMaze(x + 1, y);
                    else {
                        thread = new Thread(() -> solveMaze(x + 1, y));
                        thread.start();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

