package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1717 {

    private static final int ADD = 0;

    private static int[] roots;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        init(n);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int operation = Integer.parseInt(stringTokenizer.nextToken());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            if (operation == ADD) {
                joinSet(a, b);
            } else {
                if (isSameSet(a, b)) {
                    answer.append("YES");
                } else {
                    answer.append("NO");
                }
                answer.append("\n");
            }
        }
        System.out.println(answer);
    }

    private static void init(int n) {
        roots = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            roots[i] = i;
        }
    }

    private static void joinSet(int a, int b) {
        roots[findSet(a)] = findSet(b);
    }

    private static boolean isSameSet(int a, int b) {
        return findSet(a) == findSet(b);
    }

    private static int findSet(int element) {
        if (roots[element] == element) {
            return element;
        }
        return roots[element] = findSet(roots[element]);
    }
}
