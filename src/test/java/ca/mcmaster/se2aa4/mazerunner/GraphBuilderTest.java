package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphBuilderTest {

    /*private GraphBuilder graphBuilder;
    private Maze maze;

    @BeforeEach
    void initializeMaze() {
        graphBuilder = new GraphBuilder();
        try {
            maze = new Maze("./examples/straight.maz.txt");
        } catch (Exception e) {
            fail("Maze file failed to load " + e.getMessage());
        }
    }

    @Test
    void buildGraph() {
        graphBuilder.buildGraph(maze);
        Node startNode = graphBuilder.getStartNode();
        Node endNode = graphBuilder.getEndNode();

        assertNotNull(startNode, "Start node shouldn't be null");
        assertNotNull(endNode, "End node shouldn't be null");
        assertEquals(maze.getStart(), startNode.getPosition(), "The position stored in start node should be the same as the starting position in the maze");
        assertEquals(maze.getEnd(), endNode.getPosition(), "The position stored in end node should be the same as the end position in the maze");

        ArrayList<Node> adjacencyList = graphBuilder.getAdjacencyList();
        assertEquals(4, adjacencyList.size(), "Adjacency list should contain 4 nodes");

        for (int i = 0; i < 3; i++) {
            Node currentNode = adjacencyList.get(i);
            Node nextNode = adjacencyList.get(i + 1);
            assertEquals(1, currentNode.getEdges().size(), "All nodes excluding the last should contain one edge");
            Edge edge = currentNode.getEdges().get(0);
            assertEquals(nextNode, edge.getEndNode(), "Each edge should connect to the current node to its neighboring node");
            assertEquals("F", edge.getPath().getFactorizedForm(), "All edges for straight path should hold the path F");
        }

        assertEquals(0, endNode.getEdges().size(), "End node shouldn't have any edges");
    }*/
}
