
// Not thread safe
public class UniqueID implements Comparable<UniqueID>
{
    private int m_id;
    
    private static int idGen = 1;
    private static final int INVALID_ID = Integer.MIN_VALUE;
    
    public UniqueID()
    {
        m_id = generateId();
    }
    
    private UniqueID(int id)
    {
        m_id = id;
    }
    
    public int getID()
    {
        return m_id;
    }
    
    @Override
    public int compareTo(UniqueID other)
    {
        return m_id - other.m_id;
    }
    
    @Override
    public boolean equals(Object other)
    {
        if(other instanceof UniqueID)
        {
            UniqueID otherID = (UniqueID)other;
            return compareTo(otherID) == 0;
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        if(isValid())
        {
            return (41 * (41 + m_id));  // Only good for small ids, didn't feel like implementing a proper hasher
            //return m_id;
        }
        return INVALID_ID;
    }    
    
    static UniqueID getInvalid()
    {
        return new UniqueID(INVALID_ID);
    }
    
    public boolean isValid()
    {
        return m_id != INVALID_ID;
    }
    
    private int generateId()
    {
        return idGen++;
    }

}
