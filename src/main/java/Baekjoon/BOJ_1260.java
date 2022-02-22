package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1260 {

    private static final StringBuilder STRING_BUILDER = new StringBuilder();

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int v = Integer.parseInt(stringTokenizer.nextToken());

        int[][] adjMatrix = new int[n + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            adjMatrix[from][to] = adjMatrix[to][from] = 1;
        }

        dfs(adjMatrix, new boolean[n+1], v);
        STRING_BUILDER.append("\n");
        bfs(adjMatrix, v);
        System.out.println(STRING_BUILDER);
    }

    private static void dfs(int[][] adjMatrix, boolean[] visited, int current) {
        visited[current] = true;
        STRING_BUILDER.append(current)
                .append(" ");
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && adjMatrix[current][i] != 0) {
                dfs(adjMatrix, visited, i);
            }
        }
    }

    private static void bfs(int[][] adjMatrix, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(start);
        visited[start] = true;
        STRING_BUILDER.append(start)
                .append(" ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 1; i <= n; i++) {
                if (!visited[i] && adjMatrix[current][i] != 0) {
                    STRING_BUILDER.append(i)
                            .append(" ");
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
        STRING_BUILDER.append("\n");
    }
}
