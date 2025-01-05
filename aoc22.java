import java.io.*;
import java.util.*;

public class aoc22 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc22.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc22.out"));

        
        String line = "";
        long ret = 0;
        while((line=br.readLine())!=null){
            long secret = Long.parseLong(line);
            for(int i=0; i<2000; i++){
                secret = generateSecret(secret, i);
                //pr.println(secret);
            }
            ret+= secret;
        }
        pr.println(ret);
        pr.close();
        br.close();
    }
    static long generateSecret(long secret, int i){
        secret = ((secret) ^ (secret * 64)) % 16777216;
        secret = ((secret/32) ^ (secret)) % 16777216;
        return ((secret*2048) ^ (secret)) % 16777216; 
    }
}
