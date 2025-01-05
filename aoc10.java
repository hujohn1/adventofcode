import java.io.*;
import java.util.*;

public class aoc10 {
    static int [][] grid;
    static boolean [][]visited;
    static int nRows; 
    static int nCols;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc10.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc10.out"));
        nRows = 45;
        nCols = 45;

        int ret = 0;
        grid = new int [nRows][nRows];
        visited = new boolean [nRows][nCols];
        for(int i=0; i<nRows; i++){
            String line = br.readLine();
            for(int j=0; j<line.length(); j++){
                grid[i][j]=line.charAt(j)-'0';
                pr.print(grid[i][j]);
            }
            pr.println();
        }
        for(int i=0; i<nRows; i++){
            for(int j=0; j<nCols; j++){
                if(grid[i][j]==0){
                    for(int k=0; k<nRows; k++){
                        Arrays.fill(visited[k], false);
                    }
                    ret+=floodFill(i, j, -1);
                }
            }
        }
        pr.println(ret);
        pr.close();
    }
    static int floodFill(int x, int y, int prev){
        int cnt = 0;
        if(x<0 || x>= nRows || y<0 || y>=nCols){
            return 0;
        }
        int val = grid[x][y];
        boolean isMonotonic = (val==prev+1);
        if(val==9 && isMonotonic){
            return 1;
        }
        if(isMonotonic){
            cnt+=floodFill(x+1, y, val); 
            cnt+=floodFill(x-1, y, val);
            cnt+=floodFill(x, y+1, val);
            cnt+=floodFill(x, y-1, val);
        }
        return cnt;
    }
}
