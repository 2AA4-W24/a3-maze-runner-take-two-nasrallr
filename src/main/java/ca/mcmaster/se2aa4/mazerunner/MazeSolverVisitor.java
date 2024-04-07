package ca.mcmaster.se2aa4.mazerunner;

public interface MazeSolverVisitor {
    void visit(RightHandSolver rightHandSolver);
    void visit(TremauxSolver tremauxSolver);
    void visit(GraphTraverserAdapter algorithm);
}

