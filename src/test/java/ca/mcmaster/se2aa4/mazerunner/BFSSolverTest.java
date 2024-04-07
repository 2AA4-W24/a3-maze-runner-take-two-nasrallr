package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;


class BFSSolverTest {

    /*private BFSSolver solver;

    @BeforeEach
    void setUp() throws Exception {
        solver = new BFSSolver();
    }

    @Test
    void solveSmallMaze() {
        try {
            Maze maze = new Maze("./examples/small.maz.txt");
            BFSSolver solver = new BFSSolver();
            Path solution = solver.solve(maze);

            assertNotNull(solution);
            assertFalse(solution.getPathSteps().isEmpty());
            assertTrue(maze.validatePath(solution));
            assertEquals("F L F R 2F L 6F R 4F R 2F L 2F R 2F L F", solution.getFactorizedForm());
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void solveGiantMaze() {
        try {
            Maze maze = new Maze("./examples/giant.maz.txt");
            BFSSolver solver = new BFSSolver();
            Path solution = solver.solve(maze);

            assertNotNull(solution);
            assertFalse(solution.getPathSteps().isEmpty());
            assertTrue(maze.validatePath(solution));
            assertEquals("F L 2F R 2F L 6F R 2F L 6F R 2F R 2F L 2F R 2F L 2F R 8F L 4F R 4F L 6F R 2F L 4F R 2F L 2F R 4F L 4F R 2F L 18F R 4F L 4F R 2F L 2F R 2F L 4F R 4F L 2F R 2F L 2F L 2F R 4F L 2F R 4F L 2F R 10F L 6F R 2F L 2F R 6F L 2F R 2F R 4F L 2F R 2F L 14F R 4F L 4F R 2F L 2F R 8F L 10F R 2F L 4F R 2F L 6F R 2F L 4F R 2F L 6F L 2F R 2F L 4F R 5F", solution.getFactorizedForm());
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void nodeToPath() {
        Node startNode = new Node(new Position(1, 2), Direction.RIGHT);
        Node node1 = new Node(new Position(2, 2), Direction.RIGHT);
        Node node2 = new Node(new Position(3, 2), Direction.RIGHT);
        Node endNode = new Node(new Position(4, 2), Direction.RIGHT);

        startNode.addEdge(node1, new Path("F"));
        node1.addEdge(node2, new Path("F"));
        node2.addEdge(endNode, new Path("F"));

    
        Map<Node, Node> shortestPath = new HashMap<>();
        shortestPath.put(startNode, null); 
        shortestPath.put(node1, startNode);
        shortestPath.put(node2, node1);
        shortestPath.put(endNode, node2);

        
        Path solution = solver.nodeToPath(shortestPath, endNode);

        Path expectedPath = new Path();
        expectedPath.addStep('F'); 
        expectedPath.addStep('F'); 
        expectedPath.addStep('F'); 
        assertEquals(expectedPath.getPathSteps(), solution.getPathSteps(), " Constructed path should match the path constructed by BFSSolver");
    }*/


}
