
import java.util.*;

public class Graph implements DijkstraGraph
{

    private int size;
    private ArrayList<LinkedList<Edge>> list;

    public Graph(int size)
    {
        this.size = size;
        list = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
        {
            list.add(null);
        }
    }

    public int size()
    {
        return size;
    }

    public List<Edge> getNeighbors(int node)
    {
        return list.get(node);
    }

    public void addEdge(int s, int d, int w)
    {
        if (s < size && s >= 0 && d < size && d >= 0 && w >= 0)
        {
            List<Edge> neighbors = getNeighbors(s);

            if (neighbors == null)
            {
                LinkedList<Edge> newList = new LinkedList<>();
                newList.add(new Edge(s, d, w));
                list.set(s, newList);
            }
            else
            {
                neighbors.add(new Edge(s, d, w));
            }
        }
    }
}