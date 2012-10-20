
import java.util.Vector;


class HashTable<E> implements NoInitHashTable<E>
{
    /**
     * Taille initiale des tableaux et de la pile
     */
    private final int DEFAULT_SIZE = 5;
    
    /**
     * Pile qui contient les positions des objets dans le tableau
     */
    private Vector<Integer> hashKeys = new Vector<>();
    
    /**
     * Contient les positions des hashkeys dans la pile
     */
    private int[] hashKeysPosition = new int[DEFAULT_SIZE];
    
    /**
     * Contient les objets positionnes selon leur hash
     */
    private E[] objectArray = (E[]) new Object[DEFAULT_SIZE];

    /**
     * Insere un objet dans le tableau et son hash dans la pile
     * @param e Objet a inserer
     */
    @Override
    public void insert(E e)
    {
        if(!contains(e))
        {
            int position = myhash(e);
            
            hashKeys.add(position);
            
            hashKeysPosition[position] = hashKeys.size() - 1;
            
            objectArray[position] = e;
            
            if(hashKeys.size() >= hashKeysPosition.length / 2)
            {
                rehash();
            }
        }
    }

    /**
     * Verifie si l'objet est deja dans le tableau/la pile
     * @param e Objet a comparer
     * @return True s'il est present; false sinon
     */
    @Override
    public boolean contains(E e)
    {
        int hash = myhash(e),
            index = hashKeysPosition[hash];
        
        if(index >= 0 && index < hashKeys.size())
        {
            return hashKeys.get(index) == hash;
        }
        
        return false;
    }

    /**
     * Calcule le hash et gere les collisions
     * @param e Objet a hasher
     * @return Hash apres collision
     */
    public int myhash(E e)
    {
        int size = objectArray.length;
        int hashVal = e.hashCode() % size;
        
        while(hashVal < 0)
        {
            hashVal += size;
        }
        
        // Verifier si la position est libre
        while(hashKeysPosition[hashVal] > 0)
        {
            if(objectArray[hashVal].equals(e))
            {
                break;
            }
            // Gerer les conditions avec un sondage lineaire
            hashVal = (hashVal + 1) % size;
        }
        
        return hashVal;
    }

    /**
     * Augmente la capacite des tableaux/pile a au moins le double de la taille
     */
    @Override
    public void rehash()
    {
        int newSize = nextPrime(objectArray.length*2);
        
        Vector<Integer> oldTable = new Vector<>(hashKeys); // Save the old hashes
        
        E[] oldArray = objectArray; // Save the old array of objects
        
        objectArray = (E[]) new Object[newSize]; // Make a new, bigger array
        hashKeysPosition = new int[newSize]; // New, bigger table
        
        hashKeys.clear();
        
        for(int i:oldTable)
        {
            insert(oldArray[i]);
        }
    }

    /**
     * Calcule le prochain nombre premier a partir d'un certain nombre
     * @param n Nombre de depart
     * @return 
     */
    @Override
    public int nextPrime(int n)
    {
        if(n % 2 != 0)
        {
            n += 1;
        }

        do
        {
            n += 2;
        }while(isPrime(n));

        return n;
    }

    /**
     * Verifie si un nombre est premier
     * @param n Nombre a verifier
     * @return True si c'est un nombre premier; false sinon
     */
    @Override
    public boolean isPrime(int n)
    {
        if(n == 2 || n == 3)
        {
            return true;
        }
        if(n == 1 || n %2 == 0)
        {
            return false;
        }
        for(int i = 3; i <= Math.sqrt(n); i += 2)
        {
            if(n % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public HashIterator<E> iterator()
    {
        return new HashTableIterator<E>();
    }

    private class HashTableIterator<E> implements HashIterator<E>
    {
        int position = 0;

        @Override
        public boolean hasNext()
        {
            return position < hashKeys.size();
        }

        @Override
	public void next()
        {
            if(hasNext())
            {
                position++;
            }
            else
            {
                position  = 0;
            }
        }

        @Override
	public E current()
        {
            return (E) objectArray[hashKeys.get(position)];
        }
    }
    
    public static void main(String [] args)
    {
        HashTable<Integer> table1 = new HashTable<Integer>();
        table1.insert(1);
        table1.insert(1);
        table1.insert(2);
        table1.insert(4);
        table1.insert(6);
        table1.insert(10);
        table1.insert(12);
        table1.insert(16);
        
        HashIterator<Integer> it = table1.iterator();
        while(it.hasNext())
        {
            System.out.println(it.current());
            it.next();
        }
    }
}
