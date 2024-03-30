package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFSSolver implements MazeSolver {

    private Queue<Node> queue = new LinkedList<>();
    private Set<Position> visited = new HashSet<>();
    private Maze maze;

    @Override
    public Path solve(Maze inputMaze) {
        this.maze = inputMaze;
        Direction dir = Direction.RIGHT;
        Position pos = maze.getStart();
        queue.add(new Node(pos, dir, new Path()));
        visited.add(maze.getStart());
    
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
            if (positionIsValid(nextPosition) && visited.add(nextPosition)) {
                queue.add(updatedNode);
            }
        }
    }


    private Node updateNode(Node currentNode, Path addedPath) {
        Position position = new Position(currentNode.position.x(), currentNode.position.y());
        Direction dir = currentNode.direction;
        Path nodePath = new Path(currentNode.path.getCanonicalForm()); // Create a copy of the current node's path
        Path path = addedPath;
        
    
        for (char move : path.getPathSteps()) {
            nodePath.addStep(move); // Update the copy of the path with the move
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
    
        return new Node(position, dir, nodePath); // Use the copy of the path in the new node
    }
    

    private boolean positionIsValid(Position position) {
        return position != null && position.x() >= 0 && position.x() < maze.getSizeX()
                && position.y() >= 0 && position.y() < maze.getSizeY() && !maze.isWall(position);
    }

    @Override
    public long executionTime(Maze maze) {
        long start = System.currentTimeMillis();
        solve(maze);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public Queue<Node> getQueue() {
        return queue;
    }

    private static class Node {
        Position position;
        Direction direction;
        Path path;

        Node(Position pos, Direction dir, Path path) {
            this.position = pos;
            this.direction = dir;
            this.path = path;
        }
    }
}