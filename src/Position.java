import java.util.Random;

public class Position implements Comparable<Position>
{
    
    public float x;
    public float y;
    private static final Random RGEN = new Random();
    private static final int SPACE_RANGE = 20000;
    private static final int SPACE_OFFSET = -10000;
    
    
    public Position()
    {
        x = 0.f;
        y = 0.f;
    }
    
    public Position(float _x, float _y)
    {
        x = _x;
        y = _y;
    }
    
    public static Position getRandomPosition()
    {
        return new Position(RGEN.nextFloat() * SPACE_RANGE - SPACE_OFFSET, 
                RGEN.nextFloat() * SPACE_RANGE - SPACE_OFFSET);        
    }
    
    @Override
    public int compareTo(Position other)
    {
        if(x == other.x && y == other.y)
            return 0;
        
        // Totally arbitrary
        return (int)(x - other.x + y - other.y);                
    }

}
