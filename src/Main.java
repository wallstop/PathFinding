
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Main
{
    
    public static void main(String [] args)
    {
        HashSet<Node> graph = GraphFactory.generateBiDirectionalGraph(100000);        
        Iterator<Node> itr = graph.iterator();        
        Random rGen = new Random();
        
        int nodeToEnd = 0;
        int nodeToStart = 0;
        do
        {
            nodeToEnd = rGen.nextInt(graph.size());
            nodeToStart = rGen.nextInt(graph.size());
        }
        while(nodeToEnd == nodeToStart);
        int max = nodeToEnd > nodeToStart ? nodeToEnd : nodeToStart;
        
        Node start = Node.getInvalidNode();
        Node end = Node.getInvalidNode();
        for(int i = 0; i <= max && itr.hasNext(); ++i)
        {
            Node tempNode = itr.next();
            if(i == nodeToStart)
                start = tempNode;
            else if(i == nodeToEnd)
                end = tempNode;
        }
        
        ArrayList<Edge> dijkstraPath = PathFinder.pathfindingByDijkstra(graph, start, end);        
        ArrayList<Edge> astarPath = PathFinder.pathfindingByAStar(graph, start, end);
        
        printGraph(dijkstraPath, "Dijkstra");
        printGraph(astarPath, "AStar");
    }
    
    private static void printGraph(ArrayList<Edge> path, String name)
    {
        System.out.print(name + " path :\t");  
        double totalDistance = 0.;
        for(Edge edge : path)
        {
            assert(edge.m_weight >= 0.);
            totalDistance += edge.m_weight;
            System.out.print(edge.m_from.m_id.getID() + "-" +  edge.m_to.m_id.getID() + "\t");
        }        
        System.out.println("\nTotal distance: " + totalDistance);
        
    }
}
