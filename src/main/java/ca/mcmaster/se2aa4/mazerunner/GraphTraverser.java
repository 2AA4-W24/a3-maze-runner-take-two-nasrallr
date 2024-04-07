package ca.mcmaster.se2aa4.mazerunner;
import java.util.Map;

public interface GraphTraverser {
    Map<Node, Node> traverse(Maze maze);
    public Node getEndNode();
    public Node getStartNode();

}
