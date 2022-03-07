package APS.lab6.zad3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
public class Preveduvac {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(br.readLine());
        HashMap<String, String> map = new HashMap<>();
        String s= null;
        for(int i=0; i<N; i++){
            s= br.readLine();
            String[] parts = s.split(" ");
            map.put(parts[1], parts[0]);
        }
        s= br.readLine();
        while (!(s.contentEquals("KRAJ"))){
            if(map.containsKey(s)){
                System.out.println(map.get(s));
            }else{
                System.out.println("/");
            }
            s= br.readLine();
        }
    }
}
