package ca.mcmaster.se2aa4.mazerunner;
import java.util.Map;

public interface GraphTraverser {
    Map<Node, Node> traverse(GraphBuilder graphBuilder);
    public Node getEndNode();
    public Node getStartNode();

}
