import java.io.*;
import java.util.*;

public class aoc8 {
    static boolean [][] isANode;
    static int N; 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc8.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc8.out"));

        N = 50;
        int ret = 0;
        char [][] grid = new char[N][N];
        isANode = new boolean[N][N];
        Map<Character, List<Point>> mp = new HashMap<>();
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<N; j++){
                grid[i][j]=line.charAt(j);
                if(grid[i][j]!='.' && (!mp.containsKey(grid[i][j]))){
                    mp.put(grid[i][j], new ArrayList<>());
                    mp.get(grid[i][j]).add(new Point(i,j));
                }
                else if (grid[i][j]!='.'){
                    mp.get(grid[i][j]).add(new Point(i,j));
                }
            }
            //for each frequency f, loop through all positions n occurence, and get n(n-1)/2 pairs check the two antinodes per pair
        }
        for(Character k:mp.keySet()){
            for(int i=0; i<mp.get(k).size(); i++){
                for(int j=i+1; j<mp.get(k).size(); j++){
                    computeANodes(mp.get(k).get(i), mp.get(k).get(j));
                }
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(isANode[i][j]){
                    ret++;
                }
            }
        }
        pr.println(ret);
        pr.close();
        br.close();
    }
    static void computeANodes(Point p, Point q){
        int px=p.x, py=p.y;
        int qx=q.x, qy=q.y;
        //ensure that p is always to the right or equal of q
        if(qx>px){
            int tmp = px;
            px = qx; qx = tmp;
            tmp = py;
            py = qy; qy = tmp;
        }
        int dx = px-qx; int dy = py-qy;
        int x1, y1, x2, y2;
        x1 = px + dx; y1 = py + dy;
        x2 = qx - dx; y2 = qy - dy;

        //compute first possible ANode with boundary checking
        if((x1>=0 && x1<N) && (y1>=0 && y1<N)){
            isANode[x1][y1]=true;
        }
        if((x2 >=0 && x2<N) && (y2>=0 && y2<N)){
            isANode[x2][y2]=true;
        }
    }

    static class Point{
        int x; int y;
        public Point(int x, int y){
            this.x=x; this.y=y;
        }
    }
}
