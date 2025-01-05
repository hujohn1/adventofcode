import java.io.*;
import java.util.*;

public class aoc16 {
    static char grid[][];
    static int dim;
    static int costs[][];
    static boolean visited[][];
    static int sx, sy;
    static int ex, ey;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc16.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc16.out"));
        dim = 141;
        grid = new char[dim][dim];
        costs = new int[dim][dim];
        visited = new boolean[dim][dim];
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
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }
        costs[sx][sy] = 0;

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
                if (inBound(nx, ny) && grid[nx][ny] != '#') {
                    int pen = (curr.dir != i) ? 1000 * Math.min(Math.abs(i-curr.dir), 4-Math.abs(i-curr.dir)) : 0;
                    int newCost = costs[curr.x][curr.y] + 1 + pen;
                    if (newCost < costs[nx][ny]) {
                        costs[nx][ny] = newCost;
                        pqueue.add(new Point(nx, ny, i));
                    }
                }
            }
        }

        // Output the result
        if (costs[ex][ey] == Integer.MAX_VALUE) {
            pr.println("No path found");
        } else {
            pr.println(costs[ex][ey]);
        }
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

        public int hashCode() {
            return Objects.hash(x, y, dir);
        }
    
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            // Check if obj is a Point and compare fields
            if (obj == null || getClass() != obj.getClass()) return false;
            Point other = (Point) obj;
            return x == other.x && y == other.y && dir == other.dir;
        }

        public int compareTo(Point p) {
            return Integer.compare(costs[this.x][this.y], costs[p.x][p.y]);
        }
    }
}
