
public class Edge
{

    private Integer source;
    private Integer destination;
    private Integer weight;

    public Edge(Integer source, Integer destination, Integer weight)
    {
        super();
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String toString()
    {
        return "(" + source + ", " + destination + ", " + weight + ")";
    }

    public Integer getSource()
    {
        return source;
    }

    public Integer getDestination()
    {
        return destination;
    }

    public Integer getWeight()
    {
        return weight;
    }
}
