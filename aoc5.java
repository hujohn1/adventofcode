import java.io.*;
import java.util.*;

public class aoc5{
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("aoc5.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc5.out"));

        int cnt = 0;
        Map<Integer, Set<Integer>> priMap = new HashMap<>();
        Map<Integer, Set<Integer>> secMap = new HashMap<>();
        for(int i=0; i<1176; i++){
            String line = br.readLine();
            String delimiter = "\\|";
            String [] parts = line.split(delimiter);
            int first = Integer.parseInt(parts[0]); int second = Integer.parseInt(parts[1]);
            if(priMap.containsKey(first)){
                Set<Integer> st = priMap.get(first);
                st.add(second);
                priMap.put(first, st);
            } else{
                Set<Integer> st = new TreeSet<>();
                st.add(second);
                priMap.put(first, st);
            }   
            if(secMap.containsKey(second)){
                Set<Integer> st = secMap.get(second);
                st.add(first);
                secMap.put(second, st);
            } else{
                Set<Integer> st = new TreeSet<>();
                st.add(first);
                secMap.put(second, st);
            } 
        }
        for(int i=0; i<189; i++){
            String line = br.readLine();
            String [] parts = line.split(",");   
            int [] seq = new int[parts.length];
            for(int j=0; j<parts.length; j++){
                seq[j]=Integer.parseInt(parts[j]);
            }
            
            //inits
            boolean isValid = true;
            for(int j=0; j<parts.length-1; j++){
                if(priMap.containsKey(seq[j])){
                    if(!priMap.get(seq[j]).contains(seq[j+1])){
                        isValid=false;
                    }
                }
            }
            boolean isValid2 = true;
            for(int j=parts.length-1; j>0; j--){
                if(secMap.containsKey(seq[j])){
                    if(!secMap.get(seq[j]).contains(seq[j-1])){
                        isValid=false;
                    }
                }
            }
            if(isValid && isValid2){
                cnt+=seq[(seq.length-1)/2];
            }
        }
        pr.println(cnt);

        br.close();
        pr.close();
    }
}