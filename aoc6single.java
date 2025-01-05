import java.io.*;
import java.util.*;

public class aoc6single{
    static char [][]grid;
    static char [][]gridc;
    static int N;
    static boolean [][] visited;
    static Set<String> visitedStates;
    
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("aoc6.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc6.out"));

        N = 10;
        visited = new boolean[N][N];
        visitedStates = new HashSet<>();
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
        //part 2: 
        //brute search O(16,900) positions and check if the Set contains a coordinate which has already been seen before
        int ret = 0;
        
        gridc = new char[N][N];
        for(int k=0; k<N; k++){
            gridc[k] = Arrays.copyOf(grid[k], grid[k].length);
        }
        gridc[6][3]='O';
        Boolean isACycle = recursiveSearch(startx, starty, gridc, 1, false, false);
        visitedStates.clear();
         
        for(int k=0; k<N; k++){
            for(int l=0; l<N; l++){
                char s = visited[k][l]? 'T': gridc[k][l];
                pr.print(s);
            }
            pr.println();
        }
        pr.println();
        if(isACycle){
            ret++;
        }
        pr.println(ret);
        br.close();
        pr.close();
    }
    
    static boolean recursiveSearch(int x, int y, char [][]grid, int specifier, boolean done, boolean isCycle){
        //System.out.println(x+","+y+","+specifier);
        String stateKey = "";
    
        if(done || (x<0 || x>=N) || (y<0 || y>=N)){
            return false;
        }
        if(specifier==1){
            while((x>=0 && x< N) && grid[x][y]!='#' && grid[x][y]!='O'){
                stateKey = x + "," + y + "," + specifier;
                if(visitedStates.contains(stateKey)){
                    System.out.println(stateKey);
                    //System.out.println("entered here"+isCycle);
                    return true;
                }
                visitedStates.add(stateKey);
                visited[x][y]=true;
                x--;
            }
            if((x<0 || x>=N) || (y<0 || y>=N)){
                return false;
            }
            specifier=2;
            isCycle = recursiveSearch(x+1, y, grid, specifier, done, isCycle);
        }
        else if(specifier==2){
            while((y>=0 && y< N) && grid[x][y]!='#' && grid[x][y]!='O'){
                stateKey = x + "," + y + "," + specifier;
                if(visitedStates.contains(stateKey)){
                    System.out.println(stateKey);
                    return true;
                }
                visitedStates.add(stateKey);
                visited[x][y]=true;
                y++;
            }
            if((x<0 || x>=N) || (y<0 || y>=N)){
                return false;
            }
            specifier=3;
            isCycle = recursiveSearch(x, y-1, grid, specifier, done, isCycle);
        }
        //go down
        else if(specifier==3){
            while((x>=0 && x< N) && grid[x][y]!='#'&& grid[x][y]!='O'){
                stateKey = x + "," + y + "," + specifier;
                if(visitedStates.contains(stateKey)){
                    System.out.println(stateKey);
                    return true;
                }
                visitedStates.add(stateKey);
                visited[x][y]=true;
                x++;
            }
            if((x<0 || x>=N) || (y<0 || y>=N)){
                return false;
            } 
            specifier=4;
            isCycle = recursiveSearch(x-1, y,grid,  specifier, done, isCycle);
        }
        //go left
        else if(specifier==4){
            while((y>=0 && y< N) && grid[x][y]!='#'&& grid[x][y]!='O'){
                stateKey = x + "," + y + "," + specifier;
                if(visitedStates.contains(stateKey)){
                    System.out.println(stateKey);
                    return true;
                }
                visitedStates.add(stateKey);
                visited[x][y]=true;
                y--;
            }
            if((x<0 || x>=N) || (y<0 || y>=N)){
                return false;
            }
            specifier=1;
            isCycle = recursiveSearch(x, y+1, grid, specifier, done, isCycle);
        }
        return isCycle;
    }
}