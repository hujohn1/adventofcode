import java.io.*;
import java.util.*;

public class aoc11_2{
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("aoc11.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc11_2.out"));

        int ret = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<String> arrList = new ArrayList<>();
        while(st.hasMoreTokens()){
            arrList.add(st.nextToken());
            pr.println(st.nextToken());
        }
        Map<String, Integer> mp1 = new HashMap<>();
        Map<String, Integer> mp2 = new HashMap<>();
        for(String s: arrList){
            mp1.putIfAbsent(s, 1+mp1.getOrDefault(s, 0));
        }
        for(int i=0; i<75; i++){
            for(String s: mp1.keySet()){
                if(s.length()%2==0){
                    String left = s.substring(0, s.length()/2);
                    String right = s.substring(s.length()/2, s.length());
                    mp2.putIfAbsent(right, 1+mp2.getOrDefault(right, 0));
                    mp2.putIfAbsent(left, 1+mp2.getOrDefault(left, 0));
                }
                else if(s=="0"){
                    mp2.putIfAbsent("1", mp1.getOrDefault("0", 0));
                }
                else{
                    mp2.putIfAbsent(String.valueOf(2024*Integer.valueOf(s)), mp.getOrDefault(String.valueOf(2024*Integer.valueOf(s))));
                }
           }
        }


        
        pr.println(arrList.size());
        pr.close();
    }
}