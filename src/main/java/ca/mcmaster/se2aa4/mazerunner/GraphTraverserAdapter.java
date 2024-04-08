package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Map;

public class GraphTraverserAdapter implements MazeSolver{
    private GraphTraverser graphTraverser;

    public GraphTraverserAdapter(GraphTraverser algorithm) {
        this.graphTraverser = algorithm;
    }

    @Override   
    public Path solve(Maze maze) {
        
        Map<Node, Node> shortestPath;

        shortestPath = graphTraverser.traverse(maze);

        return nodeToPath(shortestPath, graphTraverser.getEndNode());
    }

    public Path nodeToPath(Map<Node, Node> shortestPathInput, Node endN) {
        ArrayList<Node> shortestPath = new ArrayList<>();
        Node endNode = endN;
        while (endNode != null) {
            shortestPath.add(0, endNode);
            endNode = shortestPathInput.get(endNode);
        }

        Path path = new Path();
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            Node currentNode = shortestPath.get(i);
            Node nextNode = shortestPath.get(i + 1);

            for (Edge edge : currentNode.getEdges()) {
                if (edge.getEndNode().equals(nextNode)) {
                    for (char c : edge.getPath().getPathSteps()) {
                        path.addStep(c);
                    }
                    break;
                }
            }
        }

        return path;
    }

    @Override
    public void accept(MazeSolverVisitor visitor) {
        visitor.visit(this);
    }
}
