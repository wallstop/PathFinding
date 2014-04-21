import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Graph
{
    private HashSet<Node> m_graph;
    
    public Graph(HashSet<Node> graph)
    {
        m_graph = graph;
    }
    
    public Graph(Collection<Node> graph)
    {
        m_graph = new HashSet<Node>();
        m_graph.addAll(graph);
    }
    
    public void addNodes(Collection<Node> graph)
    {
        if(m_graph == null)
            m_graph = new HashSet<Node>();
        
        m_graph.addAll(graph);        
    }  
    
    public Node nodeAt(int index)
    {
        Node ret = Node.getInvalidNode();
        if(m_graph == null || index >= m_graph.size())
            return ret;
        
        Iterator<Node> itr = m_graph.iterator();
        for(int i = 0; i < index; ++i)
            ret = itr.next();
        
        return ret;        
    }
    
    public int size()
    {
        return m_graph.size();
    }
    
    public boolean contains(Node node)
    {
        return m_graph.contains(node);
    }
    
    public boolean pathExists(Node start, Node end)
    {
        if(!contains(start) || !contains(end))
            return false;
        
        if(start == end)
            return true;
        
        return pathExistsRecursive(start, end, new HashSet<Node>());     
    }
    
    // Depth-first search
    private boolean pathExistsRecursive(Node start, Node end, HashSet<Node> visitedNodes)
    {
        HashSet<Node> nodes = new HashSet<Node>();
        visitedNodes.add(start);
        
        for(Edge edge : start.m_edges)
        {
            if(visitedNodes.contains(edge.m_to) || edge.m_to == null)
                continue;
            
            if(edge.m_to == end)
                return true;  
            
            nodes.add(edge.m_to);
        }
        
        boolean ret = false;
        for(Node node : nodes)
        {
            ret = ret || pathExistsRecursive(node, end, visitedNodes); 
            visitedNodes.add(node);
        }
        
        return ret;        
    }
}
