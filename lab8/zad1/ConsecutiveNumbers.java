package APS.lab8.zad1;

import java.util.Scanner;

class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;
    char ltag;
    char rtag;

    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
        ltag = '-';
        rtag = '-';
    }

}

class BTree<E> {

    public BNode<E> head;

    public BTree() {
        head = new BNode<E>(null);
        // po definicija ako nema koren, t.e. ako stebloto e prazno
        head.left = head;
        head.ltag = '-';
        // kaj vodacot sekogas desnata vrska pokazuva kon samiot sebe
        head.right = head;
        head.rtag = '+';
    }

    public BNode<E> makeRoot(E elem) {
        BNode<E> tmp = new BNode<E>(elem);
        head.left = tmp;
        head.ltag = '+';

        tmp.left = head;
        tmp.ltag = '-';
        tmp.right = head;
        tmp.rtag = '-';

        return tmp;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {
        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {

            if (node.ltag == '+')   // veke postoi element
                return null;

            tmp.left = node.left;
            tmp.ltag = '-';
            tmp.right = node;
            tmp.rtag = '-';
            node.left = tmp;
            node.ltag = '+';
        } else {

            if (node.rtag == '+')   // veke postoi element
                return null;

            tmp.right = node.right;
            tmp.rtag = '-';
            tmp.left = node;
            tmp.ltag = '-';
            node.right = tmp;
            node.rtag = '+';
        }

        return tmp;
    }

    public BNode<E> successorInorder (BNode<E> node) {

        if (node.rtag == '-')
            return node.right;

        BNode<E> temp = node.right;
        while (temp.ltag != '-')
            temp = temp.left;
        return temp;
    }

}

public class ConsecutiveNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BTree<Integer> tree = new BTree<Integer>();
        BNode<Integer> root[] = new BNode[n];

        for (int i = 0; i < n; i++) {
            int index = sc.nextInt();
            int value = sc.nextInt();
            String where = sc.next();
            if (where.equals("ROOT")) {
                root[index] = tree.makeRoot(value);
                sc.nextLine();
            }
            else {
                int flag = where.equals("LEFT") ? 1 : 2;
                int witchNode = sc.nextInt();
                sc.nextLine();
                root[index] = tree.addChild(root[witchNode], flag, value);
            }
        }

        System.out.println(checkTree(tree));
    }
    public static boolean checkTree(BTree<Integer> tree) {
        BNode<Integer> node = tree.head.left;
        while (node.ltag == '+'){
            node = node.left;
        }
        BNode<Integer> succ= tree.successorInorder(node);
        while (succ != tree.head) {
            if (node.info + 1 != succ.info)
                return false;
            node = succ;
            succ = tree.successorInorder(node);
        }
        return true;
    }
}
