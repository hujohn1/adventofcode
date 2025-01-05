import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class aoc24 {
    static PrintWriter pr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc24.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc24.out"));

        String line = "";
        Pattern p = Pattern.compile("(\\w+)\\s(AND|OR|XOR)\\s+(\\w+)\\s+->\\s+(\\w+)");
        Pattern p2 = Pattern.compile("(\\D\\d+):\\s(\\d)");  
        HashMap<String, Integer> bits = new HashMap<>();     
        ArrayList<Instruction> arrList = new ArrayList<>();
        while((line=br.readLine())!=null){
            Matcher m = p.matcher(line);
            Matcher m2 = p2.matcher(line);

            if(m.find()){
                //pr.println(m.group(0));
                String op1 = m.group(1);
                String op2 = m.group(3);
                String op3 = m.group(4);
                String opd = m.group(2);

                bits.putIfAbsent(op1, -1);
                bits.putIfAbsent(op2, -1);
                bits.putIfAbsent(op3, -1);
                Instruction i = new Instruction(op1, op2, op3, opd);
                arrList.add(i);
            }
            else if(m2.find()){
                //pr.println(m2.group(0));
                String op1 = m2.group(1);
                int val = Integer.parseInt(m2.group(2));
                bits.put(op1, val);
            }
        }   
        while(!arrList.isEmpty()){
            for(int i=0; i<arrList.size(); i++){
                Instruction instr = arrList.get(i);
                if(bits.get(instr.op1)!=-1 && bits.get(instr.op2) !=-1){
                    pr.println(instr.toString());
                    if(instr.opd.equals("AND")){
                        bits.put(instr.op3, AND(bits.get(instr.op1), bits.get(instr.op2)));
                    }
                    else if(instr.opd.equals("OR")){
                        bits.put(instr.op3, OR(bits.get(instr.op1), bits.get(instr.op2)));
                    }
                    else if(instr.opd.equals("XOR")){
                        bits.put(instr.op3, XOR(bits.get(instr.op1), bits.get(instr.op2)));
                    }
                    arrList.remove(i);
                    break;
                }
            }
        }
        int numDigits = 0;
        for(String s: bits.keySet()){
            if(s.charAt(0)=='z'){
                numDigits++;
            }
        }
        int [] bitArray = new int[numDigits];
        pr.println("num: "+numDigits);
        for(String s: bits.keySet()){
            if(s.charAt(0)=='z' && bits.get(s)==1){
                bitArray[Integer.parseInt(s.substring(1,s.length()))]=1;
            }
        }
        StringBuilder bitString = new StringBuilder();
        for (int bit : bitArray) {
            bitString.append(bit);
        }
        String reversed = new StringBuilder(bitString.toString()).reverse().toString();
        pr.println(Long.parseLong(reversed, 2));
        pr.close();
        br.close();
    }
    static class Instruction{
        String op1; String op2; String op3; String opd;
        public Instruction(String op1, String op2, String op3, String opd){
            this.op1 = op1; this.op2 = op2; this.op3 = op3; this.opd = opd;
        }
        public String toString(){
            return("("+op1+","+op2+")->"+op3);
        }
    }
    static int AND(int a, int b){
        return (a==1 && b==1 ? 1 : 0);
    }
    static int OR(int a, int b){
        return (a==1 || b==1 ? 1: 0);
    }
    static int XOR(int a, int b){
        return (a!=b ? 1: 0);
    }
}
