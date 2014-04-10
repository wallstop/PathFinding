import java.util.Random;

public class Position implements Comparable<Position>
{
    
    public float x;
    public float y;
    private static final Random RGEN = new Random();
   
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
    
    public static Position getRandomPosition(Position topLeft, Position bottomRight)
    {
        float xRange = bottomRight.x - topLeft.x;
        float yRange = topLeft.y - bottomRight.y;
        return new Position((RGEN.nextFloat() * xRange) + topLeft.x, 
                (RGEN.nextFloat() * yRange) + bottomRight.y);        
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
