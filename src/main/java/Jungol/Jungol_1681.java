package Jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1681 {

    private static int n;
    private static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        int[][] space = new int[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                space[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        findRoute(space, new boolean[n], 0, 0, 0);
        System.out.println(minCost);
    }

    private static void findRoute(int[][] space, boolean[] visited, int cnt, int from, int currentCost) {
        if (cnt == n-1) {
            if (space[from][0] != 0) {
                minCost = Math.min(minCost, currentCost+space[from][0]);
            }
            return;
        }

        if (currentCost > minCost) {
            return;
        }

        for (int to = 1; to < n; to++) {
            if (!visited[to] && space[from][to] != 0) {
                visited[to] = true;
                findRoute(space, visited, cnt + 1, to, currentCost + space[from][to]);
                visited[to] = false;
            }
        }
    }
}
