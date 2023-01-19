package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final Queue<int[]> VISITED_ICEBERGS = new ArrayDeque<>();
    private static final Queue<int[]> ICEBERGS = new ArrayDeque<>();
    private static final int SEA = 0;

    private static int[][] map;
    private static boolean[][] isVisited;
    private static int n;
    private static int m;
    private static boolean isAllMelt;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[n][m];
        isVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                map[i][j] = input;
                if (input != SEA) {
                    ICEBERGS.offer(new int[]{i, j});
                }
            }
        }

        int time = 0;
        while (isNotSeperated()) {
            melt();
            time++;
        }
        if (isAllMelt) {
            System.out.println(0);
        } else {
            System.out.println(time);
        }
    }

    private static boolean isNotSeperated() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(isVisited[i], false);
        }
        VISITED_ICEBERGS.clear();
        if (ICEBERGS.isEmpty()) {
            isAllMelt = true;
            return false;
        }
        VISITED_ICEBERGS.offer(ICEBERGS.peek());
        int icebergSize = ICEBERGS.size();
        int icebergs = 1;
        while (!VISITED_ICEBERGS.isEmpty()) {
            int[] current = VISITED_ICEBERGS.poll();
            int x = current[0];
            int y = current[1];
            isVisited[x][y] = true;
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < m && map[dx][dy] != SEA && !isVisited[dx][dy]) {
                    isVisited[dx][dy] = true;
                    VISITED_ICEBERGS.offer(new int[]{dx, dy});
                    icebergs++;
                }
            }
        }
        return icebergs == icebergSize;
    }

    private static void melt() {
        int[][] copiedMap = copyMap();
        int size = ICEBERGS.size();
        while (size-- > 0) {
            int[] current = ICEBERGS.poll();
            int x = current[0];
            int y = current[1];
            int seaCount = 0;
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < m && map[dx][dy] == SEA) {
                    seaCount++;
                }
            }
            copiedMap[x][y] = Math.max(copiedMap[x][y] - seaCount, SEA);
            if (copiedMap[x][y] != SEA) {
                ICEBERGS.offer(current);
            }
        }
        map = copiedMap;
    }

    private static int[][] copyMap() {
        int[][] copiedMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
        return copiedMap;
    }
}
