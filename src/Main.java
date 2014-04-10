
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Main
{
    
    public static void main(String [] args)
    {
        HashSet<Node> graph = GraphFactory.generateBiDirectionalGraph(100);
        
        Iterator<Node> itr = graph.iterator();
        
        Random rGen = new Random();
        
        int nodeToEnd = rGen.nextInt(graph.size()) + 1;
        
        Node start = itr.hasNext() ? itr.next() : new Node();
        Node end = new Node();
        for(int i = 0; i < nodeToEnd && itr.hasNext(); ++i)
            end = itr.next();
        
        
        
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
