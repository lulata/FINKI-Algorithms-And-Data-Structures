package APS.lab5.zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OddEvenSort {

    private static void descSort(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] < a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    private static void ascSort(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    static void oddEvenSort(int[] a, int n) {
        // Vasiot kod tuka
        int[] even = new int[n];
        int[] odd = new int[n];
        int oddCoutner=0, evenCoutner=0;

        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                even[evenCoutner] = a[i];
                evenCoutner++;
            } else if (a[i] % 2 != 0) {
                odd[oddCoutner] = a[i];
                oddCoutner++;
            }
        }
        ascSort(odd, oddCoutner);
        descSort(even, evenCoutner);


        for (int i = 0; i < n; i++) {
            if (i<oddCoutner) {
                a[i] = odd[i];
            }else{
                a[i] = even[i-oddCoutner];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String[] pom = s.split(" ");
        int[] a = new int[n];
        for (i = 0; i < n; i++)
            a[i] = Integer.parseInt(pom[i]);
        oddEvenSort(a, n);
        for (i = 0; i < n - 1; i++)
            System.out.print(a[i] + " ");
        System.out.print(a[i]);
    }
}
