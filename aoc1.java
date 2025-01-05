
import java.io.*;
import java.util.*;

public class aoc1{
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("aoc1.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc1.out"));

        int [] arrList1 = new int[1000];
        int [] arrList2 = new int[1000];
        

        for(int i=0; i<1000; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            arrList1[i]=p;
            arrList2[i]=q;
        }
        Map<Integer, Integer> mp = new HashMap<>();
        /*
         * Arrays.sort(arrList1);
        Arrays.sort(arrList2);
        */
        int ret = 0;
        for(int i=0; i<1000; i++){
            //System.out.println("putted" + mp.getOrDefault(i, 0)+1);
            mp.put(arrList2[i], mp.getOrDefault(arrList2[i], 0)+1);
        }
        for(int i=0; i<1000; i++){
            if(mp.containsKey(arrList1[i])){
                //System.out.println("added" + mp.get(arrList1[i]) * arrList1[i]);
                ret+=mp.get(arrList1[i]) * arrList1[i];
            }
        }
        pr.println(ret);
        pr.close();
    }
}