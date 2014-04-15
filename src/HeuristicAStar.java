
public class HeuristicAStar implements HeuristicFunction
{
    
    @Override
    public double determine(Node from, Node to)
    {
        return Position.geometricDistance(from.m_pos, to.m_pos);
    }

}
