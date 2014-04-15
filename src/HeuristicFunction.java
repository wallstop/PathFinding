
// Base class for Dijkstra and AStar (and TBD other) heuristic functions
public interface HeuristicFunction
{  
    abstract double determine(Node from, Node to);
}
