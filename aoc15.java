import java.io.*;
import java.util.*;

public class aoc15 {
    static char grid [][];
    static int N; 
    static int M;
    static PrintWriter pr;
    static Map<Character, int []> mp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc15.in"));
        pr = new PrintWriter(new FileWriter("aoc15.out"));

        N = 8; 
        M = 8;
        
        grid = new char[N][M];
        String line = "";
        int startX = 0, startY = 0;
        for(int j=0; j<N; j++){
            line = br.readLine();
            for(int i=0; i<M; i++){
                grid[j][i]=line.charAt(i);
                if(grid[j][i]=='@'){
                    startX = j; startY = i;
                }
            }
        }
        line = br.readLine();
        String dirs = br.readLine();

        mp = new HashMap<>();
        mp.put('<', new int []{0,-1});
        mp.put('>', new int []{0,1});
        mp.put('^', new int []{-1,0});
        mp.put('v', new int []{1,0});

        traverse(dirs, startX, startY, 0);
        pr.close();
        br.close();
    }
    static void traverse(String dirs, int x, int y, int n){
        if(n>=dirs.length()) return;
        int dx = mp.get(dirs.charAt(n))[0];
        int dy = mp.get(dirs.charAt(n))[1];

        ArrayList<int []> targets = new ArrayList<>();
        targets.add(new int[]{x,y});
        while(grid[x][y]!='#'){
            x+=dx; y+=dy;
            if(grid[x][y]=='O'){
                targets.add(new int[]{x, y});
            }
            else if(grid[x][y]=='.'){
                break;
            }
        }
        grid[x][y]='.';
        for(int[] j : targets){
            grid[j[0]+dx][j[1]+dy] = 'O';
        }
        x+=dx; 
        y+=dy;
        grid[x][y]='@';
        traverse(dirs, x, y, n+1);
    }
    
    static void printGrid(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                pr.print(grid[i][j]);
            }
            pr.println();
        }
        pr.println();
    }
    static boolean inBound(int x, int y){
        return (x>=0 && x<N && y>=0 && y<M);
    }
}
