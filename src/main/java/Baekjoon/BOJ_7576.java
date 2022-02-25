package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_7576 {

    private static final int[][] DELTAS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};   // 좌우상하
    private static final int UNRIPE_TOMATO = 0;
    private static final int RIPE_TOMATO = 1;

    private static int m;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        m = Integer.parseInt(stringTokenizer.nextToken());
        n = Integer.parseInt(stringTokenizer.nextToken());
        int[][] box = new int[n][m];
        Queue<int[]> tomatoes = new LinkedList<>();
        int unRipeCnt = 0;
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                if (input == RIPE_TOMATO) {
                    tomatoes.offer(new int[]{i, j});
                } else if (input == UNRIPE_TOMATO) {
                    unRipeCnt++;
                }
                box[i][j] = input;
            }
        }
        if (unRipeCnt == 0) {
            System.out.println(0);
        } else {
            ripen(box, tomatoes, unRipeCnt);
        }
    }

    private static void ripen(int[][] box, Queue<int[]> tomatoes, int unRipeCnt) {
        int day = 1;
        while (!tomatoes.isEmpty()) {
            int size = tomatoes.size();
            while (size-- > 0) {
                int[] current = tomatoes.poll();
                for (int[] delta : DELTAS) {
                    int dx = current[0] + delta[0];
                    int dy = current[1] + delta[1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < m && box[dx][dy] == UNRIPE_TOMATO) {
                        box[dx][dy] = RIPE_TOMATO;
                        unRipeCnt--;
                        tomatoes.offer(new int[]{dx, dy});
                    }
                }
            }
            if (unRipeCnt == 0) {
                System.out.println(day);
                return;
            }
            day++;
        }
        System.out.println(-1);
    }
}
