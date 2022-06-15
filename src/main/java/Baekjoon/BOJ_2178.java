package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String inputLine = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = inputLine.charAt(j)-48;
            }
        }
        System.out.println(getMinimumMove(n, m, map));
    }

    private static int getMinimumMove(int n, int m, int[][] map) {
        Queue<int[]> way = new LinkedList<>();
        boolean[][] isVisited = new boolean[n][m];
        way.add(new int[]{0, 0});
        isVisited[0][0] = true;
        int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int move = 1;
        while (!way.isEmpty()) {
            int size = way.size();
            move++;
            while (size-- > 0) {
                int[] current = way.poll();
                for (int[] delta : deltas) {
                    int dx = current[0] + delta[0];
                    int dy = current[1] + delta[1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < m && !isVisited[dx][dy] && map[dx][dy] == 1) {
                        if (dx == n - 1 && dy == m - 1) {
                            return move;
                        }
                        isVisited[dx][dy] = true;
                        way.add(new int[]{dx, dy});
                    }
                }
            }
        }
        return move;
    }
}
