import java.io.*;

public class aoc4{
    static String w;
    static String rev;
    static int ret;
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("aoc4.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc4.out"));

        int N = 140;
        w = "XMAS"; rev = "SAMX";
        char [][] chArr = new char[N][N];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<N; j++){
                chArr[i][j]=line.charAt(j);
                pr.print(chArr[i][j]);
            }
            pr.println();
        }
        ret = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<=N-w.length(); j++){
                String horiz=""+chArr[i][j]+chArr[i][j+1]+chArr[i][j+2]+chArr[i][j+3];
                String vertic=""+chArr[j][i]+chArr[j+1][i]+chArr[j+2][i]+chArr[j+3][i];
                if(horiz.equals(w) || horiz.equals(rev)){
                    ret++;
                }
                if(vertic.equals(w) || vertic.equals(rev)){
                    ret++;
                }
            }
        }
        for(int k=0;k<N*2;k++ ) {
            String diagonal="";
            for(int j=0; j<=k; j++){
                int i=k - j;
                if(i<N && j<N) {
                    diagonal+=chArr[i][j];
                }
            }
            pr.println(diagonal);
            ret+=checkString(diagonal);
        }
        //check right diagonals
        for (int k = 0; k < N * 2; k++) {
            String diagonal = "";
            for (int j = 0; j <= k; j++) {
                int i = k - j;  // This keeps the sum of i + j constant (diagonal index)
                if (i >= 0 && i < N && j >= 0 && j < N) {
                    diagonal += chArr[i][N - 1 - j];  // Notice this change (N-1-j)
                }
            }
            pr.println(diagonal);
            ret += checkString(diagonal);
        }
        pr.println(ret);
        pr.close();
    }
    static int checkString(String str){
        int cnt = 0;
        if(str.length()<4){
            return 0;
        }
        else{
            for(int i=0; i<=str.length()-w.length(); i++){
                String tmp = str.substring(i, i+4);
                if(tmp.equals(w) || tmp.equals(rev)){
                    cnt++;
                    System.out.println("POINTER"+tmp);
                }
            }
        }
        return cnt;
    }
}
