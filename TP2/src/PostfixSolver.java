import java.io.*;
import java.util.Stack;

public class PostfixSolver
{
    public static void main(String[] args) throws IOException
    {
        Stack<Double> stack = new Stack<Double>();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;

        //Les caracteres sont lus de la ligne de commande tant que l'usager n'entre
        // pas "exit"
        System.out.println("Entrez une expression postfixe");
        
        while (!(s = in.readLine()).equals("exit"))
        {
            // L'expression est separee en tokens selon les espaces
            
            /**
             * !!!ATTENTION!!!
             * 
             * Veuillez vous assurer d'avoir la version 1.7 de Java, car les
             * switch avec des strings ne fonctionnent qu'avec 1.7+
             * Merci!
             */
            for (String token : s.split("\\s"))
            {
                switch (token)
                {
                    // Selon le cas, on fait l'operation demandee a partir des
                    // valeurs dans le stack
                    case "+":
                        stack.push(stack.pop()  + stack.pop());
                        break;
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "-":
                        stack.push(stack.pop() - stack.pop());
                        break;
                    case "/":
                        stack.push(stack.pop() / stack.pop());
                        break;
                    default:
                        Double temp = Double.parseDouble(token); // On convertit en double
                        stack.push(temp); // On sauvegarde la valeur dans le stack
                        break;
                }
            }
            System.out.println("Le resultat de l'expression est: " + stack.pop());
        }
        // "exit" stoppe le programme
    }
}