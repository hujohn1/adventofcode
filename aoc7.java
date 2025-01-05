import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class aoc7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("aoc7.in"));
        PrintWriter pr = new PrintWriter(new FileWriter("aoc7.out"));

        BigInteger ret = BigInteger.ZERO;
        int N = 850; // Adjust as needed
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String tk = st.nextToken();
            BigInteger target = new BigInteger(tk.substring(0, tk.length() - 1));
            ArrayList<BigInteger> values = new ArrayList<>();
            while (st.hasMoreTokens()) {
                tk = st.nextToken();
                values.add(new BigInteger(tk));
            }

            // 2^(N-1) possible permutations where N is the # of numbers
            List<String> perms = new ArrayList<>();
            generatePerms(values.size(), "", perms);
            for (String p : perms) {
                BigInteger curr = values.get(0);
                boolean valid = true;
                for (int s = 0; s < values.size() - 1; s++) {
                    if (p.charAt(s) == '+') {
                        curr = curr.add(values.get(s + 1));
                    } else if (p.charAt(s) == '*') {
                        curr = curr.multiply(values.get(s + 1));
                    }
                    else if (p.charAt(s) == '|') {
                        BigInteger base = new BigInteger("10");
                        BigInteger rkt = base.pow(values.get(s+1).toString().length());
                        curr = curr.multiply(rkt);
                        curr = curr.add(values.get(s+1));
                    }
                    if (curr.compareTo(target) > 0) {
                        valid = false;
                        break;
                    }
                }
                if (valid && curr.equals(target)) {
                    ret = ret.add(target);

                    // Debugging
                    pr.println(target);
                    break;
                }
            }
        }
        pr.println(ret);
        pr.close();
        br.close();
    }

    static void generatePerms(int N, String str, List<String> permutations) {
        if (str.length() == N - 1) {
            permutations.add(str);
            return;
        }
        generatePerms(N, str + "+", permutations);
        generatePerms(N, str + "*", permutations);
        generatePerms(N, str + "|", permutations);
    }
}
