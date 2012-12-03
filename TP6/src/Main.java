
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
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;

public class Main
{

    // La ligne de commande prend 3 parametres: la hauteur, la largeur et le numero du labyrinthe a generer. La hauteur devrait preferablement etre egal a la largeur.
    // NE RIEN CHANGER DANS CETTE CLASSE
    public static void main(String[] args)
    {
        final int w = Integer.parseInt(args[0]);
        final int h = Integer.parseInt(args[1]);
        final int s = Integer.parseInt(args[2]);
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI(w, h, s);
            }
        });
    }

    private static void createAndShowGUI(int w, int h, int s)
    {
        System.out.println("Created GUI on EDT? "
                + SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel mp = new MyPanel(new Maze(w, h, s));
        f.add(mp);
        f.pack();
        f.setVisible(true);
        mp.simulate();
    }
}
