import java.io.*;
import java.util.*;

public class aoc12{
    static char [][]grid;
    static boolean [][]visited;
    static int R;
    static int C;
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("aoc12.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc12.out"));

        int ret = 0;
        int N = 140;
        R=N; C=N;
        grid = new char [R][C];
        visited = new boolean [R][C];
        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){
                grid[i][j]=line.charAt(j);
            }
        }
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                if(!visited[r][c]){
                    int [] params = new int[2];
                    floodFill(r,c,params, r, c);
                    ret +=params[0] * params[1];
                }
            }
        }
        pr.println(ret);
        pr.close();
    }
    static void floodFill(int x, int y, int []params, int px, int py){
        if(x<0 || x>=R || y<0 || y>=C || grid[x][y]!=grid[px][py] || visited[x][y]){
            return;
        }
        visited[x][y]=true;
        int adjs = 0;
        if(x+1<R && grid[x+1][y]==grid[x][y]){
            adjs++;
        }
        if(x-1>=0 && grid[x-1][y]==grid[x][y]){
            adjs++;
        }
        if(y+1<R && grid[x][y+1]==grid[x][y]){
            adjs++;
        }
        if(y-1>=0 && grid[x][y-1]==grid[x][y]){
            adjs++;
        }
        params[0]+=1;
        params[1]+=4-adjs;
        if(x+1<R && grid[x+1][y]==grid[x][y]){
            floodFill(x+1, y, params, x, y);
        }
        if(x-1>=0 && grid[x-1][y]==grid[x][y]){
            floodFill(x-1, y, params, x, y);
        }
        if(y+1<R && grid[x][y+1]==grid[x][y]){
            floodFill(x, y+1, params, x, y);
        }
        if(y-1>=0 && grid[x][y-1]==grid[x][y]){
            floodFill(x, y-1, params, x, y);
        }
    }
}
