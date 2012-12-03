
import java.util.*;

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
            
            if(ds.find(room1) != ds.find(room2))
            {
                maze.remove(i--);
                ds.union(ds.find(room1), ds.find(room2));
            }
            // Peut-être qu'il faut mettre ces deux lignes dans le if ci-dessus
            graph.get(room1).paths.add(graph.get(room2).id);
            graph.get(room2).paths.add(graph.get(room1).id);
        }
    }

    public void solve()
    {
        // A completer
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
