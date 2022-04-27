package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17142 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final List<int[]> VIRUS = new ArrayList<>();
    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int INACTIVE_VIRUS = 2;
    private static final int ACTIVE_VIRUS = 3;

    private static int[][] laboratory;
    private static int n;
    private static int m;
    private static int emptyPlace;
    private static int minTime;
    private static boolean canAllSpread;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        laboratory = new int[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                laboratory[i][j] = input;
                if (input == INACTIVE_VIRUS) {
                    VIRUS.add(new int[]{i, j});
                } else if (input == EMPTY) {
                    emptyPlace++;
                }
            }
        }

        minTime = 0;
        if (emptyPlace != 0) {
            minTime = n * n;
            selectVirus(new int[m][2], 0, 0);
            if (!canAllSpread) {
                minTime = -1;
            }
        }
        System.out.println(minTime);
    }

    private static void selectVirus(int[][] selectedVirus, int cnt, int start) {
        if (cnt == m) {
            spread(selectedVirus);
            return;
        }

        for (int i = start; i < VIRUS.size(); i++) {
            selectedVirus[cnt] = VIRUS.get(i);
            selectVirus(selectedVirus, cnt + 1, i + 1);
        }
    }

    private static void spread(int[][] selectedVirus) {
        int[][] laboratoryWithVirus = copy();
        Queue<int[]> queue = new LinkedList<>();
        for (int[] virus : selectedVirus) {
            queue.offer(virus);
        }
        boolean[][] isVisited = new boolean[n][n];
        int time = 0;
        int empty = emptyPlace;
        while (!queue.isEmpty()) {
            time++;
            int size = queue.size();
            while (size-- > 0) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                isVisited[x][y] = true;
                for (int[] delta : DELTAS) {
                    int dx = x + delta[0];
                    int dy = y + delta[1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < n && !isVisited[dx][dy] && laboratoryWithVirus[dx][dy] != WALL) {
                        isVisited[dx][dy] = true;
                        if (laboratoryWithVirus[dx][dy] == EMPTY) {
                            empty--;
                        }
                        laboratoryWithVirus[dx][dy] = ACTIVE_VIRUS;
                        queue.offer(new int[]{dx, dy});
                    }
                    if (empty == 0) {
                        canAllSpread = true;
                        minTime = Math.min(minTime, time);
                        return;
                    }
                }

            }
        }
    }

    private static int[][] copy() {
        int[][] laboratoryWithVirus = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                laboratoryWithVirus[i][j] = laboratory[i][j];
            }
        }
        return laboratoryWithVirus;
    }
}
