package ca.mcmaster.se2aa4.mazerunner;

public class SpeedUpCalculator {
    
    public String calculateSpeedUp(Path method, Path baseline) {
        
        int methodPathLength = calculateMoves(method);
        int baselinePathLength = calculateMoves(baseline);

        double speedUp = (double) baselinePathLength / methodPathLength;

        return String.format("%.2f", speedUp);
    }

    public int calculateMoves(Path path) {
        return path.getPathSteps().size();
    }
}
