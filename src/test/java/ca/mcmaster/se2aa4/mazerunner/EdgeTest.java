package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    @Test
    void initializeEdge() {
        Node startNode = new Node(new Position(0, 0), Direction.RIGHT);
        Node endNode = new Node(new Position(0, 1), Direction.RIGHT);
        Path path = new Path();
        path.addStep('F');

        Edge edge = new Edge(startNode, endNode, path);

        assertEquals(startNode, edge.getStartNode(), "The start node stored in the edge should be the same as the one initialized.");
        assertEquals(endNode, edge.getEndNode(), "The end node stored in the edge should be the same as the one initialized.");
        assertEquals(path, edge.getPath(), "The path stored in the edge should be the same as the one initialized");
    }
}
