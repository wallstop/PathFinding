
import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;

public class GraphFactory
{
    static Random rGen = new Random();
    static final Position DEFAULT_TOP_LEFT = new Position(0, 100);
    static final Position DEFAULT_BOTTOM_RIGHT = new Position(100, 0);
    
    private enum Direction
    {
        UNKNOWN,
        UNI,
        BI        
    }
    
    private static HashSet<Node> generateGraph(int numNodes, Position topLeft, Position bottomRight, Direction dType)
    {
        assert(numNodes >= 0);
        assert(dType != Direction.UNKNOWN);
        ArrayList<Node> ret = new ArrayList<Node>();
        HashSet<Position> usedPositions = new HashSet<Position>();
        
        float xRange = bottomRight.x - topLeft.x;
        float yRange = topLeft.y - bottomRight.y;
        
        
        for(int i = 0; i < numNodes; ++i)
        {
            Position currentPosition = new Position();
            do
            {
                currentPosition.x = rGen.nextFloat() * xRange;
                currentPosition.y = rGen.nextFloat() * yRange;
            } 
            while(!usedPositions.add(currentPosition));            
            
            ret.add(new Node(currentPosition));
        }
        for(int i = 0; i < numNodes; ++i)
        {
            int numEdges = rGen.nextInt((int)Math.log1p(numNodes)) + 2;
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
  
                ret.get(i).m_edges.add(new Edge(ret.get(i), ret.get(randomTo), distanceFunctor.determine(ret.get(i), ret.get(randomTo))));
                if(dType == Direction.BI)
                    ret.get(i).m_edges.add(new Edge(ret.get(randomTo), ret.get(i), distanceFunctor.determine(ret.get(randomTo), ret.get(i))));         
            }
        }  
        
        assert(ret.size() >= 0);
        
        HashSet<Node> retSet = new HashSet<Node>(ret);
        assert(retSet.size() == numNodes);
        
        return retSet;

    }
    
    public static HashSet<Node> generateUniDirectionalGraph(int numNodes)
    {
        return generateGraph(numNodes, DEFAULT_TOP_LEFT, DEFAULT_BOTTOM_RIGHT,  Direction.UNI);
    }
    
    public static HashSet<Node> generateUniDirectionalGraph(int numNodes, Position topLeft, Position bottomRight)
    {
        return generateGraph(numNodes, topLeft, bottomRight, Direction.UNI);
    }
    
    public static HashSet<Node> generateBiDirectionalGraph(int numNodes)
    {
        return generateGraph(numNodes, DEFAULT_TOP_LEFT, DEFAULT_BOTTOM_RIGHT, Direction.BI);
    }
    
    public static HashSet<Node> generateBiDirectionalGraph(int numNodes, Position topLeft, Position bottomRight)
    {
        return generateGraph(numNodes, topLeft, bottomRight, Direction.BI);
    }
    

}
