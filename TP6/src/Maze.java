
import com.sun.corba.se.spi.ior.iiop.MaxStreamFormatVersionComponent;
import java.util.*;
import sun.dc.pr.PathStroker;

// Classe a remplir pour realiser le TP en utilisant la classe DisjointSet fournie
public class Maze
{

    public Vector<Wall> maze;
    public Vector<Room> graph;
    public Vector<Integer> path;
    public int LASTROOM;
    public int height;
    public int width;
    public Random generator;
    public DisjointSet ds;

    public Maze(int w, int h, int seed)
    {
        width = w;
        height = h;

        LASTROOM = width * height - 1;

        // Initialisation du labyrinthe avec tous les murs
        maze = new Vector<Wall>();
        for (int i = 0; i < height; ++i)
        {
            for (int j = 0; j < width; ++j)
            {
                if (i > 0)
                {
                    maze.add(new Wall(j + i * height, j + (i - 1) * height));
                }
                if (j > 0)
                {
                    maze.add(new Wall(j + i * height, j - 1 + i * height));
                }
            }
        }

        // Creation du graphe de la topologie du labyrinthe
        graph = new Vector<Room>();
        for (int i = 0; i < height * width; ++i)
        {
            graph.add(new Room(i));
        }

        // On trie les murs de maniere aleatoire
        generator = new Random(seed);
        Wall temp;
        int pos2;
        for (int i = 0; i < maze.size(); ++i)
        {
            pos2 = Math.abs(generator.nextInt() % maze.size());
            temp = maze.get(pos2);
            maze.set(pos2, maze.get(i));
            maze.set(i, temp);
        }

        // Initialisation des structures annexes
        ds = new DisjointSet(width * height);
        path = new Vector<Integer>();
    }

    public void generate()
    {
        int room1,
                room2;

        for (int i = 0; i < maze.size(); ++i)
        {
            room1 = maze.get(i).room1;
            room2 = maze.get(i).room2;

            if (ds.find(room1) != ds.find(room2))
            {
                maze.remove(i--);
                ds.union(ds.find(room1), ds.find(room2));
                graph.get(room1).paths.add(graph.get(room2).id);
                graph.get(room2).paths.add(graph.get(room1).id);
            }
        }
    }

    public void solve()
    {
        LinkedList<Room> closedSet = new LinkedList<>();
        LinkedList<Room> openSet = new LinkedList<>();
        HashMap<Integer, Integer> heur = new HashMap<>();
        HashMap<Integer, Room> previous = new HashMap<>();

        Room currentRoom = graph.get(0);
        openSet.add(currentRoom);
        currentRoom.distance = 0;

        int distanceX,
                distanceY;

        for (int i = 0; i < graph.size(); i++)
        {
            distanceX = width - (graph.get(i).id % width);
            distanceY = height - (graph.get(i).id / height);

            if (distanceX > distanceY)
            {
                heur.put(graph.get(i).id, 14 * distanceY + 10 * (distanceX - distanceY));
            }
            else
            {
                heur.put(graph.get(i).id, 14 * distanceX + 10 * (distanceY - distanceX));
            }
        }

        while (!openSet.isEmpty())
        {
            currentRoom = openSet.peekFirst();

            for (int i = 0; i < openSet.size(); i++)
            {
                if (currentRoom.distance + heur.get(currentRoom.id) > openSet.get(i).distance + heur.get(openSet.get(i).id))
                {
                    currentRoom = openSet.get(i);
                }
            }
            openSet.remove(currentRoom);
            closedSet.add(currentRoom);

            if (currentRoom == graph.get(LASTROOM))
            {
                while (currentRoom != graph.get(0))
                {
                    path.add(currentRoom.id);
                    currentRoom = previous.get(currentRoom.id);
                }
                path.add(currentRoom.id);
                openSet.clear();
            }

            for (int i = 0; i < currentRoom.paths.size(); i++)
            {
                if (!closedSet.contains(graph.get(currentRoom.paths.get(i))))
                {
                    if (!openSet.contains(graph.get(currentRoom.paths.get(i))))
                    {
                        openSet.add(graph.get(currentRoom.paths.get(i)));
                        previous.put(currentRoom.paths.get(i), currentRoom);
                        graph.get(currentRoom.paths.get(i)).distance = currentRoom.distance + 1;
                    }
                    else if (previous.get(currentRoom.paths.get(i)).distance > currentRoom.distance)
                    {
                        previous.put(currentRoom.paths.get(i), currentRoom);
                        graph.get(currentRoom.paths.get(i)).distance = currentRoom.distance + 1;
                    }
                }
            }
        }
    }

    public class Wall
    {

        public int room1;
        public int room2;

        public Wall(int r1, int r2)
        {
            room1 = r1;
            room2 = r2;
        }
    }

    public class Room
    {

        public int id;
        Vector<Integer> paths;
        int distance;

        public Room(int i)
        {
            id = i;
            distance = -1;
            paths = new Vector<Integer>();
        }
    }
}
