
public class BinHeapElem<T> implements Comparable<T>
{

    T x;

    @Override
    public int compareTo(T o)
    {
        return o.hashCode() - x.hashCode();
    }
}
