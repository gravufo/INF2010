public class RedBlackTree<T extends Comparable<? super T> > 
{
   private RBNode<T> root;  // Racine de l'arbre
   //private RBNode<T> nil; // BONUS
   
   enum ChildType{ left, right }
   
   public RedBlackTree()
   { 
      root = null;
      // nil = new RBNode<T>(); // BONUS
   }
   
   public void printFancyTree()
   {
      printFancyTree( root, "", ChildType.right);
   }
   
   private void printFancyTree( RBNode<T> t, String prefix, ChildType myChildType)
   {
      System.out.print( prefix + "|__"); // un | et trois _
      
      if( t != null )
      {
         boolean isLeaf = (t.isNil()) || ( t.leftChild.isNil() && t.rightChild.isNil() );
         
         System.out.println( t );
         String _prefix = prefix;
         
         if( myChildType == ChildType.left )
            _prefix += "|   "; // un | et trois espaces
         else
            _prefix += "   " ; // trois espaces
         
         if( !isLeaf )
         {
            printFancyTree( t.leftChild, _prefix, ChildType.left );
            printFancyTree( t.rightChild, _prefix, ChildType.right );
         }
      }
      else
         System.out.print("null\n");
   }
   
   public T find(int key)
   {
      return find(root, key);
   }
   
   private T find(RBNode<T> current, int key)
   {
      // Completer
      return null;
   }
   
   public void insert(T val)
   {
      insertNode( new RBNode<T>( val ) );
   }
   
   private void insertNode( RBNode<T> newNode )
   { 
      if (root == null)  // Si arbre vide
         root = newNode;
      else
      {
         RBNode<T> position = root; // On se place a la racine
         
         while( true ) // on insere le noeud (ABR standard)
         {
            int newKey = newNode.value.hashCode();
            int posKey = position.value.hashCode();
            
            if ( newKey < posKey ) 
            {
               if ( position.leftChild.isNil() ) 
               {
                  position.leftChild = newNode;
                  newNode.parent = position;
                  break;
               } 
                  else 
                  position = position.leftChild;
            } 
            else if ( newKey > posKey ) 
            {
               if ( position.rightChild.isNil() )
               {
                  position.rightChild = newNode;
                  newNode.parent = position;
                  break;
               }
               else 
                  position = position.rightChild;
            }
            else // pas de doublons
               return;
         }
      }
      
      insertionCases( newNode );
   }

   private void insertionCases( RBNode<T> X )
   {
      insertionCase1( X );
   }
   
   /**
     * Celle-ci est codee popur vous afin d'illustrer
     * le concept des insersionCase qui s'appellent l'une l'autre
     */
   private void insertionCase1 ( RBNode<T> X )
   {
      /*// Bon code, commente pour compilation
     if( X == root )
         X.setToBlack();
      else
         insertionCase2( X );
      */
      
      // effacer ce qui suit
      return;
   }

   private void insertionCase2( RBNode<T> X )
   {
      // Completer
      return;
   }

   private void insertionCase3( RBNode<T> X )
   {
      // Completer
      return;
   }

   private void insertionCase4( RBNode<T> X )
   {
      // Completer
      return;
   }

   private void insertionCase5( RBNode<T> X )
   {
      // Completer
      return;
   }
   
   private void rotateLeft( RBNode<T> G )
   {
      // Completer
      return;
   }
   
   private void rotateRight( RBNode<T> G )
   {
      // Completer
      return;
   }
   
   public T remove(int key)
   {
      RBNode<T> removed = remove(root, key);
      
      if( removed != null )
            return removed.value;
      else
         return null;
   }
   
   private RBNode<T> remove(RBNode<T> current, int key)
   {
      if( current.isNil() )
         return null;
      
      if( current.value.hashCode() > key )
         return remove( current.leftChild, key );
      else if( current.value.hashCode() < key )
         return remove( current.rightChild, key );
      else //if( current.value.hashCode() == key )
      {
         if( !current.leftChild.isNil() && !current.rightChild.isNil() )
         {
            current.value = findMin( current.rightChild );
            remove( current.rightChild, current.value.hashCode() );
         }
         else
        	 deleteOneChild( current );
         
         return current;
         
      }
   }
   
   private T findMin( RBNode<T> ref )
   { 
      if( ref.isNil() ) 
         return null; 
      else if( ref.leftChild.isNil() ) 
         return ref.value;
      
      return findMin( ref.leftChild );
   }
   
   private void deleteOneChild( RBNode<T> X )
   {
      RBNode<T> P = X.parent;
      RBNode<T> C;
      boolean isLeftChild = (X!=root) && ( P.leftChild == X );

      // Trouver C
      if( X.leftChild.isNil() )
         C = X.rightChild;
      else
         C = X.leftChild;

      // On remplace X par C
      if( X != root )
         if( isLeftChild )
            P.leftChild = C;
         else
            P.rightChild = C;
      else
         root = C;
      
      C.parent = P;
      
      if( X.isBlack() )
         if( C.isRed() )
            C.setToBlack();
         else
            removeCase1( C );
   }
   
   private void removeCase1( RBNode<T> X )
   {
      if( X != root )
         removeCase2( X );
   }
   
   private void removeCase2( RBNode<T> X )
   {
      RBNode<T> S = X.sibling();
      RBNode<T> P = X.parent;
      
      if( S.isRed() )
      {
         P.setToRed();
         S.setToBlack();
         
         if( X == P.leftChild )
            rotateLeft( P );
         else
;            rotateRight( P );
      }
      
      removeCase3( X );
   }
   
   private void removeCase3( RBNode<T> X )
   {
      RBNode<T> S  = X.sibling();
      RBNode<T> Sr = S.rightChild;
      RBNode<T> Sl = S.leftChild;
      RBNode<T> P  = X.parent;
      
      if( P.isBlack() &&
          S.isBlack() &&
          Sl.isBlack() &&
          Sr.isBlack() )
      {
         S.setToRed();
         removeCase1( P );
      }
      else
         removeCase4( X );
   }
   
   private void removeCase4( RBNode<T> X )
   {
      RBNode<T> S  = X.sibling();
      RBNode<T> Sr = S.rightChild;
      RBNode<T> Sl = S.leftChild;
      RBNode<T> P  = X.parent;
      
      if( P.isRed() &&
          S.isBlack() &&
          Sl.isBlack() &&
          Sr.isBlack() )
      {
         S.setToRed();
         P.setToBlack();
      }
      else
         removeCase5( X );
   }
   
   private void removeCase5( RBNode<T> X )
   {
      RBNode<T> S  = X.sibling();
      RBNode<T> Sr = S.rightChild;
      RBNode<T> Sl = S.leftChild;
      RBNode<T> P  = X.parent;
      boolean isLeftChild = (P.leftChild == X);
      
      if( S.isBlack() )
      {
         if( isLeftChild &&
             Sr.isBlack() &&
             Sl.isRed() )
         {
            S.setToRed();
            Sl.setToBlack();
            rotateRight( S );
         }
         else if( !isLeftChild &&
             Sr.isRed() &&
             Sl.isBlack() )
         {
            S.setToRed();
            Sr.setToBlack();
            rotateLeft( S );
         }
      }
      
      removeCase6( X );
   }
   
   private void removeCase6( RBNode<T> X )
   {
      RBNode<T> S  = X.sibling();
      RBNode<T> Sr = S.rightChild;
      RBNode<T> Sl = S.leftChild;
      RBNode<T> P  = X.parent;
      boolean isLeftChild = (X!=root) && ( P.leftChild == X );
      
      S.copyColor( P );
      P.setToBlack();
      
      if( isLeftChild )
      {
         Sr.setToBlack();
         rotateLeft( P );
      }
      else
      {
         Sl.setToBlack();
         rotateRight( P );
      }
   }

   public void printTreePreOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "PreOrdre ( ");
         printTreePreOrder( root );
         System.out.println( " )");
      }
      return;
   }
   
   private void printTreePreOrder( RBNode<T> P )
   {
      // Completer
      return;
   }
   
   public void printTreePostOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "PostOrdre ( ");
         printTreePostOrder( root );
         System.out.println( ")");
      }
      return;
   }
  
   private void printTreePostOrder( RBNode<T> P )
   {
      // Completer
      return;
   }
   
   private static class RBNode<T extends Comparable<? super T> > 
   {
      enum RB_COLOR { BLACK, RED }  // Couleur possible
      
      RBNode<T>  parent;      // Noeud parent
      RBNode<T>  leftChild;   // Feuille gauche
      RBNode<T>  rightChild;  // Feuille droite
      RB_COLOR   color;       // Couleur du noeud
      T          value;       // Valeur du noeud
      
      // Constructeur (NIL)   
      RBNode() { setToBlack(); }
      
      // Constructeur (feuille)   
      RBNode(T val)
      {
         setToRed();
         value = val;
         leftChild = new RBNode<T>();
         leftChild.parent = this;
         rightChild = new RBNode<T>();
         rightChild.parent = this;
      }
      
      RBNode<T> grandParent()
      {
         return parent.parent;
      }
      
      RBNode<T> uncle()
      {
          if(parent.parent.leftChild.value.equals(value))
          {
              return parent.parent.leftChild;
          }
          else
          {
              return parent.parent.rightChild;
          }
      }
      
      RBNode<T> sibling()
      {
         if(parent.leftChild.value.equals(value))
          {
              return parent.leftChild;
          }
          else
          {
              return parent.rightChild;
          }
      }
      
      public String toString()
      {
         return value + " (" + (color == RB_COLOR.BLACK ? "black" : "red") + ")"; 
      }
      
      boolean isBlack(){ return (color == RB_COLOR.BLACK); }
      boolean isRed(){ return (color == RB_COLOR.RED); }
      boolean isNil(){ return (leftChild == null) && (rightChild == null); }
      
      void setToBlack(){ color = RB_COLOR.BLACK; }
      void setToRed(){ color = RB_COLOR.RED; }
      void copyColor(RBNode<T> ref)
      {
         if( ref != null )
            color = ref.color;
      }
   }
}
