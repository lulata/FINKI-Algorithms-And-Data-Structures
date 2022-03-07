package APS.lab4.zad2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class CheckXML {

    private static int checkXMLElement(String[] s) {
        Stack<String> tagsCounter = new Stack<String>();
        for (String tags : s) {
            if (tags.charAt(0) == '[' && tags.charAt(1) != '/') {
                tagsCounter.push(tags);
            } else if (tags.charAt(0) == '[' && tags.charAt(1) == '/') {
                if (!tagsCounter.peek().substring(1).equals(tags.substring(2)) || tagsCounter.empty()) {
                    return 0;
                } else {
                    tagsCounter.pop();
                }
            }
        }
        if (tagsCounter.empty()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String[] redovi = new String[n];

        for (int i = 0; i < n; i++)
            redovi[i] = br.readLine();

        int valid = checkXMLElement(redovi);


        System.out.println(valid);

        br.close();
    }
}
