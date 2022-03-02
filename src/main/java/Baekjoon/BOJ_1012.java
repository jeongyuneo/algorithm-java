package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012 {

    private static final int[][] DELTAS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int CROP = 1;
    private static final int VISIT = 2;

    private static int n;
    private static int m;
    private static int worm;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = null;
        StringBuilder stringBuilder = new StringBuilder();
        int testCase = Integer.parseInt(bufferedReader.readLine());
        for (int t = 0; t < testCase; t++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            m = Integer.parseInt(stringTokenizer.nextToken());
            n = Integer.parseInt(stringTokenizer.nextToken());
            int cropNum = Integer.parseInt(stringTokenizer.nextToken());
            int[][] field = new int[n][m];
            for (int i = 0; i < cropNum; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int y = Integer.parseInt(stringTokenizer.nextToken());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                field[x][y] = 1;
            }
            worm = 0;
            countWorms(field);
            stringBuilder.append(worm)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void countWorms(int[][] field) {
        Queue<int[]> crops = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] == CROP) {
                    worm++;
                    crops.offer(new int[]{i, j});
                    while (!crops.isEmpty()) {
                        int[] crop = crops.poll();
                        int x = crop[0];
                        int y = crop[1];
                        field[x][y] = VISIT;
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < n && dy >= 0 && dy < m && field[dx][dy] == CROP) {
                                field[dx][dy] = VISIT;
                                crops.offer(new int[]{dx, dy});
                            }
                        }
                    }
                }
            }
        }
    }
}
