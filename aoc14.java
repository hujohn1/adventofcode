import java.io.*;
import java.util.*;
import java.util.regex.*;

public class aoc14 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc14.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc14.out"));

        String line = "";
        int N = 103; 
        int M = 101; // Set grid dimensions
        int[][] grid = new int[N][M]; // Initialize grid with zeros

        int lineNum = 1; // Line number tracker
        while ((line = br.readLine()) != null) {
            Pattern p = Pattern.compile("p=(\\d+),(\\d+)\\s+v=(-?\\d+),(-?\\d+)");
            Matcher m = p.matcher(line);

            ArrayList<List<Integer>> arrList = new ArrayList<>();
            while (m.find()) {
                List<Integer> lst = new ArrayList<>();
                int px = Integer.parseInt(m.group(2));
                int py = Integer.parseInt(m.group(1));
                int vx = Integer.parseInt(m.group(4));
                int vy = Integer.parseInt(m.group(3));
                lst.add(px);
                lst.add(py);
                lst.add(vx);
                lst.add(vy);
                arrList.add(lst);
            }

            for (List<Integer> l : arrList) {
                int px = l.get(0);
                int py = l.get(1);
                int vx = l.get(2);
                int vy = l.get(3);

                for (int i = 0; i < 100; i++) {
                    px += vx;
                    py += vy;
                    // Handle boundary conditions
                    if (px < 0) {
                        px = N + px;
                    } else if (px >= N) {
                        px = px % N;
                    }
                    if (py < 0) {
                        py = M + py;
                    } else if (py >= M) {
                        py = py % M;
                    }

                    // Print the line number and initial position if the particle marks the 1st row
                    // First row
                    
                }
                System.out.println("Line " + lineNum + ": Particle with velocity (" + vx + ", " + vy + ") started at (" + l.get(0) + ", " + l.get(1) + ") and moved to position (x=" + py + ", y=" + px + ")");
                    
                grid[px][py] += 1; // Mark the position in the grid
            }
            lineNum++; // Increment the line number for each line processed
        }

        // Output the grid to the output file without spaces between the values
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                pr.print(grid[i][j]);
            }
            pr.println();
        }

        // Optionally, check quadrants
        int midX = N / 2;
        int midY = M / 2;
        pr.println("mid" + midX + "," + midY);
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        for (int i = 0; i < midX; i++) {
            for (int j = 0; j < midY; j++) {
                q2 += grid[i][j];
            }
        }
        for (int i = midX + 1; i < N; i++) {
            for (int j = 0; j < midY; j++) {
                q3 += grid[i][j];
            }
        }
        for (int i = 0; i < midX; i++) {
            for (int j = midY + 1; j < M; j++) {
                q1 += grid[i][j];
            }
        }
        for (int i = midX + 1; i < N; i++) {
            for (int j = midY + 1; j < M; j++) {
                q4 += grid[i][j];
            }
        }

        pr.println(q1 + "," + q2 + "," + q3 + "," + q4);
        pr.println(q1 * q2 * q3 * q4);

        pr.close();
        br.close();
    }
}
