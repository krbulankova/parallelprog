package pkg2pc.assign01;

import java.util.concurrent.RecursiveTask;

/**
 * Assignment 01 - Implement parallel maze solving algorithm using JAVA Concurrent API
 * <p>
 * use maze.isNorth(x,y), maze.isSouth(x,y), etc to check if there is wall in exact direction
 * use maze.setVisited(x,y) and maze.isVisited(x,y) to check is you already have been at particular cell in the maze
 *
 * @author Janis
 */
public class MazeSolver extends RecursiveTask<Integer> {

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

        this.x = x;
        this.y = y;

        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);

        this.maze.setVisited(x, y);
        StdDraw.show(30);

        // TODO Your algorithm here. If necessary, create onother methods (for example, recursive solve method)

        if (!this.maze.isSouth(x, y) && !this.maze.isVisited(x, y - 1) && !(this.maze.isVisited(30, 30))) {
            this.maze.setVisited(x, y);
            solveMaze(x, y - 1);

        }
        if (!this.maze.isNorth(x, y) && !this.maze.isVisited(x, y + 1) && !(this.maze.isVisited(30, 30))) {
            this.maze.setVisited(x, y + 1);
            solveMaze(x, y + 1);

        }
        if (!this.maze.isWest(x, y) && !this.maze.isVisited(x - 1, y) && !(this.maze.isVisited(30, 30))) {
            this.maze.setVisited(x - 1, y);
            solveMaze(x - 1, y);

        }
        if (!this.maze.isEast(x, y) && !this.maze.isVisited(x + 1, y) && !(this.maze.isVisited(30, 30))) {
            this.maze.setVisited(x, y);
            solveMaze(x + 1, y);
        }

    }




    @Override
    protected Integer compute() {

        return null;
    }
}

