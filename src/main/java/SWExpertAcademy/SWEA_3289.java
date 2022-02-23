package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289 {

    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        for (int t = 1; t <= testCase; t++) {
            STRING_BUILDER.append("#")
                    .append(t)
                    .append(" ");
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());

            makeSet(n);
            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int operation = Integer.parseInt(stringTokenizer.nextToken());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());

                if (operation == 0) {
                    union(a, b);
                } else {
                    int result = 0;
                    if (findSet(a) == findSet(b)) {
                        result = 1;
                    }
                    STRING_BUILDER.append(result);
                }
            }
            STRING_BUILDER.append("\n");
        }
        System.out.println(STRING_BUILDER);
    }

    private static void makeSet(int n) {
        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = findSet(parents[a]);
    }

    private static void union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) {
            return;
        }
        parents[aRoot] = parents[bRoot];
    }
}
