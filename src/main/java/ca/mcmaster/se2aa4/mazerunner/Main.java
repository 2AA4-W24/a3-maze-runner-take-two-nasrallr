package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(getParserOptions(), args);
            String filePath = cmd.getOptionValue('i');
            Maze maze = new Maze(filePath);
            System.out.println("Time spent loading the maze from the file: " + maze.executionTime() + " milliseconds" );
            if (cmd.getOptionValue("p") != null) {
                logger.info("Validating path");
                Path path = new Path(cmd.getOptionValue("p"));
                if (maze.validatePath(path)) {
                    System.out.println("correct path");
                } else {
                    System.out.println("incorrect path");
                }
            } else {
                SpeedUpCalculator suc = new SpeedUpCalculator();
                String method = cmd.getOptionValue("method", "BFS");
                Path path = solveMaze(method, maze);
                System.out.println(path.getFactorizedForm());
                String baseline = cmd.getOptionValue("baseline", "tremaux");
                Path baselinePath = solveMaze(baseline, maze);
                //System.out.println(baselinePath.getFactorizedForm());
                String speedUp = suc.calculateSpeedUp(maze, path, baselinePath);
                System.out.println(method + " algorithm allows one to escape the maze " + speedUp + " times faster than the " + baseline + " algorithm");
            }
        } catch (Exception e) {
            System.err.println("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }

        logger.info("End of MazeRunner");
    }

    /**
     * Solve provided maze with specified method.
     *
     * @param method Method to solve maze with
     * @param maze Maze to solve
     * @return Maze solution path
     * @throws Exception If provided method does not exist
     */
    private static Path solveMaze(String method, Maze maze) throws Exception {
        MazeSolver solver = null;
        switch (method) {
            case "righthand" -> {
                logger.debug("RightHand algorithm chosen.");
                solver = new RightHandSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solver = new TremauxSolver();
            }
            case "BFS" -> {
                logger.debug("BFS algorithm chosen.");
                solver = new BFSSolver();
            }            
            default -> {
                throw new Exception("Maze solving method '" + method + "' not supported.");
            }
        }
    
        logger.info("Computing path");
        long startTime = System.currentTimeMillis(); 
        Path solution = solver.solve(maze);
        long endTime = System.currentTimeMillis(); 
        double timeInMillis = (endTime - startTime); 
        System.out.println(method + " algorithm takes " + String.format("%.2f", timeInMillis) + " milliseconds to solve the maze");
        return solution;
    }
    

    /**
     * Get options for CLI parser.
     *
     * @return CLI parser options
     */
    private static Options getParserOptions() {
        Options options = new Options();

        Option fileOption = new Option("i", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        options.addOption(new Option("p", true, "Path to be verified in maze"));
        options.addOption(new Option("method", true, "Specify which path computation algorithm will be used"));
        options.addOption(new Option("baseline", true, "Specify which path computation algorithm will be compared to chosen computation algorithm to find maze path"));

        return options;
    }
}
