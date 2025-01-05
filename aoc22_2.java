import java.io.*;
import java.util.*;

public class aoc22_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc22.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc22_2.out"));

        String line = "";
        List<long []> buyers = new ArrayList<>();
        while((line=br.readLine())!=null){
            long [] buyer = new long[2001];
            long secret = Long.parseLong(line);
            buyer[0]=secret%10;
            for(int i=0; i<2000; i++){
                secret = generateSecret(secret, i);
                buyer[i+1]=secret%10;
            }
            buyers.add(buyer);
        }
        Map<Quartet, Integer> finalMap = new HashMap<>();
        for(long [] ls: buyers){
            Set<Quartet> seen = new HashSet<>();
            for(int i=0; i<ls.length-4; i++){
                long a = ls[i+1]-ls[i];
                long b = ls[i+2]-ls[i+1];
                long c = ls[i+3]-ls[i+2];
                long d = ls[i+4]-ls[i+3];

                Quartet q = new Quartet(a, b, c, d);
                if(!seen.contains(q)){
                    seen.add(q);
                    if(finalMap.containsKey(q)){
                        finalMap.put(q, (int)(ls[i+4]+finalMap.get(q)));
                    }
                    else{
                        finalMap.put(q, (int)ls[i+4]);
                    }
                }
                
            }
        }
        int max = Integer.MIN_VALUE;
        int i = 0;
        for(Quartet q: finalMap.keySet()){
            //pr.println(q.toString()+":"+finalMap.get(q));
            max=Math.max(max, finalMap.get(q));
        }
        pr.println(max);
        pr.close();
        br.close();
    }
    static class Quartet{
        long a; long b; long c; long d;
        public Quartet(long a, long b, long c, long d){
            this.a = a; this.b=b; this.c=c; this.d=d;
        }
        public int hashCode(){
            return Objects.hash(a, b, c, d);
        }
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Quartet other = (Quartet) obj;
            return a == other.a && b == other.b && c == other.c && d==other.d;
        }
        @Override
        public String toString(){
            return ("("+this.a+","+this.b+","+this.c+","+this.d+")");
        }

    }

    static long generateSecret(long secret, int i){
        secret = ((secret) ^ (secret * 64)) % 16777216;
        secret = ((secret/32) ^ (secret)) % 16777216;
        return ((secret*2048) ^ (secret)) % 16777216; 
    }
}
