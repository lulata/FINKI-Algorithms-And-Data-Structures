package APS.lab3.zad3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {

    static int solve(int[] numbers, int N, int K) {
        // vasiot kod ovde
        // mozete da napisete i drugi funkcii dokolku vi se potrebni
        int counter = 2;
        int j, i;
        int[][] maxabs = new int[K + 1][N];
        int abs = 0, max = 0;

        while (counter <= K) {
            for (i = counter - 1; i < N; i++) {
                for (j = 0; j < i; j++) {
                    abs = Math.abs(numbers[j] - numbers[i]) + maxabs[counter - 1][j];
                    if (abs > maxabs[counter][i]) {
                        maxabs[counter][i] = abs;
                    }
                }
            }
            counter++;
        }
        for (i = 0; i < N; i++) {
            if (maxabs[K][i] > max) {
                max = maxabs[K][i];
            }
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();
    }
}