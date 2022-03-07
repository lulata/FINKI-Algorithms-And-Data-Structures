package APS.Kol1.zad8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class ExpressionEvaluator {


    public static int evaluateExpression(String expression) {
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operation = new Stack<>();
        char[] exp = expression.toCharArray();

        for (int i = 0; i < exp.length; i++) {
            if (Character.isDigit(exp[i])) {
                StringBuilder fullNumber = new StringBuilder();
                for (; i < exp.length && Character.isDigit(exp[i]); i++) {
                    fullNumber.append(exp[i]);
                }
                --i;
                numbers.push(Integer.parseInt(fullNumber.toString()));
            } else if (exp[i] == '+' || exp[i] == '*') {
                operation.push(exp[i]);
            }
        }
        while (!operation.isEmpty()) {
            int a = numbers.pop();
            int b = numbers.pop();
            char op1 = operation.pop();
            if (operation.size() > 0) {
                char op2 = operation.peek();
                if (op1 == '+' && op2 == '*') {
                    op2 = operation.pop();
                    int c = numbers.pop();
                    numbers.push(b * c);
                    numbers.push(a);
                    operation.push(op1);
                } else if (op1 == '+') {
                    numbers.push(a + b);
                } else if (op1 == '*') {
                    numbers.push(a * b);
                }
            } else if (op1 == '+') {
                numbers.push(a + b);
            } else if (op1 == '*') {
                numbers.push(a * b);
            }
        }
        return numbers.pop();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }
}

