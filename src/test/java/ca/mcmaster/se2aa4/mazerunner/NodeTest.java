package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class NodeTest {

    @Test
    void addEdge() {
        Node startNode = new Node(new Position(0, 0), Direction.RIGHT);
        Node endNode = new Node(new Position(0, 1), Direction.RIGHT);
        Path path = new Path();
        path.addStep('R');
        path.addStep('F');

        startNode.addEdge(endNode, path);

        assertEquals(1, startNode.getEdges().size(), "After adding one edge to the node the node should have once edge");
        Edge edge = startNode.getEdges().get(0);
        assertEquals(startNode, edge.getStartNode(), "The start node that was added to the edge, and the start node stored in the edge should be the same");
        assertEquals(endNode, edge.getEndNode(), "The end node that was added to the edge, and the end node stored in the edge should be the same");
        assertEquals(path, edge.getPath(), "The path that was added to the edge, and the path stored in the edge should be the same");
    }

    @Test
    void testGetters() {
        Position position = new Position(1, 2);
        Direction direction = Direction.RIGHT;
        Node node = new Node(position, direction);

        assertEquals(position, node.getPosition(), "The position initialized and the one provided to the constructor should be the same");
        assertEquals(direction, node.getDirection(), "The direction initialized and the one provided to the constructor should be the same");
        assertTrue(node.getEdges().isEmpty(), "If no edges are added, the edges list should be empty");
    }

}
