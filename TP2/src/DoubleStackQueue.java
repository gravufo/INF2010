import java.util.Stack;

public class DoubleStackQueue<Elem> implements myQueue<Elem>
{
    private Stack<Elem> first = new Stack(),
            last = new Stack();
            
    
    public void offer(Elem element) // Ajoute un element dans la file
    {
        // Pretty straightforward : if the size of the first stack is 0, push the new element on it.
        // Side note : this shouldn't happen unless the last.size() == 0 too.
        if(first.empty())
        {
            first.push(element);
        }
        else
        {
            last.push(element);
        }   
    }
    
    public Elem peek() // Retourne l'element en tete de la file, sans le retirer de la file
    {
        return first.peek();
    }

    public Elem poll() // Retourne l'element en tete de la file et le retire de la file
    {
        if(first.empty())
        {
            return null;
        }
        
        Elem temp = first.pop();
        
        if(first.empty())
        {
            while(!last.empty())
            {
                first.push(last.pop());
            }
        }
        return temp;
    }
    
    // Fonction principale pour tester notre algorithme
    public static void main(String[] args)
    {
        DoubleStackQueue<Integer> queue = new DoubleStackQueue<>();
        
        for(Integer i = 0; i < 100; i++)
        {
            queue.offer(i);
        }
        
        // Le 101 est pour verifier si la 100ieme valeur sera null (car le stack est vide)
        for(Integer i = 0; i < 101; i++)
        {
            System.out.println(queue.poll() + " ");
        }
    }
}
