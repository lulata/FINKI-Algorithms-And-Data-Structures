package APS.lab8.zad3;

import java.util.Scanner;

class BinarySearchTree<E extends Comparable<E>> {

    /** The tree root. */
    private BNode<E> root;

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    public void insert(E x) {
        root = insert(x, root);
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     */
    private BNode<E> insert(E x, BNode<E> t) {
        if (t == null) {
            t = new BNode<E>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        } else;  // Duplicate; do nothing
        return t;
    }


    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public BNode<E> find(E x) {
        return find(x, root);
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private BNode<E> find(E x, BNode<E> t) {
        if (t == null)
            return null;

        if (x.compareTo(t.info) < 0) {
            return find(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            return find(x, t.right);
        } else {
            return t;    // Match
        }
    }


    public int height(E number) {
        return height(find(number));
    }

    public int height(BNode<E> node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public int nodesOnDepth(int number) {
        return nodesOnDepth(root,number,0);
    }

    private int nodesOnDepth(BNode<E> rt, int number,int depth) {

        if (rt != null && depth == number)
            return 1;
        if (rt == null)
            return 0;
        return nodesOnDepth(rt.left,number,depth+1) + nodesOnDepth(rt.right,number,depth+1);
    }

}

class BNode<E extends Comparable<E>> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

public class BinarnoDrvo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            tree.insert(x);
        }
        int depth = sc.nextInt();
        int height = tree.height(depth);
        System.out.println(height);
        System.out.println(tree.nodesOnDepth(height));
    }
}
