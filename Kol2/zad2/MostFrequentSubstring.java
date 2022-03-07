package APS.Kol2.zad2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString() {
        return "(" + key + "," + value + ")";
    }
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

class CBHT<K extends Comparable<K>, E> {

    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {        // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K, E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

    public SLLNode<MapEntry<K, E>>[] getBuckets() {
        return buckets;
    }

    public int length() {
        return buckets.length;
    }

}

public class MostFrequentSubstring {
    public static void main(String[] args) throws IOException {
        CBHT<String, Integer> tabela = new CBHT<String, Integer>(300);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine().trim();

        /*
         *
         * Vashiot kod tuka....
         *
         */
        String temp = "";
        for (int i = 0; i < word.length(); i++) {
            for (int j = i; j < word.length(); j++) {
                temp = "";

                for (int k = i; k <= j; k++) {
                    temp += word.charAt(k);
                }

                SLLNode<MapEntry<String, Integer>> curr = tabela.search(temp);

                if (curr == null) {
                    tabela.insert(temp, 1);
                } else {
                    curr.element.value++;
                }
            }
        }

        SLLNode<MapEntry<String, Integer>>[] buckets = tabela.getBuckets();
        int tableLength = tabela.length();
        int max = 0;
        String maxString = "";

        for (int i = 0; i < tableLength; i++) {
            for (SLLNode<MapEntry<String, Integer>> curr = buckets[i]; curr != null; curr = curr.succ) {
                if (curr.element.value > max) {
                    max = curr.element.value;
                    maxString = curr.element.key;
                }
                if (curr.element.value == max) {
                    if (curr.element.key.length() > maxString.length()) {
                        maxString = curr.element.key;
                    }
                    if (curr.element.key.length() == maxString.length()) {
                        if (maxString.compareTo(curr.element.key) > 0) {
                            maxString = curr.element.key;
                        }
                    }
                }
            }
        }
        System.out.println(maxString);
    }
}

