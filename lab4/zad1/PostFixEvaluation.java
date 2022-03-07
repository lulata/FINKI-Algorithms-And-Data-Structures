package APS.lab4.zad1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

public class PostFixEvaluation {

    public static int evaluate(String expression) {
        Stack<Integer> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(expression);


        while (st.hasMoreTokens()) {
            String nextToken = st.nextToken();
            if (nextToken.equals("+")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b);
            } else if (nextToken.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else if (nextToken.equals("*")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
            } else if (nextToken.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.parseInt(nextToken));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char[] exp = expression.toCharArray();
        System.out.println(evaluate(expression));

        br.close();

    }

}

