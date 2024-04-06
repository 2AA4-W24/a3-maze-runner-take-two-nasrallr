package ca.mcmaster.se2aa4.mazerunner;

public class Edge{
    private Node startNode;
    private Node endNode;
    private Path path;

    public Edge(Node startNode, Node endNode, Path path) {
        this.startNode = endNode;
        this.endNode = endNode;
        this.path = path;
    }

    public Node getEndNode(){
        return endNode;
    }

    public Node getStartNode(){
        return startNode;
    }

    public Path getPath(){
        return path;
    }

}