package ca.mcmaster.se2aa4.mazerunner;

public class Node {
        Position position;
        Direction direction;
        Path path;

        public Node(Position pos, Direction dir, Path path) {
            this.position = pos;
            this.direction = dir;
            this.path = path;
        }
    }
