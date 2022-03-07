package APS.lab3.zad2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    static int longestZigZag(int[] a) {
        int counter = 1;
        int max = 0;

        for (int i = 0; i < a.length-1; i++) {
            if (a[i] == 0) {
                counter = 1;
            } else {
                if ((a[i] > 0 && a[i + 1] < 0) || (a[i] < 0 && a[i + 1] > 0)) {
                    counter++;
                } else {
                    counter = 1;
                }
            }
            if (counter > max) {
                max = counter;
            }
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        int i;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        for (i = 0; i < N; i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = longestZigZag(a);
        System.out.println(rez);

        br.close();

    }

}

