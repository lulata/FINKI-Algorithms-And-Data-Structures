package APS.lab4.zad3;


import java.util.NoSuchElementException;
import java.util.Scanner;

interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

     boolean isEmpty ();
    // Vrakja true ako i samo ako redicata e prazena.

     int size ();
    // Ja vrakja dolzinata na redicata.

     E peek ();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear ();
    // Ja prazni redicata.

    public void enqueue (E x);
    // Go dodava x na kraj od redicata.

    public E dequeue ();
    // Go otstranuva i vrakja pochetniot element na redicata.

}

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


class Gragjanin {
    private int id;
    private int drivingLicense;
    private int passport;
    private String name;

    public Gragjanin(String name,int id, int drivingLicense, int passport ) {
        this.id = id;
        this.drivingLicense = drivingLicense;
        this.passport = passport;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getDrivingLicense() {
        return drivingLicense;
    }

    public int getPassport() {
        return passport;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}


public class MVR {

    public static void main(String[] args) {
        Scanner br = new Scanner(System.in);
        LinkedQueue<Gragjanin> iD = new LinkedQueue<Gragjanin>();
        LinkedQueue<Gragjanin> drivingLicence = new LinkedQueue<Gragjanin>();
        LinkedQueue<Gragjanin> passport = new LinkedQueue<Gragjanin>();

        int N = Integer.parseInt(br.nextLine());
        for (int i = 1; i <= N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);

            if (covek.getId() == 1) {
                iD.enqueue(covek);
            }else if (covek.getDrivingLicense() == 1) {
                drivingLicence.enqueue(covek);
            }else if (covek.getPassport() == 1) {
                passport.enqueue(covek);
            }
        }

        while (!iD.isEmpty()) {
            Gragjanin person = iD.dequeue();
            if (person.getDrivingLicense() == 1) {
                drivingLicence.enqueue(person);
            }else if(person.getPassport() == 1) {
                passport.enqueue(person);
            }else{
                System.out.println(person);
            }
        }while(!drivingLicence.isEmpty()){
            Gragjanin person = drivingLicence.dequeue();
            if (person.getPassport() == 1) {
                passport.enqueue(person);
            }else{
                System.out.println(person);
            }
        }while (!passport.isEmpty()) {
            Gragjanin person = passport.dequeue();
            System.out.println(person);
        }
    }
}

