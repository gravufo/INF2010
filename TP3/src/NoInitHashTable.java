public interface NoInitHashTable<E>
{
    public void insert(E e);
    public boolean contains(E e);
    
    public void rehash();
    public int nextPrime(int n);
    public boolean isPrime(int n);
    
    public HashIterator<E> iterator();
}

