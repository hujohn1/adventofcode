import java.io.*;
import java.util.*;

public class aoc16_2 {
    static char grid[][];
    static int dim;
    static int costs[][][]; //costs from start
    static int costs2[][][]; //costs from end
    static int[][] preds;
    static boolean visited[][];
    static boolean visited2[][];
    static int sx, sy;
    static int ex, ey;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc16.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc16_2.out"));
        dim = 141;
        grid = new char[dim][dim];
        costs = new int[dim][dim][4];
        costs2 = new int[dim][dim][4];
        visited = new boolean[dim][dim];
        visited2 = new boolean[dim][dim];
        preds = new int[dim][dim];
        String line = "";
        sx = 0; sy = 0; ex = 0; ey = 0;

        for (int i = 0; i < dim; i++) {
            line = br.readLine();
            for (int j = 0; j < dim; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'S') { // Starting point
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 'E') { // Ending point
                    ex = i;
                    ey = j;
                }
            }
        }

        // Initialize costs
        for (int i = 0; i < dim; i++) {
            for(int j=0; j< dim; j++){
                for(int k=0; k<4; k++){
                    costs[i][j][k] = Integer.MAX_VALUE;
                    costs2[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        costs[sx][sy][1] = 0;

        int[] dr = new int[]{-1, 0, 1, 0}; // N, E, S, W
        int[] dc = new int[]{0, 1, 0, -1};

        PriorityQueue<Point> pqueue = new PriorityQueue<>();
        pqueue.add(new Point(sx, sy, 1));

        while (!pqueue.isEmpty()) {
            Point curr = pqueue.poll();
            if (visited[curr.x][curr.y]) {
                continue;
            }
            visited[curr.x][curr.y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dr[i];
                int ny = curr.y + dc[i];
                Point next = new Point(nx, ny, i);
                if (inBound(nx, ny) && grid[nx][ny] != '#') {
                    int pen = (curr.dir != i) ? 1000 * Math.min(Math.abs(i-curr.dir), 4-Math.abs(i-curr.dir)) : 0;
                    int newCost = costs[curr.x][curr.y][curr.dir] + 1 + pen;
                    if (newCost < costs[nx][ny][curr.dir]) {
                        costs[nx][ny][i] = newCost;
                        preds[nx][ny]=grid[curr.x][curr.y];
                        pqueue.add(next);
                    }
                    
                }
            }
        }

        //backtrack to get grid
        char [][] plot = new char [dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                plot[i][j] = grid[i][j];
            }
        }
        int minDir = -1;
        int minCost = Integer.MAX_VALUE;
        for(int dir = 0; dir<4; dir++){
            if(costs[ex][ey][dir]<minCost){
                minCost = costs[ex][ey][dir];
                minDir = dir;
            }
        }
        Point curr =  new Point(ex, ey, minDir);
        while (curr != null) {
            if (curr.x == sx && curr.y == sy) break;
            if (curr.dir == 0) plot[curr.x][curr.y] = '^';
            else if (curr.dir == 1) plot[curr.x][curr.y] = '>';
            else if (curr.dir == 2) plot[curr.x][curr.y] = 'v';
            else if (curr.dir == 3) plot[curr.x][curr.y] = '<';
        
            if (preds[curr.x][curr.y].isEmpty()) break;
            curr = preds[curr.x][curr.y].get(0); // Assume single path (can modify for multiple)
        }
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                pr.print(plot[i][j]);
            }
            pr.println();
        }

         

        //run Djikstra in reverse
        pr.println("Lowest cost:" + minCost);
        int cnt = 0;
        //backtracking
        /*for(int i=0; i<dim; i++){
            for(int j=0; j<dim; j++){
                if(costs[i][j]+costs2[i][j]==costs[ex][ey]){
                    cnt++;
                    pr.print(costs[i][j]+costs2[i][j]==costs[ex][ey]? '#': '.');
                }
            }
            pr.println();
        }*/
        pr.println(cnt);
        pr.close();
        br.close();
    }

    static boolean inBound(int x, int y) {
        return (x >= 0 && x < dim && y >= 0 && y < dim);
    }

    static class Point implements Comparable<Point> {
        int x, y, dir;

        public Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public int compareTo(Point p) {
            return Integer.compare(costs[this.x][this.y][this.dir], costs[p.x][p.y][p.dir]);
        }
    }
}
