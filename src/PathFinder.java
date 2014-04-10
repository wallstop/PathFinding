
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
        
        TreeSet<GraphElement> traveledSorted = new TreeSet<GraphElement>();
        HashSet<Edge> traveledSet = new HashSet<Edge>();
        TreeSet<GraphElement> untraveledSortedSet = new TreeSet<GraphElement>();
        HashSet<Edge> untraveledSet = new HashSet<Edge>();
        HashMap<Node, GraphElement> internalGraph = new HashMap<Node, GraphElement>();
        
        // Initial setup
        GraphElement startElement = new GraphElement(new Edge(new Node(), start), 0, 0);
        internalGraph.put(start, startElement);
        for(Edge edge : start.m_edges)
        {
            if(untraveledSet.add(edge))
                untraveledSortedSet.add(new GraphElement(edge, edge.m_weight + heuristic.determine(edge.m_from, end), edge.m_weight));
        }
        
        boolean foundPath = false;
        for(int i = 0; i < MAX_ITERATIONS && !untraveledSortedSet.isEmpty() && !foundPath; ++i)
        {
            GraphElement currentElement = untraveledSortedSet.first();
            Edge definingEdge = currentElement.m_edge;
            Node toNode = definingEdge.m_to;
            
            if(!internalGraph.containsKey(toNode) 
                    || internalGraph.get(toNode).m_cost > currentElement.m_cost) 
            {
                internalGraph.put(toNode, currentElement);
            }

            if(traveledSet.add(definingEdge))
                traveledSorted.add(currentElement);
            
            untraveledSet.remove(definingEdge);
            untraveledSortedSet.remove(currentElement);
 
            if(toNode == end)
            {
                foundPath = true;
                break;
            }
            
            for(Edge edge : toNode.m_edges)
            {
                double cost = currentElement.m_cost + edge.m_weight;
                GraphElement tempGraphElement = new GraphElement(edge, cost + heuristic.determine(edge.m_from, end),
                        cost);
                if(traveledSet.contains(edge) || untraveledSet.contains(edge))
                    continue;
                
                untraveledSet.add(edge);        
                untraveledSortedSet.add(tempGraphElement);
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
            //graph.remove(oldNode);
        }

        Collections.reverse(ret);
        return ret;
    }

}
