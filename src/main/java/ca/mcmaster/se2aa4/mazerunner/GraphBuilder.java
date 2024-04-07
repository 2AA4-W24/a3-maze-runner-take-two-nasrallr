package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Set;

public class GraphBuilder {

    private Queue<Node> queue = new LinkedList<>();
    private Set<Position> visitedNodes = new HashSet<>();
    private Maze maze;
    private ArrayList<Node> adjacencyList = new ArrayList<>();
    private Node startNode;
    private Node endNode;
    

    public void buildGraph(Maze inputMaze) {
        this.maze = inputMaze;
        Direction dir = Direction.RIGHT;
        Position pos = maze.getStart();
        startNode = new Node(pos, dir);
        queue.add(startNode);
        visitedNodes.add(maze.getStart());

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            enqueue(currentNode, 'F');
            enqueue(currentNode, 'R', 'F');
            enqueue(currentNode, 'L', 'F');
        }
    }

    private void enqueue(Node currentNode, Character... moves) {
        Path path = new Path();
        for (Character move : moves) {
            path.addStep(move);
        }
        Position position = currentNode.getPosition();
        Direction dir = currentNode.getDirection();
    
        for (char move : path.getPathSteps()) {
            switch (move) {
                case 'F':
                    position = position.move(dir);
                    break;
                case 'R':
                    dir = dir.turnRight();
                    break;
                case 'L':
                    dir = dir.turnLeft();
                    break;
            }
        }
    
        Node newNode = new Node(position, dir);
    
        if (positionIsValid(position) && visitedNodes.add(position)) {
            queue.add(newNode);
            this.adjacencyList.add(newNode);
            currentNode.addEdge(newNode, path);
        }
    
        if (newNode.getPosition().equals(maze.getEnd())) {
            endNode = newNode;
        }
    }
    

    private boolean positionIsValid(Position position) {
        return position != null && position.x() >= 0 && position.x() < maze.getSizeX() && position.y() >= 0 && position.y() < maze.getSizeY() && !maze.isWall(position);
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public ArrayList<Node> getAdjacencyList() {
        return adjacencyList;
    }

}
