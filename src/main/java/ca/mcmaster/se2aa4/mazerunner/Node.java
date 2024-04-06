package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Node{
    private Position position;
    private ArrayList<Edge> edges;
    private Direction direction;

    public Node(Position inputPosition, Direction inputDirection) {
        this.position = inputPosition;
        this.direction = inputDirection;
        this.edges = new ArrayList<Edge>();
    }

    public void addEdge(Node endVertex, Path path) {
        this.edges.add(new Edge(this, endVertex, path));
    }

    public Position getPosition(){
        return position;
    }

    public Direction getDirection(){
        return direction;
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }



}
