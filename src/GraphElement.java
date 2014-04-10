
public class GraphElement implements Comparable<GraphElement>
{
    public Edge m_edge;
    
    public double m_cost;   // AStar's g cost
    public double m_heuristicCost;  // AStar's f cost
    
    public GraphElement()
    {
        m_edge = null;
        m_cost = 0.;
        m_heuristicCost = 0.;
    }
    
    public GraphElement(Edge edge)
    {
        m_edge = edge;
        m_cost = 0.;
        m_heuristicCost = 0.;
    }
    
    public GraphElement(Edge edge, double cost)
    {
        m_edge = edge;
        m_cost = cost;
        m_heuristicCost = 0.;
    }
    
    public GraphElement(Edge edge, double cost, double heuristicCost)
    {
        m_edge = edge;
        m_cost = cost;
        m_heuristicCost = heuristicCost;
    }

    @Override
    public int compareTo(GraphElement other)
    {
        return m_edge.compareTo(other.m_edge);
    }
    
    @Override
    public boolean equals(Object other)
    {
        if(other instanceof GraphElement)
        {
            GraphElement otherGraphElement = (GraphElement)other;
            return m_edge.equals(otherGraphElement.m_edge);
        }

        return false;        
    }
    
    @Override
    public int hashCode()
    {
        return m_edge.m_id.hashCode();
    }

}
