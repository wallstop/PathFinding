
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;


public class Main
{
    
    public static void main(String [] args)
    {
        HashSet<Node> graph = GraphFactory.generateBiDirectionalGraph(50);
        
        Iterator<Node> itr = graph.iterator();
        
        Node start = itr.hasNext() ? itr.next() : new Node();
        Node end = itr.hasNext() ? itr.next() : new Node();
        
        ArrayList<Edge> dijkstraPath = PathFinder.pathfindingByDijkstra(graph, start, end);
        
        ArrayList<Edge> astarPath = PathFinder.pathfindingByAStar(graph, start, end);
        
        System.out.print("Dijkstra path : ");
        double totalDistance = 0.;
        for(Edge edge : dijkstraPath)
        {
            assert(edge.m_weight >= 0.);
            totalDistance += edge.m_weight;
            System.out.print(edge.m_from.m_id.getID() + " ");
        }
        System.out.println("\nTotal Distance: " + totalDistance);
        totalDistance = 0.;
        System.out.print("AStar path : ");
        for(Edge edge : astarPath)
        {
            assert(edge.m_weight >= 0.);
            totalDistance += edge.m_weight;
            System.out.print(edge.m_from.m_id.getID() + " ");            
        }
        System.out.println("\nTotal Distance: " + totalDistance);
    }
}
