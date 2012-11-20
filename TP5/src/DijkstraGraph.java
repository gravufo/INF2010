import java.util.List;

public interface DijkstraGraph {

	public int size(); //The number of vertex in the graph

	public List<Edge> getNeighbors(int node); //Returns a list of all the outgoing edges from node

	//Add an edge of weight w between source and destination; create the nodes if necessary
	public void addEdge(int source, int destination, int w);

}