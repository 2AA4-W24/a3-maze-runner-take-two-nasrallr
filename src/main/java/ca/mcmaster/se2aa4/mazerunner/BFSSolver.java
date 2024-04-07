package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

public class BFSSolver implements MazeSolver {

    

    @Override
    public Path solve(Maze maze) {
        GraphBuilder graphBuilder = new GraphBuilder();
        graphBuilder.buildGraph(maze);
        Node startNode = graphBuilder.getStartNode();
        Node endNode = graphBuilder.getEndNode();

        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> shortestPath = new HashMap<>();

        queue.add(startNode);
        shortestPath.put(startNode, null);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.equals(endNode)) {
                return nodeToPath(shortestPath, endNode);
            }

            for (Edge edge : current.getEdges()) {
                Node neighbor = edge.getEndNode();
                if (!shortestPath.containsKey(neighbor)) {
                    shortestPath.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        return new Path();
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

    public void accept(MazeSolverVisitor visitor) {
        visitor.visit(this);
    }
    
}
