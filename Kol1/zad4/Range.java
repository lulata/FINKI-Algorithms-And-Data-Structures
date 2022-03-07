package APS.Kol1.zad4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.*;

public class Range {

    private static long sum(long x){
        long sum = 0;
        while(x>0){
            sum += x%10;
            x /= 10;
        }
        return sum;
    }

    private static long function(long x) {
        return (long) Math.pow(x,2)+sum(x)+200*x;
    }


    static long proveri(long N, long A, long B) {
        while(A<=B){
            long middle = (A+B)/2;
            if (function(middle)==N){
                return middle;
            } else if(function(middle)>N){
                B = middle-1;
            } else if (function(middle)<N){
                A = middle+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N =  Long.parseLong(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long A =  Long.parseLong(st.nextToken());
        long B =  Long.parseLong(st.nextToken());

        long res = proveri(N, A, B);
        System.out.println(res);

        br.close();

    }

}
