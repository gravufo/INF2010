import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

public class Dijkstra {

	public static void fiboHeapDijkstra(DijkstraGraph graph, int source, int[] distance) {

		FibonacciHeap<Integer> fHeap = new FibonacciHeap<Integer>();

		for (int i=0; i < graph.size(); i++){
			distance[i] = Integer.MAX_VALUE;
		}		
		distance[source] = 0;

		//Complétez
	}

	public static void binHeapDijkstra(DijkstraGraph graph, int source, int[] distance) {

		//Complétez
	}

	public static void printPath(int source, int destination){
		//Complétez
	}

	public static void main(String[] args) {

		//Routine de generation aleatoire de graphe, pour vous aider à tester vos implantations.
		/*	  
	Random random = new Random();
	int size = 10000;
	DijkstraGraph graph = new Graph(size);

	for (int i = 0; i < size; i++){
	    for (int j = 0; j < size; j++){
		if (i != j){
		    double rdm = random.nextDouble();
		    if (rdm > 0.99){
			graph.addEdge(i,j,random.nextInt(100));
		    }
		}
	    }
	}
		 */

		int[] dist = new int[graph.size()];

		//long start = System.currentTimeMillis();
		fiboHeapDijkstra(graph,0,dist);
		//long end = System.currentTimeMillis();

		//System.out.println("Time for FIBO: "+ (end-start) + "ms");

		//System.out.println();

		dist = new int[graph.size()];

		//start = System.currentTimeMillis();
		binHeapDijkstra(graph,0,dist);
		//end = System.currentTimeMillis();

		//System.out.println("Time for BIN: "+ (end-start) + "ms");	
	}

}
