# Assignment A1 - Maze Runner

* **Student**: [Rayan Nasrallah](nasrallr@mcmaster.ca)
* **Authors**: Rayan Nasrallah, Alexandre Lachance
* **Program**: B. Eng. In Software Engineering
* **Course code**: SFWRENG 2AA4
* **Course Title**: Software Design I - Introduction to Software Development
* Term: *Level II - Winter 2024*

## Business Logic Specification

This program explores a maze, finding a path from an entry point to an exit one.

- The maze is stored in a text file, with `#` representing walls and `␣` (_empty space_) representing passages.
- You’ll find examples of such mazes in the [`examples`](./examples) directory.
    - You can also use the [Maze Generator](https://github.com/ace-lectures/maze-gen) to generate others.
- The Maze is surrounded by walls on its four borders, except for its entry/exit points.
    - Entry and exit points are always located on the East and West border.
    - The maze is not directed. As such, exit and entry can be interchanged.
- At the beginning of the exploration, we're located on the entry tile, facing the opposite side (e.g., if entering by
  the eastern entry, you're facing West).
- The program generates a sequence of instructions to reach the opposite exit (i.e., a "path"):
    - `F` means 'move forward' according to your current direction
    - `R` means 'turn right' (does not move, just change direction), and `L` means ‘turn left’.
- A canonical path contains only `F`, `R` and `L` symbols
- A factorized path squashes together similar instructions (i.e., `FFF` = `3F`, `LL` = `2L`).
- Spaces are ignored in the instruction sequence (only for readability: `FFLFF` = `FF L FF`)
- The program takes as input a maze and print the path on the standard output.
    - For this assignment, the path does not have to be the shortest one.
- The program can take a path as input and verify if it's a legit one.

## How to run this software?

To build the program, simply package it with Maven:

```
mosser@azrael A1-Template % mvn -q clean package 
```

### Provided version (starter code)

The starter code assumes the maze file name is the first argument.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar ./examples/small.maz.txt
** Starting Maze Runner
**** Reading the maze from file ./examples/small.maz.txt
WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL 
WALL PASS PASS PASS PASS PASS PASS PASS PASS PASS WALL 
WALL WALL WALL PASS WALL WALL WALL PASS WALL WALL WALL 
WALL PASS PASS PASS PASS PASS WALL PASS PASS PASS WALL 
WALL PASS WALL PASS WALL WALL WALL WALL WALL PASS WALL 
WALL PASS WALL PASS PASS PASS PASS PASS WALL PASS PASS 
WALL WALL WALL PASS WALL PASS WALL WALL WALL WALL WALL 
WALL PASS PASS PASS WALL PASS PASS PASS PASS PASS WALL 
PASS PASS WALL PASS WALL PASS WALL WALL WALL PASS WALL 
WALL PASS WALL PASS WALL PASS WALL PASS PASS PASS WALL 
WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL 
**** Computing path
PATH NOT COMPUTED
** End of MazeRunner
```

When called on a non-existing file. it prints an error message

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar ./examples/small.maz.txtd
** Starting Maze Runner
**** Reading the maze from file ./examples/small.maz.txtd
/!\ An error has occured /!\
**** Computing path
PATH NOT COMPUTED
** End of MazeRunner
```

### Delivered version

#### Command line arguments

The delivered program at the end of this assignment should use the following flags:

- `-i MAZE_FILE`: specifies the filename to be used;
- `-p PATH_SEQUENCE`: activates the path verification mode to validate that PATH_SEQUENCE is correct for the maze
- `-method {tremaux, righthand, BFS}`: specifies which path computation method to use (default is right hand).
- `baseline {tremaux, righthand, BFS}`: specifies the baseline algorithm for speedup comparison.

#### Examples

When no logs are activated, the programs only print the computed path on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt
4F
mosser@azrael A1-Template %
```

If a given path is correct, the program prints the message `correct path` on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 4F
correct path
mosser@azrael A1-Template %
```

If a given path is incorrect, the program prints the message `incorrect path` on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 3F
inccorrect path
mosser@azrael A1-Template %
```

If a given method is provided, the program prints the path computed by the chosen algorithm

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/giant.maz.txt -method BFS 
[INFO ] Main ** Starting Maze Runner
[INFO ] Main Computing path
F L 2F R 2F L 6F R 2F L 6F R 2F R 2F L 2F R 2F L 2F R 8F L 4F R 4F L 6F R 2F L 4F R 2F L 2F R 4F L 4F R 2F L 18F R 4F L 4F R 2F L 2F R 2F L 4F R 4F L 2F R 2F L 2F L 2F R 4F L 2F R 4F L 2F R 10F L 6F R 2F L 2F R 6F L 2F R 2F R 4F L 2F R 2F L 14F R 4F L 4F R 2F L 2F R 8F L 10F R 2F L 4F R 2F L 6F R 2F L 4F R 2F L 6F L 2F R 2F L 4F R 5F
[INFO ] Main End of MazeRunner
mosser@azrael A1-Template % 
```

The program reacts to a new command flag, -baseline XXX , used to specify XXX as the comparison baseline. When run in benchmark mode, the program:
- Prints on stdout the time spent loading the maze from the file
- Prints on stdout the time spent exploring the maze using the provided -method
- Prints on stdout the time spent exploring the maze using the provided -baseline
- Prints on stdout the improvement on the path as a speedup: 𝑆𝑝𝑒𝑒𝑑𝑢𝑝 = |path of baseline|/|path of method|
- Times are measured in milliseconds.
- Speedup and times must be printed with two significative digits.

Example:
Assume right-hand gives you a path containing 113 instructions as a baseline, and Tremeaux a path with 37 instructions as the optimized method. 𝑆𝑝𝑒𝑒𝑑𝑢𝑝 = |baseline|/|method| = 3.05
So overall, using Tremeaux allows one to escape the maze three times faster in this case.


```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/small.maz.txt -method BFS -baseline tremaux
[INFO ] Main ** Starting Maze Runner
Time spent loading the maze from the file: 6.00 milliseconds
[INFO ] Main Computing path
Graph traversal algorithm takes 2.00 milliseconds to solve the maze
F L F R 2F L 6F R 4F R 2F L 2F R 2F L F
[INFO ] Main Computing path
Tremaux algorithm takes 2.00 milliseconds to solve the maze
BFS algorithm allows one to escape the maze 1.00 times faster than the tremaux algorithm
[INFO ] Main End of MazeRunner
mosser@azrael A1-Template %
```

Maze Representations:

The maze is represented in two ways throughout the program:
- True and false representation
- Graph representation

1) Graph Representation
For graph traversing algorithms that find the shortest path (graph traversing algorithms implement the GraphTraverser interface), the maze is represented as a graph where each cell in the maze is a node, and the edges between nodes represent possible paths between the cells. The graph representation allows for the to application of numerous graph traversal algorithms to find a path from the entry point to the exit point of the maze. In the program the BFS algorithm is implemented to find the shortest path from entry to exit. The graph is built by iterating through the maze and creating nodes for each cell. Edges are then added between adjacent nodes based on the possible moves in the maze (i.e., where there is no wall between two cells). This graph building process creates a graph that represents the structure of the maze.

