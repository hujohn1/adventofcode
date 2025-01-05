import java.io.*;
import java.util.*;

public class aoc23 {
    static Map<String, Set<String>> adjLists;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc23.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc23.out"));
        PrintWriter pr2 = new PrintWriter(new FileWriter("aoc23_2.out"));

        String line = "";
        adjLists = new HashMap<>();
        while((line=br.readLine())!=null){
            String [] in = line.split("-");
            String first = in[0];
            String second = in[1];
            if(!adjLists.containsKey(first)){
                adjLists.put(first, new HashSet<String>());
            }
            if(!adjLists.containsKey(second)){
                adjLists.put(second, new HashSet<String>());
            }
            adjLists.get(first).add(second);
            adjLists.get(second).add(first);
        }
        int numCliques = 0;
        for(String s: adjLists.keySet()){
            for(String s2: adjLists.get(s)){
                for(String s3: adjLists.get(s2)){
                    if(adjLists.get(s3).contains(s) && (s.charAt(0)=='t' || s2.charAt(0)=='t' || s3.charAt(0)=='t')){
                        pr.println("("+s+","+s2+","+s3+")");
                        numCliques++;
                    }
                }
            }
        }
        pr.println(numCliques/(3*2*1));
        pr.close();
        br.close();


        ////////////////////////////PART 2////////////////////////////
        Set<String> R = new HashSet<>();
        Set<String> X = new HashSet<>();
        Set<String> P = new HashSet<>();
        ArrayList<Set<String>> maximalCliques = new ArrayList<>();
        for(String s: adjLists.keySet()){
            P.add(s);
        }
        bronkerbosch(R, P, X, maximalCliques);
        /* 
        for(Set<String> st: maximalCliques){
            pr2.println(st.size());
            for(String sr: st){
                pr2.print(sr+"<->");
            }
            pr2.println();
        }*/
        int maxIdx = -1;
        int maxSize = Integer.MIN_VALUE;
        for(int i=0; i<maximalCliques.size(); i++){
            if(maximalCliques.get(i).size()>maxSize){
                maxIdx = i; maxSize = maximalCliques.get(i).size();
            }
        }
        pr2.println(setToString(maximalCliques.get(maxIdx)));
        pr2.close();
    }
    static String setToString(Set<String> st){
        String [] temp = new String [st.size()];
        int i = 0;
        for(String s: st){
            temp[i]=s; i++;
        }
        Arrays.sort(temp);
        return String.join(",", temp);
    }

    static void bronkerbosch(Set<String> R, Set<String> P, Set<String> X, ArrayList<Set<String>> max){
        if(P.isEmpty() && X.isEmpty()){
            max.add(new HashSet<>(R));
            return;
        }
        for(String s: new HashSet<>(P)){
            Set<String> neighbors = adjLists.getOrDefault(s, Collections.emptySet());
            Set<String> newR = new HashSet<>(R);
            newR.add(s);
            Set<String> newP = new HashSet<>(P);
            newP.retainAll(neighbors);
            Set<String> newX = new HashSet<>(X);
            newX.retainAll(neighbors);

            bronkerbosch(newR, newP, newX, max);     
            P.remove(s);
            X.add(s);
        }
    }
}