package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolverConcreteVisitor implements MazeSolverVisitor{

    private Maze maze;

    public MazeSolverConcreteVisitor(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void visit(RightHandSolver rightHandSolver) {
        long startTime = System.currentTimeMillis(); 
        rightHandSolver.solve(maze);
        long endTime = System.currentTimeMillis(); 
        double timeInMillis = (endTime - startTime); 
        System.out.println("Right algorithm takes " + String.format("%.2f", timeInMillis) + " milliseconds to solve the maze");
    }

    @Override
    public void visit(TremauxSolver tremauxSolver) {
        long startTime = System.currentTimeMillis(); 
        tremauxSolver.solve(maze);
        long endTime = System.currentTimeMillis(); 
        double timeInMillis = (endTime - startTime); 
        System.out.println("Tremaux algorithm takes " + String.format("%.2f", timeInMillis) + " milliseconds to solve the maze");
    }

    @Override
    public void visit(GraphTraverserAdapter algorithm) {
        long startTime = System.currentTimeMillis(); 
        algorithm.solve(maze);
        long endTime = System.currentTimeMillis(); 
        double timeInMillis = (endTime - startTime); 
        System.out.println("Graph traversal algorithm takes " + String.format("%.2f", timeInMillis) + " milliseconds to solve the maze");
    }
}
