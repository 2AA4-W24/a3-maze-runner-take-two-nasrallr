package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

public class BFSSolver implements GraphTraverser {
    private Node endN;
    private Node startN;

    @Override
    public Map<Node, Node> traverse(Maze maze) {
        GraphBuilder graphBuilder = new GraphBuilder();
        graphBuilder.buildGraph(maze);
        Node startNode = graphBuilder.getStartNode();
        Node endNode = graphBuilder.getEndNode();
        startN = startNode;
        endN = endNode;

        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> shortestPath = new HashMap<>();

        queue.add(startNode);
        shortestPath.put(startNode, null);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.equals(endNode)) {
                return shortestPath;
            }

            for (Edge edge : current.getEdges()) {
                Node neighbor = edge.getEndNode();
                if (!shortestPath.containsKey(neighbor)) {
                    shortestPath.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        return shortestPath;
    }

    @Override
    public Node getEndNode() {
        return endN;
    }

    @Override
    public Node getStartNode() {
        return startN;
    }
    
}
