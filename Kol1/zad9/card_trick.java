package APS.Kol1.zad9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
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


interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size ();
    // Ja vrakja dolzinata na redicata.

    public E peek ();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear ();
    // Ja prazni redicata.

    public void enqueue (E x);
    // Go dodava x na kraj od redicata.

    public E dequeue ();
    // Go otstranuva i vrakja pochetniot element na redicata.

}




class LinkedQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...

    public LinkedQueue () {
        clear();
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size () {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek () {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear () {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue (E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue () {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null)  rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}


public class card_trick {
    public static int count(int N){
        Queue<Integer> deck = new LinkedQueue<Integer>();
        Stack<Integer> cards = new Stack<Integer>();
        int shuffleCounter = 0;
        final int shuffles=7;
        for (int i = 1; i <= 51; i++) {
            deck.enqueue(i);
        }

        while (deck.peek() != N){
            for (int i = 0; i < shuffles; i++) {
                cards.push(deck.dequeue());
            }
            for (int i = 0; i < shuffles; i++) {
                deck.enqueue(cards.pop());
                deck.enqueue(deck.dequeue());
            }
            ++shuffleCounter;
        }

        return shuffleCounter;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}

