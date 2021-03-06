
import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;

public class GraphFactory
{
    private static Random rGen = new Random();
    
    private static final Position DEFAULT_TOP_LEFT = new Position(-10000, 10000);
    private static final Position DEFAULT_BOTTOM_RIGHT = new Position(10000, -10000);
    
    private enum Direction
    {
        UNKNOWN,
        UNI,
        BI        
    }
    
    private static Graph generateGraph(int numNodes, Position topLeft, Position bottomRight, Direction dType)
    {
        assert(numNodes >= 0);
        assert(dType != Direction.UNKNOWN);
        ArrayList<Node> ret = new ArrayList<Node>();
        HashSet<Position> usedPositions = new HashSet<Position>();        
        
        for(int i = 0; i < numNodes; ++i)
        {
            Position currentPosition;
            do
            {
                currentPosition = Position.getRandomPosition(topLeft, bottomRight);
            } 
            while(!usedPositions.add(currentPosition));            
            
            ret.add(new Node(currentPosition));
        }
        for(int i = 0; i < numNodes; ++i)
        {
            int numEdges = (int)(rGen.nextFloat() * Math.log1p(numNodes)) + 2;
            numEdges = numEdges >= numNodes ? numNodes - 1 : numEdges; // Sanity check our random
            HeuristicFunction distanceFunctor = new HeuristicAStar();
            HashSet<Integer> edgesTo = new HashSet<Integer>();
            for(int j = 0; j < numEdges; ++j)
            {
                int randomTo = 0;
                do
                {
                    randomTo = rGen.nextInt(numNodes - 1);  
                }
                while(randomTo == i || edgesTo.contains(randomTo)); 
                edgesTo.add(randomTo);
  
                Node to = ret.get(randomTo);
                Node from = ret.get(i);
                from.m_edges.add(new Edge(from, to, distanceFunctor.determine(from, to)));
                if(dType == Direction.BI)
                    to.m_edges.add(new Edge(to, from, distanceFunctor.determine(from, to)));         
            }
           
        }  
        
        assert(ret.size() >= 0);
        
        return new Graph(ret);

    }
    
    public static Graph generateUniDirectionalGraph(int numNodes)
    {
        return generateGraph(numNodes, DEFAULT_TOP_LEFT, DEFAULT_BOTTOM_RIGHT,  Direction.UNI);
    }
    
    public static Graph generateUniDirectionalGraph(int numNodes, Position topLeft, Position bottomRight)
    {
        return generateGraph(numNodes, topLeft, bottomRight, Direction.UNI);
    }
    
    public static Graph generateBiDirectionalGraph(int numNodes)
    {
        return generateGraph(numNodes, DEFAULT_TOP_LEFT, DEFAULT_BOTTOM_RIGHT, Direction.BI);
    }
    
    public static Graph generateBiDirectionalGraph(int numNodes, Position topLeft, Position bottomRight)
    {
        return generateGraph(numNodes, topLeft, bottomRight, Direction.BI);
    }
    

}
