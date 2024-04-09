package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolverPerformanceVisitor implements MazeSolverVisitor{

    private Maze maze;

    public MazeSolverPerformanceVisitor(Maze maze) {
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

        long start1 = System.currentTimeMillis();
        GraphBuilder graphBuilder = new GraphBuilder();
        graphBuilder.buildGraph(maze);
        long end1 = System.currentTimeMillis();
        double timeToBuildGraph = (end1 - start1); 

        executionTime -= timeToBuildGraph; //subtracting time it takes to build graph representation
        // This is so that execution time is only the time it takes to traverse/solve the graph
        if ( executionTime <= 0 ) {
            executionTime = 0.00;
        }

        System.out.println("Graph traversal algorithm takes " + String.format("%.2f", executionTime) + " milliseconds to solve the maze");
    }
}
