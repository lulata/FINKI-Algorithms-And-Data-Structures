package APS.lab2.zad2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;


class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret.append(tmp).append("->");
            while (tmp.succ != null) {
                tmp = tmp.succ;
                if (tmp.succ != null) {
                    ret.append(tmp).append("->");
                } else {
                    ret.append(tmp);
                }
            }
        } else
            ret.append("Prazna lista!!!");
        return ret.toString();
    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public void setFirst(SLLNode<E> first) {
        this.first = first;
    }
    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public Iterator<E> iterator () {
        // Return an iterator that visits all elements of this list, in left-to-right order.
        return new LRIterator<E>();
    }

    private class LRIterator<E> implements Iterator<E> {

        private SLLNode<E> place, curr;

        private LRIterator() {
            place = (SLLNode<E>) first;
            curr = null;
        }

        public boolean hasNext() {
            return (place != null);
        }

        public E next() {
            if (place == null)
                throw new NoSuchElementException();
            E nextElem = place.element;
            curr = place;
            place = place.succ;
            return nextElem;
        }

        public void remove() {
            //Not implemented
        }
    }

    public SLL<Integer> joinLists(SLL<Integer> lista2) {
        SLLNode<Integer> firstList = (SLLNode<Integer>) this.getFirst();
        SLLNode<Integer> secondList = lista2.getFirst();
        SLLNode<Integer> firstNode = new SLLNode<Integer>(0, null);
        SLLNode<Integer> temp = firstNode;
        SLL<Integer> result = new SLL<>();

        while (firstList != null && secondList!=null) {
            if (firstList.element > secondList.element) {
                temp.succ = secondList;
                secondList = secondList.succ;
            }else if (firstList.element < secondList.element) {
                temp.succ = firstList;
                firstList = firstList.succ;
            } else {
                temp.succ = firstList;
                firstList = firstList.succ;
                secondList = secondList.succ;
            }temp = temp.succ;
        }

        if (firstList == null) {
            temp.succ = secondList;
        }else if (secondList == null) {
            temp.succ = firstList;
        }

        temp = firstNode.succ;
        while (temp != null) {
            SLLNode<Integer> prev = temp;
            while (prev != null && prev.element.equals(temp.element)) {
                prev = prev.succ;
            }
            temp.succ = prev;
            temp = temp.succ;
        }

        temp = firstNode.succ;
        firstNode.succ = null;

        result.setFirst(temp);
        return result;
    }
}


public class SLLJoinLists {
    public static void main(String[] args) throws IOException {

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        SLL<Integer> lista1 = new SLL<>();
        SLL<Integer> lista2 = new SLL<>();
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista1.insertLast(Integer.parseInt(pomniza[i]));
        }

        s = stdin.readLine();
        N = Integer.parseInt(s);
        s = stdin.readLine();
        pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista2.insertLast(Integer.parseInt(pomniza[i]));
        }

        // spoeni = lista1.joinLists(lista2);
        SLL<Integer> spoeni = new SLL<>();
        spoeni = lista1.joinLists(lista2);
        Iterator<Integer> it = spoeni.iterator();
        while (it.hasNext()) {

            System.out.print(it.next());
            if (it.hasNext())
                System.out.print(" ");
        }
        System.out.println();
    }
}

