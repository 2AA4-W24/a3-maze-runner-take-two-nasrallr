package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFSSolver implements MazeSolver {

    private Queue<Node> queue = new LinkedList<>();
    private Set<Position> visitedNodes = new HashSet<>();
    private Maze maze;

    @Override
    public Path solve(Maze inputMaze) {
        this.maze = inputMaze;
        Direction dir = Direction.RIGHT;
        Position pos = maze.getStart();
        queue.add(new Node(pos, dir, new Path()));
        visitedNodes.add(maze.getStart());
    
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            Path currentPath = currentNode.path;
            Position currentPosition = currentNode.position;
            if (currentPosition.equals(maze.getEnd())) {
                return currentPath;
            } else {
                enqueue(currentNode, 'F');
                enqueue(currentNode, 'R', 'F');
                enqueue(currentNode, 'L', 'F');
            }
    
            
        }
        return new Path(); 
    }
    

    private void enqueue(Node currentNode, Character... moves) {
        Path potentialPath = new Path();
        for (Character move : moves) {
            potentialPath.addStep(move);
        }
        Node updatedNode = updateNode(currentNode, potentialPath);
        if (updatedNode != null) {
            Position nextPosition = updatedNode.position;
            if (positionIsValid(nextPosition) && visitedNodes.add(nextPosition)) {
                queue.add(updatedNode);
            }
        }
    }


    private Node updateNode(Node currentNode, Path addedPath) {
        Position position = new Position(currentNode.position.x(), currentNode.position.y());
        Direction dir = currentNode.direction;
        Path nodePath = new Path(currentNode.path.getCanonicalForm()); 
        Path path = addedPath;
        
    
        for (char move : path.getPathSteps()) {
            nodePath.addStep(move); 
            switch (move) {
                case 'F':
                    position = position.move(dir);
                    if (!positionIsValid(position)) {
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
    
        return new Node(position, dir, nodePath); 
    }
    

    private boolean positionIsValid(Position position) {
        return position != null && position.x() >= 0 && position.x() < maze.getSizeX()
                && position.y() >= 0 && position.y() < maze.getSizeY() && !maze.isWall(position);
    }

    @Override
    public String executionTime(Maze maze) {
        long start = System.currentTimeMillis();
        solve(maze);
        long end = System.currentTimeMillis();
        double timeInMillis = end - start; // Time in milliseconds
        return String.format("%.2f", timeInMillis);
    }

    public Queue<Node> getQueue() {
        return queue;
    }

    
}
