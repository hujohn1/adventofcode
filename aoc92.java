import java.io.*;
import java.util.*;

public class aoc92 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc9.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc92.out"));

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
        int l=0, r = n-1; 
        while(l < r){
            if(arrList.get(l)==-1){
                int freespace = 0; int j = l;
                while(arrList.get(j)==-1){
                    freespace++;
                    j++;
                }
                pr.println("freespace"+freespace);
                l = j;
                
                int k = r;
                while(k > l){
                    int blockspace = 0;
                    while(arrList.get(k)!=-1 && arrList.get(k)==arrList.get(r)){
                        blockspace++; 
                        k--;
                    }
                    System.out.println("k: "+k);
                    if(blockspace <= freespace){
                        pr.println("block: "+blockspace);
                        for(int p=l; p<l+freespace; p++){
                            arrList.set(p, arrList.get(k));
                        }
                        for(int p=k+1; p<k+1+blockspace; p++){
                            arrList.set(p, -1);
                        }
                        l+=blockspace;
                        break;
                    }
                    else{
                        while (k >= 0 && arrList.get(k) == -1) {
                            k--;
                        }

                    }
                }
            }
            else{
                l++;
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
