import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aoc17 {
    static char grid [][];
    static int A;
    static int B;
    static int C;
    static int ptr;
    static ArrayList<String> returned;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc17.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc17.out"));

        String line = "";
        String instructions = "";
        while((line=br.readLine())!=null){
            String regex1 = "Register (\\D): (\\d+)";
            String regex2 = "Program: (.+)";
            Pattern p = Pattern.compile(regex1);
            Pattern p2 = Pattern.compile(regex2);
            Matcher m = p.matcher(line);
            Matcher m2 = p2.matcher(line);

            if(m.find()){
                if(m.group(1).equals("A")){
                    A = Integer.parseInt(m.group(2));
                }
                else if(m.group(1).equals("B")){
                    B = Integer.parseInt(m.group(2));
                }
                else if(m.group(1).equals("C")){
                    C = Integer.parseInt(m.group(2));
                }
            }
            else if(m2.find()){
                instructions = m2.group(1);
            }   
        }
        ptr = 0;
        String [] instructs = instructions.split(",");
        returned = new ArrayList<>();
        while(ptr+1 < instructs.length){
            int opc = Integer.parseInt(instructs[ptr]);
            int opr = Integer.parseInt(instructs[ptr+1]);
            pr.println(opc+","+opr);
            ptr+=2; 
            evaluate(opc, opr);
            pr.println("A:"+A+"B:"+B+"C:"+C);
        }
        for(int i=0; i<returned.size(); i++){
            pr.print(returned.get(i)+",");
        }
        
        pr.close();
        br.close();
    }
    static void evaluate(int opc, int opr){
        if(opc==0){ //adv
            A = A/(1<<combo(opr));
        }
        else if(opc==1){ //bxl
            B = B^opr;
        }
        else if(opc==2){ //bst
            B = combo(opr)%8;
        }
        else if(opc==3){//jnz
            if(A!=0){
                ptr=opr;
            }
        }
        else if(opc==4){//jnz
            B = B^C;
        }
        else if(opc==5){
            returned.add(String.valueOf(combo(opr)%8));
        }
        else if(opc==6){
            B = A/(1<<combo(opr));
        }
        else if(opc==7){
            C = A/(1<<combo(opr));
        }
    }

    static int combo(int opr){
        int ret=0;;
        if(opr>=0 && opr<=3){
            ret = opr;
        }
        else if(opr==4){
            ret =  A;
        }
        else if(opr==5){
            return B;
        }
        else if(opr==6){
            return C;
        }
        return ret;
    }
}
