package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1249 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int testCase = Integer.parseInt(bufferedReader.readLine());
        for (int t = 1; t <= testCase; t++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            map = new int[n][n];
            int[][] minTimeInfos = new int[n][n];
            for (int i = 0; i < n; i++) {
                String input = bufferedReader.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = input.charAt(j) - 48;
                    minTimeInfos[i][j] = Integer.MAX_VALUE;
                }
            }

            minTimeInfos[0][0] = 0;
            findMinTime(minTimeInfos, n);

            stringBuilder.append("#")
                    .append(t)
                    .append(" ")
                    .append(minTimeInfos[n - 1][n - 1])
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void findMinTime(int[][] minTimeInfos, int n) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < n && minTimeInfos[dx][dy] > minTimeInfos[x][y] + map[dx][dy]) {
                    minTimeInfos[dx][dy] = minTimeInfos[x][y] + map[dx][dy];
                    queue.offer(new int[]{dx, dy});
                }
            }
        }
    }
}
