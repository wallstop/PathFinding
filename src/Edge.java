
public class Edge implements Comparable<Edge>
{
    
    public Node m_to;
    public Node m_from;
    public UniqueID m_id;
    
    public double m_weight;
    
    public Edge()
    {
        m_id = new UniqueID();
        m_to = null;
        m_from = null;
        m_weight = 0.;
    }
    
    public Edge(Node from, Node to)
    {
        m_id = new UniqueID();
        m_to = to;
        m_from = from;
        m_weight = 0.;
    }
    
    public Edge(Node from, Node to, double weight)
    {   
        m_id = new UniqueID();
        m_to = to;
        m_from = from;
        m_weight = weight;
    }
    
    @Override
    public int compareTo(Edge other)
    {
        if(m_id.compareTo(other.m_id) == 0)
            return 0;

        return m_weight - other.m_weight > 0. ? 1 : -1;     
    }
   
    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Edge)
        {
            Edge otherEdge = (Edge)other;
            return m_id.equals(otherEdge.m_id);
        }
        
        return false;
    }
    
    @Override
    public int hashCode()
    {
        return m_id.hashCode();
    }

}
