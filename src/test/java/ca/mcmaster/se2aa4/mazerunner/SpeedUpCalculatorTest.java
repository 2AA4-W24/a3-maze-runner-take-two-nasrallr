package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpeedUpCalculatorTest {

    //unchanged

    @Test
    void calculateSpeedUp() {
        SpeedUpCalculator calculator = new SpeedUpCalculator();

        Path baselinePath = new Path();
        baselinePath.addStep('F');
        baselinePath.addStep('F');
        baselinePath.addStep('F');

        Path methodPath = new Path();
        methodPath.addStep('F');
        methodPath.addStep('F');

        String speedUp = calculator.calculateSpeedUp(null, methodPath, baselinePath);

        assertEquals("1.50", speedUp, "Speed up should equal 1.50");
    }

    @Test
    void calculateMoves() {
        SpeedUpCalculator calculator = new SpeedUpCalculator();
        Path path = new Path();
        path.addStep('F');
        path.addStep('F');
        path.addStep('R');
        path.addStep('F');
        path.addStep('L');

        int moves = calculator.calculateMoves(path);

        assertEquals(5, moves, "There should be 5 moves.");
    }
}
