package APS.lab3.zad1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
   static int calculate(char[] exp, int l, int r) {
        int result = 0;
        int i = l;
        int j;

        while (i <= r) {
            if (exp[i] == '(') {
                j = findClosingBracket(exp, i);
                result = calculate(exp, i + 1, j - 1);
                i = j + 1;
            } else if (exp[i] == '+') {
                result += calculate(exp, i + 1, r);
                i = r + 1;
            } else if (exp[i] == '-') {
                result -= calculate(exp, i + 1, r);
                i = r + 1;
            } else {
                result = Integer.parseInt(String.valueOf(exp[i]));
                i++;
            }
        }return result;
    }

    private static int findClosingBracket(char[] c, int i) {
        int j = i;
        int bracketCount = 1;

        while (bracketCount > 0) {
            j++;
            if (c[j] == '(') {
                bracketCount++;
            } else if (c[j] == ')') {
                bracketCount--;
            }
        }
        return j;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int result = calculate(exp, 0, exp.length - 1);
        System.out.println(result);

        br.close();

    }

}
