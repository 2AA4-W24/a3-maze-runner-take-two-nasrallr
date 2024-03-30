package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;
import java.util.Queue;

class BFSSolverTest {

    private BFSSolver solver;
    private Method enqueueMethod;
    private Method updateNodeMethod;
    private Method positionIsValidMethod;

    @BeforeEach
    void setUp() throws Exception {
        solver = new BFSSolver();
        enqueueMethod = BFSSolver.class.getDeclaredMethod("enqueue", Node.class, Character[].class);
        enqueueMethod.setAccessible(true);
        updateNodeMethod = BFSSolver.class.getDeclaredMethod("updateNode", Node.class, Path.class);
        updateNodeMethod.setAccessible(true);
        positionIsValidMethod = BFSSolver.class.getDeclaredMethod("positionIsValid", Position.class);
        positionIsValidMethod.setAccessible(true);
    }

    @Test
    void testSolveSmallMaze() {
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
    void testSolveGiantMaze() {
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
    void testUpdateNode() throws Exception {
        Maze maze = new Maze("./examples/straight.maz.txt");
        solver.solve(maze); 
        Position pos = maze.getStart();

        Node currentNode = new Node(pos, Direction.RIGHT, new Path());
        Path addedPath = new Path("FFFF");
        Node updatedNode = (Node) updateNodeMethod.invoke(solver, currentNode, addedPath);
        assertNotNull(updatedNode);
        assertEquals(new Position(pos.x() + 4, pos.y()), updatedNode.position);
    }

    @Test
    void testEnqueue() throws Exception {
        Maze maze = new Maze("./examples/small.maz.txt");
        solver.solve(maze); 

        Node currentNode = new Node(maze.getStart(), Direction.RIGHT, new Path());
        enqueueMethod.invoke(solver, currentNode, new Character[]{'F'});

        assertEquals(0, solver.getQueue().size());
}


    @Test
    void testPositionIsValid() throws Exception {
        Maze maze = new Maze("./examples/straight.maz.txt");
        solver.solve(maze); 

        Position startPos = maze.getStart();
        Position upPosition = new Position(startPos.x(), startPos.y() + 1);
        Position forwardPosition = new Position(startPos.x() + 1, startPos.y());

        boolean isUpPositionValid = (boolean) positionIsValidMethod.invoke(solver, upPosition);
        boolean isForwardPositionValid = (boolean) positionIsValidMethod.invoke(solver, forwardPosition);

        assertFalse(isUpPositionValid);
        assertTrue(isForwardPositionValid);
    }
}
