
public class RedBlackTreeMain
{

    public static void main(String[] args)
    {
        // Valeurs a inserer dans l'arbre
        int val[] =
        {
            201, 106, 401, 302, 119, 128, 210, 315, 137, 148, 225, 235
        };

        // Creation de l'arbre
        RedBlackTree<Integer> tree = new RedBlackTree<Integer>();

        // Insertion des elements dans l'arbre
        for (int i = 0; i < val.length; i++)
        {
            tree.insert(new Integer(val[i]));
        }

        // Afficher arbre
        System.out.println("Apres quelques insertions...");
        tree.printFancyTree();
        System.out.println();

        // Verifier find()
        Integer n = tree.find(401);
        System.out.print("Recherche valeur 401 : ");
        if (n != null)
        {
            System.out.println("Noeud trouvé.");
        }
        else
        {
            System.out.println("Noeud introuvable.");
        }

        n = tree.find(99);
        System.out.print("Recherche valeur 99 : ");
        if (n != null)
        {
            System.out.println("Noeud trouvé.");
        }
        else
        {
            System.out.println("Noeud introuvable.");
        }

        System.out.println();

        // Affichage pre-ordre et post-ordre
        tree.printTreePreOrder();
        tree.printTreePostOrder();

        System.out.println();

        // Supressions
        System.out.println("Tests de supression");

        tree.remove(106);
        tree.printFancyTree();
        System.out.println();

        for (int i = val.length - 1; i > 0; i--)
        {
            System.out.println("Remove " + val[i]);
            tree.remove(val[i]);
            tree.printFancyTree();
            System.out.println();
        }

        // Recherche dans un arbre rouge-noir 201, 99
        n = tree.find(201);
        System.out.print("Recherche valeur 201 : ");
        if (n != null)
        {
            System.out.println("Noeud trouvé.");
        }
        else
        {
            System.out.println("Noeud introuvable.");
        }

        n = tree.find(99);
        System.out.print("Recherche valeur 99 : ");
        if (n != null)
        {
            System.out.println("Noeud trouvé.");
        }
        else
        {
            System.out.println("Noeud introuvable.");
        }
    }
}
