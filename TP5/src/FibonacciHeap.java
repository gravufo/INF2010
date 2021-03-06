
import java.util.*;

public class FibonacciHeap<T>
{

    private FibonacciHeapNode<T> minNode;
    private int size;
    private static final double logPhi = Math.log((1.0 + Math.sqrt(5.0)) / 2.0);
    private HashMap<T, FibonacciHeapNode<T>> nodeMap = new HashMap<T, FibonacciHeapNode<T>>();

    public FibonacciHeap()
    {
        size = 0;
        minNode = null;
    }

    public boolean isEmpty()
    {
        return minNode == null;
    }

    public void clear()
    {
        size = 0;
        minNode = null;
    }

    public int size()
    {
        return size;
    }

    private void insertInNodeList(FibonacciHeapNode<T> head, FibonacciHeapNode<T> node)
    {

        node.left = head;
        node.right = head.right;
        head.right = node;
        node.right.left = node;
    }

    public void insert(T value, int key)
    {
        FibonacciHeapNode<T> newNode = new FibonacciHeapNode<>(value, key);

        if (isEmpty())
        {
            minNode = newNode;
            minNode.right = minNode;
            minNode.left = minNode;
        }
        else
        {
            insertInNodeList(minNode, newNode);

            if (key < minNode.key)
            {
                minNode = newNode;
            }
        }

        nodeMap.put(value, newNode);
        size++;
    }

    public T extractMin()
    {

        FibonacciHeapNode<T> z = minNode;

        if (z != null)
        {
            //Iterate over z's children
            if (z.hasChild())
            {

                int numChildren = z.degree;
                FibonacciHeapNode<T> x = z.child;
                FibonacciHeapNode<T> next;

                while (numChildren > 0)
                {
                    next = x.right;

                    //Remove x from z's children list
                    x.right.left = x.left;
                    x.left.right = x.right;

                    //Add x to the root list 
                    insertInNodeList(minNode, x);

                    x.parent = null;
                    x = next;
                    numChildren--;
                }
            }

            //Remove z from the root list
            z.left.right = z.right;
            z.right.left = z.left;

            if (z == z.right)
            {
                minNode = null;
            }
            else
            {
                minNode = z.right;
                consolidate();
            }
            size -= 1;
        }
        nodeMap.remove(z);
        return z.value;
    }

    private void consolidate()
    {

        int arrSize = (int) Math.floor(Math.log(size) / logPhi) + 1;
        FibonacciHeapNode[] arr = new FibonacciHeapNode[arrSize];

        FibonacciHeapNode<T> y = null;
        FibonacciHeapNode<T> x = null;

        FibonacciHeapNode<T> root = minNode;
        Vector<FibonacciHeapNode> roots = new Vector<FibonacciHeapNode>();
        do
        {
            roots.add(root);
            root = root.right;
        }
        while (root != minNode);

        for (FibonacciHeapNode w : roots)
        {
            x = w;
            int d = x.degree;

            while (arr[d] != null)
            {
                y = arr[d];
                if (x.key > y.key)
                {
                    FibonacciHeapNode<T> temp = x;
                    x = y;
                    y = temp;
                }
                link(y, x);
                arr[d] = null;
                d += 1;
            }
            arr[d] = x;
            w = w.right;
        }

        minNode = null;

        for (int i = 0; i < arrSize; i++)
        {
            if (arr[i] != null)
            {
                if (minNode == null)
                {
                    minNode = arr[i];
                    minNode.right = minNode.left = minNode;
                }
                else
                {
                    insertInNodeList(minNode, arr[i]);
                }
                if (arr[i].key < minNode.key)
                {
                    minNode = arr[i];
                }
            }
        }
    }

    private void link(FibonacciHeapNode<T> y, FibonacciHeapNode<T> x)
    {

        //Remove y from the root list
        y.left.right = y.right;
        y.right.left = y.left;

        y.parent = x;

        //Insert y in x's children list
        if (x.hasChild())
        {
            insertInNodeList(x.child, y);
        }
        else
        {
            y.left = y.right = y;
            x.child = y;
        }
        x.degree += 1;
        y.mark = false;
    }

    public void decreaseKey(T value, int k) throws IllegalArgumentException
    {
        FibonacciHeapNode<T> x = nodeMap.get(value);

        if (k > x.key)
        {
            throw new IllegalArgumentException("New key is greater than current key");
        }

        x.key = k;

        FibonacciHeapNode<T> y = x.parent;

        if (y != null && x.key < y.key)
        {
            cut(x, y);
            cascadingCut(y);
        }

        if (x.key < minNode.key)
        {
            minNode = x;
        }
    }

    private void cut(FibonacciHeapNode<T> x, FibonacciHeapNode<T> y)
    {
//        if (y.child == x)
//        {
//            y.child = y.child.right;
//            y.child.left = y.child;
//        }
//        else
//        {
//            FibonacciHeapNode<T> currentRight = y.child.right;
//            FibonacciHeapNode<T> currentLeft = y.child.left;
//
//            while (currentRight != y.child && currentLeft != y.child)
//            {
//                if (currentRight == x)
//                {
//                    FibonacciHeapNode<T> temp = currentRight.left;
//                    currentRight = currentRight.right;
//                    currentRight.left = temp;
//                    temp.right = currentRight;
//                }
//                if (currentLeft == x)
//                {
//                    FibonacciHeapNode<T> temp = currentLeft.left;
//                    currentLeft = currentLeft.right;
//                    currentLeft.left = temp;
//                    temp.right = currentLeft;
//                }
//            }
//        }
//        y.degree -= 1;
//        x.parent = null;
//        x.mark = false;
        if (x.right == x)
        {
            y.child = null;

        }
        else if (x == minNode)
        {
            extractMin();
        }
        else
        {
            y.child = x.right;
            x.right.left = x.left;
            x.left.right = x.right;
        }
        
        y.degree--;

        insertInNodeList(minNode, x);

        x.parent = null;
        x.mark = false;
    }

    private void cascadingCut(FibonacciHeapNode<T> y)
    {
        FibonacciHeapNode<T> z = y.parent;

        if (z != null)
        {
            if (!y.mark)
            {
                y.mark = true;
            }
            else
            {
                cut(y, z);
                cascadingCut(z);
            }
        }
    }

    public void delete(T value)
    {

        decreaseKey(value, Integer.MIN_VALUE);
        extractMin();


    }

    private class FibonacciHeapNode<T>
    {

        public int degree;
        public boolean mark;
        public FibonacciHeapNode<T> parent;
        public FibonacciHeapNode<T> child;
        public FibonacciHeapNode<T> right;
        public FibonacciHeapNode<T> left;
        public T value;
        public int key;

        public FibonacciHeapNode(T value, int key)
        {
            left = right = this;
            degree = 0;
            parent = null;
            child = null;
            mark = false;
            this.value = value;
            this.key = key;
        }

        public boolean hasChild()
        {
            return child != null;
        }
    }
}
