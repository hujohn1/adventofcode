import java.io.*;
import java.util.*;

public class aoc20 {
    static char grid[][];
    static int N;
    static int M;
    static int endX;
    static int endY;
    static boolean visited[][];
    static ArrayList<Pair> arrList;
    static Map<Pair, Integer> mp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc20.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc20.out"));

        int ret = 0;
        N = 141;
        M = 141;
        arrList = new ArrayList<>();
        grid = new char[N][M];
        visited = new boolean[N][M];
        int startX = 0, startY = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        floodFill(startX, startY);
        mp = new HashMap<>();
        for (int i = 0; i < arrList.size(); i++) {
            mp.put(arrList.get(i), i);
        }
        Map<Integer, Integer> mpcounter = new HashMap<>();

        for (int i = 0; i < arrList.size(); i++) {
            Pair p = arrList.get(i);
            //pr.println(i+":("+p.x+","+p.y+")");
            for (int j = -20; j <= 20; j++) {
                for (int k = -20; k <= 20; k++) {
                    int newX = p.x + j; int newY = p.y + k;
                    int mhdist = Math.abs(j) + Math.abs(k);
                    if (mhdist <= 20) {
                        if (inBound(newX, newY) && (grid[newX][newY] == '.' || grid[newX][newY] == 'E')) {
                            int timeSaved = mp.get(new Pair(newX, newY)) - mp.get(p)-mhdist;
                            //mpcounter.put(timeSaved, mpcounter.getOrDefault(timeSaved, 0) + 1);
                            if (timeSaved >= 100) {
                                ret++;
                            }
                        }
                    }
                }
            }
        }
        /*for(Integer r: mpcounter.keySet()){
            pr.println(r+","+mpcounter.get(r));
        }*/
        pr.println(ret);
        pr.close();
    }

    static boolean inBound(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

    // Iterative floodFill method using a stack
    static void floodFill(int startX, int startY) {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(startX, startY));

        while (!stack.isEmpty()) {
            Pair current = stack.pop();
            int x = current.x;
            int y = current.y;

            if (!inBound(x, y) || visited[x][y] || (grid[x][y] != '.' && grid[x][y] != 'S' && grid[x][y] != 'E')) {
                continue;
            }

            visited[x][y] = true;
            arrList.add(current);

            if (grid[x][y] == 'E') {
                endX = x;
                endY = y;
            }

            // Add neighbors to the stack
            stack.push(new Pair(x + 1, y));
            stack.push(new Pair(x - 1, y));
            stack.push(new Pair(x, y + 1));
            stack.push(new Pair(x, y - 1));
        }
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair other = (Pair) obj;
            return x == other.x && y == other.y;
        }
    }
}