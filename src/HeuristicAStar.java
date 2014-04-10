
public class HeuristicAStar implements HeuristicFunction
{
    // Manhattan distance
    @Override
    public double determine(Node from, Node to)
    {
        assert(from != to);
        assert(from.m_pos.y != to.m_pos.y);
        assert(from.m_pos.x != to.m_pos.x);
        
        return Math.sqrt((((to.m_pos.y - from.m_pos.y) * (to.m_pos.y - from.m_pos.y)) 
                + ((to.m_pos.x - from.m_pos.x) * (to.m_pos.x - from.m_pos.x))));
    }

}
