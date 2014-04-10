
public class HeuristicAStar implements HeuristicFunction
{
    // Manhattan distance
    @Override
    public double determine(Node to, Node from)
    {
        return Math.sqrt(Math.pow(to.m_pos.y - from.m_pos.y, 2) - Math.pow(to.m_pos.x - from.m_pos.x, 2));
    }

}
