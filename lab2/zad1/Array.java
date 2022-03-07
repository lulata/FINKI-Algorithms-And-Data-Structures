package APS.lab2.zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Array<E> {


    public static int brojDoProsek(int[] a) {
        //Vashiot kod tuka...
        double avg= Arrays.stream(a).sum() /(double) a.length;
        int min=a[0];
        double diffrence=Math.abs((double) min-avg);
        for (int i : a) {
            if (Math.abs((double) i - avg) < diffrence) {
                diffrence = Math.abs((double) i - avg);
                min = i;
            } else if (Math.abs((double) i - avg) == diffrence) {
                if (i < min) {
                    min = i;
                    diffrence = Math.abs((double) i - avg);
                }
            }
        }return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        //Vashiot kod tuka...
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(stdin.readLine());
        }

        System.out.println(brojDoProsek(a));
    }


}
