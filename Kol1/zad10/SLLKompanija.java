package APS.Kol1.zad10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class SLLNode {
    protected int id;
    protected int plata;
    protected SLLNode succ;

    public SLLNode(int id, int plata, SLLNode succ) {
        this.id = id;
        this.plata = plata;
        this.succ = succ;
    }


}

class SLL {
    private SLLNode first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }


    public void insertFirst(int id, int plata) {
        SLLNode ins = new SLLNode(id, plata, first);
        first = ins;
    }

    public void insertLast(int id, int plata) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(id, plata, null);
            tmp.succ = ins;
        } else {
            insertFirst(id, plata);
        }
    }

    public SLLNode getFirst() {
        return first;
    }


    public SLL brisi_pomali_od(int iznos) {
        // Vasiot kod tuka
        SLLNode node = first;
        SLL result = new SLL();

        while (node != null) {
            if (node.plata < iznos) {
                node = node.succ;
            } else {
                result.insertLast(node.id, node.plata);
                node = node.succ;
            }
        }

        return result;
    }

    public SLL sortiraj_opagacki() {
        // Vasiot kod tuka
        SLLNode node = first;
        SLLNode index = first;
        int temp, temp2;

        while (node != null) {
            index = node.succ;
            while (index != null) {
                if (node.id < index.id) {
                    temp = node.id;
                    temp2 = node.plata;
                    node.id = index.id;
                    node.plata = index.plata;
                    index.id = temp;
                    index.plata = temp2;
                }
                index = index.succ;
            }
            node = node.succ;
        }
        return this;
    }

    public void pecati(SLL lista) {
        SLLNode p = lista.first;
        while (p != null) {
            System.out.println(p.id + " " + p.plata);
            p = p.succ;
        }
    }

}

public class SLLKompanija {
    public static void main(String[] args) throws IOException {

        SLL lista1 = new SLL();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        for (int i = 0; i < N; i++) {
            s = stdin.readLine();
            String s1 = stdin.readLine();
            lista1.insertLast(Integer.parseInt(s), Integer.parseInt(s1));
        }
        s = stdin.readLine();

        lista1 = lista1.brisi_pomali_od(Integer.parseInt(s));
        if(lista1.length() == 0){
            System.out.println("nema");
        }
        if (lista1 != null) {
            lista1 = lista1.sortiraj_opagacki();
            lista1.pecati(lista1);
        }

    }
}
