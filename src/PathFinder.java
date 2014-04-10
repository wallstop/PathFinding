
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.HashMap;

public class PathFinder
{
    // Fallback
    private static final int MAX_ITERATIONS = 100000;    
    
    public static ArrayList<Edge> pathfindingByDijkstra(HashSet<Node> graph, Node start, Node end)
    {
        return internalDijkstraOrAStar(graph, start, end, new HeuristicDijkstra());
    }
    
    public static ArrayList<Edge> pathfindingByAStar(HashSet<Node> graph, Node start, Node end)
    {
        return internalDijkstraOrAStar(graph, start, end, new HeuristicAStar());
    }
    
    private static ArrayList<Edge> internalDijkstraOrAStar(HashSet<Node> graph, Node start, Node end, 
            HeuristicFunction heuristic)
    {
        ArrayList<Edge> ret = new ArrayList<Edge>();

        if(!graph.contains(start) || !graph.contains(end))
            return ret;
        
        TreeSet<GraphElement> traveled = new TreeSet<GraphElement>();
        TreeSet<GraphElement> untraveled = new TreeSet<GraphElement>();
        HashMap<Node, GraphElement> internalGraph = new HashMap<Node, GraphElement>();
        
        // Initial setup
        GraphElement startElement = new GraphElement(new Edge(new Node(), start), 0, 0);
        internalGraph.put(start, startElement);
        for(Edge edge : start.m_edges)
            untraveled.add(new GraphElement(edge, edge.m_weight, heuristic.determine(edge.m_from, edge.m_to)));
        
        boolean foundPath = false;
        for(int i = 0; i < MAX_ITERATIONS && !untraveled.isEmpty() && !foundPath; ++i)
        {
            GraphElement currentElement = untraveled.first();
            Node toNode = currentElement.m_edge.m_to;
            
            if(!internalGraph.containsKey(toNode) 
                    || internalGraph.get(toNode).m_cost > currentElement.m_cost) 
            {
                internalGraph.put(toNode, currentElement);
            }
            
            traveled.add(currentElement);
            untraveled.remove(currentElement);
 
            if(toNode == end)
            {
                foundPath = true;
                break;
            }
            
            for(Edge edge : toNode.m_edges)
            {
                GraphElement tempGraphElement = new GraphElement(edge, currentElement.m_cost + edge.m_weight, 
                        currentElement.m_cost + heuristic.determine(edge.m_from, end));
                if(traveled.contains(tempGraphElement) || untraveled.contains(tempGraphElement))
                    continue;
                
                untraveled.add(tempGraphElement);                
            }
        }

        if(foundPath)
            return unravelPath(internalGraph, start, end);
        
        ret.clear();
        return ret;
    }
    
    private static ArrayList<Edge> unravelPath(HashMap<Node, GraphElement> graph, Node start, Node end)
    {
        ArrayList<Edge> ret = new ArrayList<Edge>();
        if(!graph.containsKey(start) || !graph.containsKey(end))
            return ret;
        
        Node currentNode = end;
        for(int i = 0; i < graph.size() && currentNode != start; ++i)
        {
            assert(graph.containsKey(currentNode));
            GraphElement tempGraphElement = graph.get(currentNode);
            assert(tempGraphElement.m_edge.m_id.isValid());
            assert(tempGraphElement.m_edge.m_to == currentNode);            
            ret.add(tempGraphElement.m_edge);
            Node oldNode = currentNode;
            currentNode = tempGraphElement.m_edge.m_from;
            graph.remove(oldNode);
        }

        Collections.reverse(ret);
        return ret;
    }

}
