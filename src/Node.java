
import java.util.ArrayList;

public class Node implements Comparable<Node>
{
    
	public UniqueID m_id;
	public Position m_pos;
	public ArrayList<Edge> m_edges;
	
	public Node()
	{
	    m_id = UniqueID.getInvalid();
	    m_pos= new Position();
	    m_edges = new ArrayList<Edge>();
	}
	
	public Node(UniqueID id)
	{
	    m_id = id;
	    m_pos = Position.getRandomPosition();
		m_edges = new ArrayList<Edge>();
	}
	
	
	public Node(ArrayList<Edge> edges)
	{
        m_id = new UniqueID();
	    m_pos = Position.getRandomPosition();
	    m_edges = new ArrayList<Edge>(edges);
	}
	
	public Node(Position pos)
	{
        m_id = new UniqueID();
	    m_pos = pos;
	    m_edges = new ArrayList<Edge>();
	}
	
	public Node(Position pos, ArrayList<Edge> edges)
	{
        m_id = new UniqueID();
	    m_pos = pos;
	    m_edges = edges;	    
	}

	public boolean isValid()
	{
	    return m_id.isValid();
	}
	
	@Override
	public int compareTo(Node other)
	{	    
	    return m_id.compareTo(other.m_id);	    
	}
	
	@Override
	public boolean equals(Object other)
    {
	    if(other instanceof Node)
	    {
	        Node otherNode = (Node)other;
	        return m_id.equals(otherNode.m_id);
	    }
	    
	    return false;
    }
	
	@Override
	public int hashCode()
	{
	    return m_id.hashCode();
	}
	
}
