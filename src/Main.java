
import java.util.ArrayList;
import java.util.Random;

public class Main
{
    
    public static void main(String [] args)
    {
        Graph graph = GraphFactory.generateBiDirectionalGraph(100000);             
        Random rGen = new Random();
        
        int startPos = 0;
        int endPos = 0;
        do
        {
            startPos = rGen.nextInt(graph.size());
            endPos = rGen.nextInt(graph.size());
        }
        while(startPos == endPos);

        Node start = graph.nodeAt(startPos);
        Node end = graph.nodeAt(endPos);
        
        if(!graph.pathExists(start,  end))
            return;
        
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
