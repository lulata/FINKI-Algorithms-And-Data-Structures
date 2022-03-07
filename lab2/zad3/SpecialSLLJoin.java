package APS.lab2.zad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
    protected SLLNode<E> first;

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
            ret.append(tmp).append(" ");
            while (tmp.succ != null) {
                tmp = tmp.succ;
                if (tmp.succ != null) {
                    ret.append(tmp).append(" ");
                } else {
                    ret.append(tmp);
                }
            }
        } else
            ret.append("Prazna lista!!!");
        return ret.toString();
    }

    public void insertFirst(E o) {
        first = new SLLNode<>(o, first);
    }



    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<>(o, null);
        } else {
            insertFirst(o);
        }
    }
    public SLLNode<E> getFirst() {
        return first;
    }

    public void setFirst(SLLNode<E> first) {
        this.first = first;
    }
}


public class SpecialSLLJoin {

    private static SLL<Integer> specialJoin(SLL<Integer> lista1, SLL<Integer> lista2) {
        SLL<Integer> result = new SLL<>();
        SLLNode<Integer> firstList = lista1.getFirst();
        SLLNode<Integer> secondList = lista2.getFirst();
        SLLNode<Integer> tempNode = new SLLNode<>(0, null);
        SLLNode<Integer> temp = tempNode;


        while (firstList != null && firstList.succ != null && secondList != null && secondList.succ != null) {
            temp.succ = firstList;
            firstList = firstList.succ.succ;
            temp = temp.succ.succ;

            temp.succ = secondList;
            secondList = secondList.succ.succ;
            temp = temp.succ.succ;
        }
        while (firstList != null || secondList != null) {
            if (firstList != null){
                temp.succ = firstList;
                temp = temp.succ;
                firstList = firstList.succ;
            }else {
                temp.succ = secondList;
                temp = temp.succ;
                secondList = secondList.succ;
            }
        }

        temp = tempNode.succ;
        tempNode.succ = null;
        result.setFirst(temp);
        return result;

    }

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
        SLL<Integer> spoeni = specialJoin(lista1,lista2);
        System.out.print(spoeni);

    }
}
