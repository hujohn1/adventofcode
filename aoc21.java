import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class aoc21{
    static char grid [][];
    static int N; 
    static int M;

    /*my input
    341A
    083A
    802A
    973A
    780A
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc21.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc21.out"));

        String code = ""; 
        int ret = 0;
        while((code=br.readLine())!=null){
            Pattern p = Pattern.compile("(\\d+)(\\D)");
            Matcher m = p.matcher(code);
            pr.println(m.group(0));
            char [] ch1 = findShortest(m.group(1).toCharArray());

            char [] ch2 = findShortest(ch1);
            char [] ch3 = findShortest(ch2);
            ret += ch3.length * Integer.parseInt(m.group(1));
        }
        pr.close();
        br.close();

    }
    static char [] findShortest(char [] charArray){
        return new char []{'B'};
    }
}
