
public class BinHeapElem<T> implements Comparable<T>
{

    T value;
    int key;

    public BinHeapElem(T value, int key)
    {
        this.value = value;
        this.key = key;
    }
    
    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    public int getKey()
    {
        return key;
    }

    public void setKey(int key)
    {
        this.key = key;
    }

    @Override
    public int compareTo(T o)
    {
        return key - ((BinHeapElem<T>)o).key;
    }
}
