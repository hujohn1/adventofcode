import java.io.*;

public class aoc4_2{
    static int ret;
    static char [][] chArr;
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("aoc4.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc4.out"));

        int N = 140;
        chArr = new char[N][N];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<N; j++){
                chArr[i][j]=line.charAt(j);
                pr.print(chArr[i][j]);
            }
            pr.println();
        }
        ret = 0;
        for(int i=0; i<=N-3; i++){
            for(int j=0; j<=N-3; j++){
                //pr.println("checkSubArray("+i+","+j+")");
                //pr.println("tuple("+chArr[1][6]+","+chArr[1][6+2]+","+chArr[1+1][6+1]+","+chArr[1+2][6]+","+chArr[1+2][6+2]+")");
                checkSubArray(i,j);
            }
        }
      
        pr.println(ret);
        pr.close();
    }
    static boolean checkSubArray(int startRow, int startCol){
        boolean isGood = false;
        if(chArr[startRow][startCol] == 'M' && chArr[startRow][startCol+2] == 'S'
           && chArr[startRow+1][startCol+1] == 'A'
           && chArr[startRow+2][startCol] == 'M' && chArr[startRow+2][startCol+2] == 'S') {
            ret += 1;
            isGood = true;
        } 
        else if(chArr[startRow][startCol] == 'S' && chArr[startRow][startCol+2] == 'S'
                && chArr[startRow+1][startCol+1] == 'A'
                && chArr[startRow+2][startCol] == 'M' && chArr[startRow+2][startCol+2] == 'M') {
            ret += 1;
            isGood = true;
        } 
        else if(chArr[startRow][startCol] == 'S' && chArr[startRow][startCol+2] == 'M'
                && chArr[startRow+1][startCol+1] == 'A'
                && chArr[startRow+2][startCol] == 'S' && chArr[startRow+2][startCol+2] == 'M') {
            ret += 1;
            isGood = true;
        } 
        else if(chArr[startRow][startCol] == 'M' && chArr[startRow][startCol+2] == 'M'
                && chArr[startRow+1][startCol+1] == 'A'
                && chArr[startRow+2][startCol] == 'S' && chArr[startRow+2][startCol+2] == 'S') {
            ret += 1;
            isGood = true;
        } 
        return isGood;
    }
}
    
