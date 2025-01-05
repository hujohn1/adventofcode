import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aoc18{
    static char [][]grid;
    static int [][]dist; 
    static boolean [][]processed;
    static int dim;
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("aoc18.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc18.out"));

        dim = 71;
        grid = new char[dim][dim];
        dist = new int [dim][dim];
        processed = new boolean[dim][dim];
        ArrayList<int []> arrList = new ArrayList<>();
        String line = "";
        Pattern p = Pattern.compile("(\\d+),(\\d+)");

        while((line=br.readLine())!=null){
            Matcher m = p.matcher(line);
            if(m.find()){
                arrList.add(new int []{Integer.parseInt(m.group(2)), Integer.parseInt(m.group(1))});
            }
        }
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid.length; j++){
                grid[i][j]='.';
            }
        }
        for(int i=0; i<dim; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        for(int i=0; i<3450; i++){
            grid[arrList.get(i)[0]][arrList.get(i)[1]]='#';
        }
        
        //djikstras
        dist[0][0]=0;
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(0,0));
        int [] dr = new int[]{-1,1,0,0}; //N, S, E, W
        int [] dc = new int[]{0,0,1,-1};
        while(!queue.isEmpty()){
            Point curr = queue.peek(); queue.poll();
            if(processed[curr.x][curr.y]){
                break;
            }
            processed[curr.x][curr.y]=true;
            for(int i=0; i<4; i++){
                int nextX = curr.x+dr[i]; int nextY = curr.y+dc[i];
                Point next = new Point(nextX, nextY);
                if(inBounds(next.x, next.y) && grid[next.x][next.y]!='#' && dist[curr.x][curr.y]+1<dist[next.x][next.y]){
                    dist[next.x][next.y] = dist[curr.x][curr.y]+1;
                    queue.add(next);
                }
            }
        }
        pr.println(dist[dim-1][dim-1] == Integer.MAX_VALUE ? -1 : dist[dim-1][dim-1]);
        pr.close();
    }
    
    static boolean inBounds(int x, int y){
        return (x>=0 && x < dim && y>=0 && y < dim);
    }
    static class Point implements Comparable<Point>{
        int x; int y;
        public Point(int x, int y){
            this.x=x; this.y=y;
        }
        public int compareTo(Point p){
            return Integer.compare(dist[this.x][this.y], dist[p.x][p.y]);
        }
    }
}