package APS.Kol1.zad6;

import java.util.Scanner;

public class LDS {
    private static int najdolgaOpagackaSekvenca(int[] a) {
        // Vasiot kod tuka
        int max=0,counter=1;
        for(int i=0;i<a.length-1;i++){
            if(a[i]>a[i+1]){
                counter++;
            }else if(a[i]<a[i+1]){
                if(counter>max){
                    max=counter;
                }counter=1;
            }
        }
        if(counter>max){
            max=counter;
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}
