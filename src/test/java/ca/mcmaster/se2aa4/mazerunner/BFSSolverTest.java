package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;


class BFSSolverTest {

    private GraphTraverserAdapter adapter;
    private BFSSolver solver;

    @BeforeEach
    void setUp() throws Exception {
        solver = new BFSSolver();
        adapter = new GraphTraverserAdapter(solver);
    }

    @Test
    void solveSmallMaze() {
        try {
            Maze maze = new Maze("./examples/small.maz.txt");
            BFSSolver solver = new BFSSolver();
            Map<Node, Node> shortestPath = new HashMap<>();
            GraphBuilder graphBuilder = new GraphBuilder();
            graphBuilder.buildGraph(maze);
            shortestPath = solver.traverse(graphBuilder);
            Path solution = adapter.nodeToPath(shortestPath, solver.getEndNode());
            assertNotNull(solution);
            assertFalse(solution.getPathSteps().isEmpty());
            assertTrue(maze.validatePath(solution));
            assertEquals("F L F R 2F L 6F R 4F R 2F L 2F R 2F L F", solution.getFactorizedForm());
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }


}
