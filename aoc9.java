import java.io.*;
import java.util.*;

public class aoc9 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc9.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc9.out"));

        String line = br.readLine(); 
        ArrayList<Integer> arrList = new ArrayList<>();
        int id = 0;
        for(int i=0; i<line.length(); i++){
            int count = line.charAt(i)-'0';
            if(i%2==1){
                for(int j=0; j<count; j++){
                    arrList.add(-1);
                }
            }
            else{
                for(int j=0; j<count; j++){
                    arrList.add(id);
                }
                if(count!=0){
                    id++;
                }
            }
        }

        long count = 0;
        int n = arrList.size();
        for(int i=0; i<n; i++) {
            if(arrList.get(i)!=-1) {
                count++;
            }
        }
        //pointer method
        int pointer = n-1;
        for(int i=0; i<count; i++){
            if(arrList.get(i)==-1){
                while(arrList.get(pointer)==-1){
                    pointer--;
                }
                if(i<pointer){
                    arrList.set(i, arrList.get(pointer));  
                    arrList.set(pointer, -1);  
                    pointer--;
                }
            }
        }
        for(int j=0; j<arrList.size(); j++){
            pr.print(arrList.get(j));
        }
        pr.println();
        long checksum = 0;
        for(int i=0; i<arrList.size(); i++){
            if(arrList.get(i)!=-1){
                checksum+=(long)i * arrList.get(i);
            }
        }
        pr.println(checksum);
        pr.close();
        br.close();
    }
}
