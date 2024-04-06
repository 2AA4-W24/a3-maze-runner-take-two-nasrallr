package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

public class BFSSolver implements MazeSolver {

    private GraphBuilder graphBuilder = new GraphBuilder();

    @Override
    public Path solve(Maze maze) {
        
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
                return nodeToPath(shortestPath);
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

    private Path nodeToPath(Map<Node, Node> shortestPathInput) {
        ArrayList<Node> shortestPath = new ArrayList<>();
        Node endNode = graphBuilder.getEndNode();
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
    
}