Nodes: Each node in the graph corresponds to a cell in the maze. A node has consists of the following attributes:
- Position: The (x, y) coordinates of the cell in the maze.
- Edges: A list of edges connected to the node. Each edge represents a possible move from the current cell to an adjacent cell.
- Direction: The direction that faced at this node. 

Edges: Each edge in the graph represents a possible move from one cell to another (one node to another). An edge has the following attributes:
- StartNode: The node where the edge starts.
- EndNode: The node where the edge ends.
- Path: The sequence of instructions consisting of 'F', 'FR', or 'FL' required to move from the start node to the end node.

Path Finding
Once the graph is built, graph traversal algorithms can be applied to find a path from the entry node to the exit node. The algorithms explore the graph, starting from the entry node, and follow the edges to navigate through the maze. Any graph traversal algorithm impkements the GraphTraverser algorithm, which requires the path to be returned as Map<Node, Node>, the path is then reconstructed based on the sequence of nodes visited by the algorithm to represent the shortest path as type Path. Overall the graph representation of the maze allows us to use a variety of graph traversal algorithms, such as Breadth-First Search (BFS), to find a path that solves the maze.

2) True and False Representations
In the Maze class, the maze is represented as a two-dimensional list of Boolean values, where each element corresponds to a cell in the maze. If a given value in the two-dimensional list of Boolean values is false, that means the cell is an open space (i.e ' '), if a given value is true, that means that the cell is a wall (i.e '#'). Example:

        #####
        #####
     
        #####
        #####

is represented as:
```
List<List<Boolean>> maze = List.of(
    List.of(true, true, true, true, true),
    List.of(true, true, true, true, true),
    List.of(false, false, false, false, false),
    List.of(true, true, true, true, true),
    List.of(true, true, true, true, true)
);
```
The cells in the maze can be accessed using their (x, y) coordinates, where x is the column index and y is the row index. For example, to check if the cell at position (0, 0) is a wall, you can call maze.isWall(Position), which will return true for a wall, and false for an open space. The representation of the maze as a list of boolean values allows for one to implement any maze solving algorithms (apart from graph traveral algorithms) to solve the maze. Any maze solver that uses this representation implements the MazeSolver interface, which returns type Path to represent the path computed by a given algorithm. In this program, the tremaux and right hand algorithms use the true and false representation. 
