package ca.mcmaster.se2aa4.mazerunner;

import java.util.LinkedList;
import java.util.Queue;

public class BFSSolver implements MazeSolver {

    private Queue<Path> queue = new LinkedList<>();
    private Maze maze;

    @Override
    public Path solve(Maze inputMaze) {
        this.maze = inputMaze;
        queue.add(new Path()); 

        while (!queue.isEmpty()) {
            Path currentPath = queue.poll();
            Position currentPosition = updatePosition(maze.getStart(), currentPath);
            if (currentPosition.equals(maze.getEnd())) {
                return currentPath;
            }

            enqueue(currentPath, 'F');
            enqueue(currentPath, 'R', 'F');
            enqueue(currentPath, 'L', 'F');
        }

        return new Path(); 
    }

    private void enqueue(Path currentPath, Character... moves) {
        Path potentialPath = new Path(currentPath.getCanonicalForm()); 
        for (Character move : moves) {
            potentialPath.addStep(move);
        }
        Position nextPosition = updatePosition(maze.getStart(), potentialPath);
        if (positionIsValid(nextPosition)) {
            queue.add(potentialPath);
        }
    }

    private Position updatePosition(Position start, Path path) {
        Position pos = new Position(start.x(), start.y());
        Direction dir = Direction.RIGHT;

        for (char move : path.getPathSteps()) {
            switch (move) {
                case 'F':
                    pos = pos.move(dir);
                    if (!positionIsValid(pos)) {
                        return null;
                    }
                    break;
                case 'R':
                    dir = dir.turnRight();
                    break;
                case 'L':
                    dir = dir.turnLeft();
                    break;
            }
        }
        return pos;
    }

    private boolean positionIsValid(Position pos) {
        return pos != null && pos.x() >= 0 && pos.x() < maze.getSizeX() && pos.y() >= 0 && pos.y() < maze.getSizeY() && !maze.isWall(pos);
    }
}
