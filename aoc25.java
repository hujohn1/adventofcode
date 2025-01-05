import java.io.*;
import java.util.*;

public class aoc25 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc25.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc25.out"));

        String line = "";
        ArrayList<key> keys = new ArrayList<>();
        ArrayList<lock> locks = new ArrayList<>();
        while((line=br.readLine())!=null){
            //key
            if(line.equals(".....")){
                int [] heights = new int[5];
                for(int i=0; i<5; i++){
                    line = br.readLine();
                    for(int j=0; j<line.length(); j++){
                        if(line.charAt(j)=='#'){heights[j]++;}
                    }
                }
                keys.add(new key(heights));
                line = br.readLine();
            }
            //lock
            else if(line.equals("#####")){
                int [] heights = new int[5];
                for(int i=0; i<5; i++){
                    line = br.readLine();
                    for(int j=0; j<line.length(); j++){
                        if(line.charAt(j)=='#'){heights[j]++;}
                    }    
                }
                locks.add(new lock(heights));
                line = br.readLine();
            }
        }
        int cnt = 0;
        pr.println(locks.size());
        pr.println(keys.size());
        for(lock l: locks){
            for(key k: keys){
                boolean good = true;
                for(int i=0; i<5; i++){
                    if(l.h[i]+k.h[i]>5){
                        good = false;
                    }
                }
                if(good){
                    cnt++;
                }
            }
        }

        pr.println(cnt);
        pr.close();
        br.close();
    }
    static class lock{
        int [] h = new int[5];
        public lock(int [] h){
            this.h = h;
        }
    }
    static class key{
        int [] h = new int[5];
        public key(int [] h){
            this.h = h;
        }
    }
}
