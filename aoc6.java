import java.io.*;
import java.util.*;

public class aoc6{
    static char [][]grid;
    static int N;
    static boolean [][] visited;
    
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("aoc6.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc6.out"));

        N = 130;
        visited = new boolean[N][N];
        grid = new char[N][N];
        int startx=0; int starty=0;
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<N; j++){
                grid[i][j]=line.charAt(j);

                if(grid[i][j]=='^'){
                    startx=i; starty=j;
                }
            }
        }
        recursiveSearch(startx, starty, 1, false);
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(visited[i][j]){
                    cnt++;
                }
            }
        }
        pr.println(cnt);
        br.close();
        pr.close();
    }
    
    static void recursiveSearch(int x, int y, int specifier, boolean done){
        System.out.println("recursiveSearch"+x+","+y+","+specifier);
        if(done || (x<0 || x>=N) || (y<0 || y>=N)){
            return;
        }
        if(specifier==1){
            while((x>=0 && x< N) && grid[x][y]!='#'){
                visited[x][y]=true;
                x--;
            }
            if((x<0 || x>=N) || (y<0 || y>=N)){
                done = true;
            }
            specifier=2;
            recursiveSearch(x+1, y, specifier, done);
        }
        else if(specifier==2){
            while((y>=0 && y< N) && grid[x][y]!='#'){
                visited[x][y]=true;
                y++;
            }
            if((x<0 || x>=N) || (y<0 || y>=N)){
                done = true;
            }
            specifier=3;
            recursiveSearch(x, y-1, specifier, done);
        }
        //go down
        else if(specifier==3){
            while((x>=0 && x< N) && grid[x][y]!='#'){
                visited[x][y]=true;
                x++;
            }
            if((x<0 || x>=N) || (y<0 || y>=N)){
                done = true;
            } 
            specifier=4;
            recursiveSearch(x-1, y, specifier, done);
        }
        //go left
        else if(specifier==4){
            while((y>=0 && y< N) && grid[x][y]!='#'){
                visited[x][y]=true;
                y--;
            }
            if((x<0 || x>=N) || (y<0 || y>=N)){
                done = true;
            }
            specifier=1;
            recursiveSearch(x, y+1, specifier, done);
        }
    }
}