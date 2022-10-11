package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17142 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final List<int[]> VIRUSES = new ArrayList<>();
    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int VIRUS = 2;

    private static int[][] laboratory;
    private static int[][] activeVirus;
    private static int n;
    private static int m;
    private static int emptySpace;
    private static int minTime;

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
                if (input == EMPTY) {
                    ++emptySpace;
                } else if (input == VIRUS) {
                    VIRUSES.add(new int[]{i, j});
                }
            }
        }

        minTime = Integer.MAX_VALUE;
        activeVirus = new int[m][2];
        activateVirus(0, 0);
        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
    }

    private static void activateVirus(int cnt, int start) {
        if (cnt == m) {
            spreadVirus();
            return;
        }

        for (int i = start; i < VIRUSES.size(); i++) {
            activeVirus[cnt] = VIRUSES.get(i);
            activateVirus(cnt + 1, i + 1);
        }
    }

    private static void spreadVirus() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[n][n];
        for (int[] virus : activeVirus) {
            queue.offer(virus);
            isVisited[virus[0]][virus[1]] = true;
        }
        int empty = emptySpace;
        int time = 0;
        while (!queue.isEmpty()) {
            if (empty == 0) {
                break;
            }

            int size = queue.size();
            ++time;
            while (size-- > 0) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                for (int[] delta : DELTAS) {
                    int dx = x + delta[0];
                    int dy = y + delta[1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < n && laboratory[dx][dy] != WALL && !isVisited[dx][dy]) {
                        queue.offer(new int[]{dx, dy});
                        isVisited[dx][dy] = true;
                        if (laboratory[dx][dy] == EMPTY) {
                            --empty;
                        }
                    }
                }
            }
        }
        if (empty == 0) {
            minTime = Math.min(minTime, time);
        }
    }
}
