package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolverExecutionTimeVisitor implements MazeSolverVisitor{

    private Maze maze;

    public MazeSolverExecutionTimeVisitor(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void visit(RightHandSolver rightHandSolver) {
        long start = System.currentTimeMillis(); 
        rightHandSolver.solve(maze);
        long end = System.currentTimeMillis(); 
        double executionTime = (end - start); 
        System.out.println("Right algorithm takes " + String.format("%.2f", executionTime) + " milliseconds to solve the maze");
    }

    @Override
    public void visit(TremauxSolver tremauxSolver) {
        long start = System.currentTimeMillis(); 
        tremauxSolver.solve(maze);
        long end = System.currentTimeMillis(); 
        double executionTime = (end - start); 
        System.out.println("Tremaux algorithm takes " + String.format("%.2f", executionTime) + " milliseconds to solve the maze");
    }

    @Override
    public void visit(GraphTraverserAdapter algorithm) {
        long start = System.currentTimeMillis(); 
        algorithm.solve(maze);
        long end = System.currentTimeMillis(); 
        double executionTime = (end - start); 
        System.out.println("Graph traversal algorithm takes " + String.format("%.2f", executionTime) + " milliseconds to solve the maze");
    }
}
