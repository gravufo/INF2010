
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.*;
import java.awt.Graphics2D;

// NE PAS EDITER CETTE CLASSE !!
// SI VOUS LE FAITES, C'EST A VOS RISQUES ET PERIL !!
class MyPanel extends JPanel
{

    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;
    private final int pHeight = 500;
    private final int pWidth = 500;

    public MyPanel(Maze m)
    {
        maze = m;
        maze.generate();
        repaint();
        setFocusable(true);

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                maze.solve();
                repaint();
            }
        });
    }

    public void simulate()
    {
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(pWidth, pHeight);
    }

    // Affiche le labyrinthe
    // NE PAS TOUCHER A CETTE METHODE SOUS AUCUNE CONSIDERATION !!
    // Si le labyrinthe est correctement genere, il s'affichera a l'ecran sans aucune modification a cette fonction!!
    // Apres avoir clique dans la fenetre, le chemin le plus court pour resoudre le labyrinthe s'affichera sous la forme d'un trait rouge
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int hres = pWidth / maze.height;
        int wres = pHeight / maze.width;

        g.setColor(Color.BLACK);

        for (Maze.Wall w : maze.maze)
        {
            if (w.room2 < w.room1)
            {
                int diff = w.room1 - w.room2;
                if (diff == 1)
                {
                    int x1 = w.room2 % maze.width + 1;
                    int y1 = w.room2 / maze.width;
                    ((Graphics2D) g).draw(new Line2D.Double(wres * x1, hres * y1, wres * x1, hres * y1 + hres));
                }
                else
                {
                    int x1 = w.room1 % maze.width;
                    int y1 = w.room1 / maze.width;
                    ((Graphics2D) g).draw(new Line2D.Double(wres * x1, hres * y1, wres * x1 + wres, hres * y1));
                }
            }
            else
            {
                System.out.println("!!!!");
            }
        }

        maze.maze.trimToSize();
        System.out.println(maze.maze.size());

        g.setColor(Color.RED);

        int x1 = -1;
        int y1 = -1;
        for (Integer i : maze.path)
        {
            int x2 = i % maze.width * hres + hres / 2;
            int y2 = i / maze.width * wres + wres / 2;

            if (x1 != -1)
            {
                ((Graphics2D) g).draw(new Line2D.Double(x1, y1, x2, y2));
            }

            x1 = x2;
            y1 = y2;
        }

    }
    Maze maze;
}
