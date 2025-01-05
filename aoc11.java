import java.io.*;
import java.util.*;

public class aoc11{
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("aoc11.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc11.out"));

        int ret = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<String> arrList = new ArrayList<>();
        while(st.hasMoreTokens()){
            arrList.add(st.nextToken());
        }
        for(int i=0; i<75; i++){
            int ptr = 0; 
            while(ptr<arrList.size()){
                String curr = arrList.get(ptr);
                if(Long.parseLong(curr)==0){
                    arrList.add(ptr, "1"); 
                    arrList.remove(ptr+1);
                    ptr++;
                }
                else if(curr.length()>1 && curr.length()%2==0){
                    String left = curr.substring(0,curr.length()/2);
                    String right = curr.substring(curr.length()/2,curr.length());

                    arrList.add(ptr, String.valueOf(Integer.parseInt(left)));
                    arrList.add(ptr+1, String.valueOf(Integer.parseInt(right)));
                    arrList.remove(ptr+2);
                    ptr+=2;
                }
                else{
                    Long val = Long.parseLong(curr);
                    arrList.set(ptr, String.valueOf(val*2024));
                    ptr++;
                } 
            }
        }
        pr.println(arrList.size());
        pr.close();
    }
}