import java.io.*;
import java.util.*;

public class aoc13{
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("aoc13.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc13.out"));

        String line = "";
        long ret =0 ;
        while((line=br.readLine())!=null){
            if (line.trim().isEmpty()) {
                continue;
            }
            String line1 = line;
            String line2 = br.readLine();
            String line3 = br.readLine();
            
            if (line2 == null || line3 == null) {
                break;
            }
            
            int xa = 0, ya = 0;
            int xb = 0, yb = 0;
            Long n; Long m;
            
            String [] buttonA = line1.split(":")[1].split(", ");
            xa = Integer.parseInt(buttonA[0].split("X+")[1]);
            ya = Integer.parseInt(buttonA[1].split("Y+")[1]);
        
            
            String [] buttonB = line2.split(":")[1].split(", ");
            xb = Integer.parseInt(buttonB[0].split("X+")[1]);
            yb = Integer.parseInt(buttonB[1].split("Y+")[1]);
            
            
            String [] prize = line3.split(":")[1].split(", ");
            n = Long.parseLong(prize[0].split("X=")[1]);
            m = Long.parseLong(prize[1].split("Y=")[1]);
            
            n+=(long)1e13;
            m+=(long)1e13;

            long denom = (long) xa * yb - (long) xb * ya;
            long numX = (long) n * yb - (long) xb * m;
            long numY = (long) xa * m - (long) n * ya;

            if (denom!=0 && numX%denom==0 && numY%denom==0) {
                long x = numX / denom;
                long y = numY / denom;
                ret += 3 * x + y;
            }
        }
        pr.println(ret);
        pr.close();
    }
    static int [] egcd(int a, int b){
        int x=0; int y = 1; int u = 1; int v = 0;
        while(a!=0){
            int q=Math.floorDiv(b,a); int r=  b%a;
            int m = x-u*q; int n = y-v*q;
            b=a; a=r; x=u; y=v; u=m; v=n;
        }
        int gcd = b;
        int [] arr = new int[3];
        arr[0]=gcd;
        arr[1]=x;
        arr[2]=y;
        return arr; 
    }
}