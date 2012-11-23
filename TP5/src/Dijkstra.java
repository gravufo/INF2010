
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

public class Dijkstra
{

    public static void fiboHeapDijkstra(DijkstraGraph graph, int source, int[] distance)
    {

        FibonacciHeap<Integer> fHeap = new FibonacciHeap<>();

        for (int i = 0; i < graph.size(); i++)
        {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;

        fHeap.clear();

        for (int i = 0; i < graph.size(); i++)
        {
            fHeap.insert(i, distance[i]);
        }

        int distanceMin;
        Edge currentEdge;

        while (!fHeap.isEmpty())
        {
            distanceMin = fHeap.extractMin();

            for (int i = 0; i < graph.getNeighbors(distanceMin).size(); i++)
            {
                currentEdge = graph.getNeighbors(distanceMin).get(i);

                if (distance[currentEdge.getDestination()] > distance[distanceMin] + currentEdge.getWeight() && distance[currentEdge.getDestination()] != Integer.MAX_VALUE)
                {
                    distance[currentEdge.getDestination()] = distance[distanceMin] + currentEdge.getWeight();
                    fHeap.decreaseKey(currentEdge.getDestination(), distance[currentEdge.getDestination()]);
                }
            }
        }
    }

    public static void binHeapDijkstra(DijkstraGraph graph, int source, int[] distance)
    {
        PriorityQueue<BinHeapElem<Integer>> bHeap = new PriorityQueue<>();

        for (int i = 0; i < graph.size(); i++)
        {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;

        bHeap.clear();

        for (int i = 0; i < graph.size(); i++)
        {
            bHeap.offer(new BinHeapElem<>(i, distance[i]));
        }

        int distanceMin;
        Edge currentEdge;
        //START FROM HERE

        while (!bHeap.isEmpty())
        {
            distanceMin = bHeap.poll().value;

            for (int i = 0; i < graph.getNeighbors(distanceMin).size(); i++)
            {
                currentEdge = graph.getNeighbors(distanceMin).get(i);

                if (distance[currentEdge.getDestination()] > distance[distanceMin] + currentEdge.getWeight() && distance[currentEdge.getDestination()] != Integer.MAX_VALUE)
                {
                    distance[currentEdge.getDestination()] = distance[distanceMin] + currentEdge.getWeight();
                    
                    for(BinHeapElem<Integer> pos:bHeap)
                    {
                        if(pos.value == currentEdge.getDestination())
                        {
                            bHeap.remove(pos);
                            break;
                        }
                    }
                    bHeap.offer(new BinHeapElem<>(currentEdge.getDestination(), distance[currentEdge.getDestination()]));
                    //bHeap.decreaseKey(currentEdge.getDestination(), distance[currentEdge.getDestination()]);
                }
            }
        }
    }

    public static void printPath(int source, int destination)
    {
        //Complétez
    }

    public static void main(String[] args)
    {
        //Routine de generation aleatoire de graphe, pour vous aider à tester vos implantations.
        Random random = new Random();
        int size = 10000;
        DijkstraGraph graph = new Graph(size);

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (i != j)
                {
                    double rdm = random.nextDouble();
                    if (rdm > 0.99)
                    {
                        graph.addEdge(i, j, random.nextInt(100));
                    }
                }
            }
        }


        int[] dist = new int[graph.size()];

        long start = System.currentTimeMillis();
        fiboHeapDijkstra(graph, 0, dist);
        long end = System.currentTimeMillis();

        System.out.println("Time for FIBO: " + (end - start) + "ms");

        System.out.println();

        dist = new int[graph.size()];

        start = System.currentTimeMillis();
        binHeapDijkstra(graph, 0, dist);
        end = System.currentTimeMillis();

        System.out.println("Time for BIN: " + (end - start) + "ms");
    }
}
