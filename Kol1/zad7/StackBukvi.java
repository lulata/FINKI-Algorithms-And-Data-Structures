package APS.Kol1.zad7;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear();
    // Go prazni stekot.

    public void push(E x);
    // Go dodava x na vrvot na stekot.

    public E pop();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth - 1];
    }


    public void clear() {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }


    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class StackBukvi {
    static int proveri_t_posle_s(char[] St) {
        Stack<Character>  sentenceReversed= new ArrayStack<Character>(100);
        Stack<Character>  sentence= new ArrayStack<Character>(100);
        int counterT = 0, match =100;

        for (char c : St) {
            if (c == 'T' || c == 'S') {
//                sentenceReversed.push(c);
                sentence.push(c);
            }
        }
        //Ova tera kako so e inputo a vas vi e odnopaku :)
//        while(!sentenceReversed.isEmpty()){
//            sentence.push(sentenceReversed.pop());
//        }
//        if (sentence.peek() == 'T') {
//            sentence.pop();
//        }sentence.pop();
        while (!sentence.isEmpty()) {
            char letter = sentence.pop();
            if(letter=='T'){
                counterT++;
            }else if (letter == 'S') {
                if (match==100){
                    match=counterT;
                }else if(match!=counterT){
                    return 0;
                }match=counterT;
                counterT=0;
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        char[] niza = new char[100];

        Scanner f = new Scanner(System.in);
        String st = f.next();
        niza = st.toCharArray();

        int rez = proveri_t_posle_s(niza);
        System.out.println(rez);
    }


}
