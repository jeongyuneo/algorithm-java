package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17141 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final List<int[]> VIRUSES = new ArrayList<>();
    private static final Queue<int[]> SPREAD_VIRUSES = new ArrayDeque<>();
    private static final int WALL = 1;
    private static final int VIRUS = 2;

    private static int[][] laboratory;
    private static int[] selected;
    private static boolean[][] isVisited;
    private static int n;
    private static int m;
    private static int spreadableSpace;
    private static int minTime;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        laboratory = new int[n][n];
        isVisited = new boolean[n][n];
        selected = new int[m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                laboratory[i][j] = input;
                if (input == VIRUS) {
                    VIRUSES.add(new int[]{i, j});
                }
                if (input != WALL) {
                    spreadableSpace++;
                }
            }
        }
        minTime = n * n;
        selectVirus(0, 0);
        if (minTime == n * n) {
            System.out.println(-1);
        } else {
            System.out.println(minTime);
        }
    }

    private static void selectVirus(int cnt, int start) {
        if (cnt == m) {
            for (int i = 0; i < n; i++) {
                Arrays.fill(isVisited[i], false);
            }
            for (int selectedVirus : selected) {
                int[] virus = VIRUSES.get(selectedVirus);
                SPREAD_VIRUSES.offer(virus);
                isVisited[virus[0]][virus[1]] = true;
            }
            spreadVirus();
            return;
        }

        for (int i = start, size = VIRUSES.size(); i < size; i++) {
            selected[cnt] = i;
            selectVirus(cnt + 1, i + 1);
        }
    }

    private static void spreadVirus() {
        int empty = spreadableSpace - m;
        int time = -1;
        while (!SPREAD_VIRUSES.isEmpty()) {
            time++;
            int size = SPREAD_VIRUSES.size();
            while (size-- > 0) {
                int[] current = SPREAD_VIRUSES.poll();
                int x = current[0];
                int y = current[1];
                for (int[] delta : DELTAS) {
                    int dx = x + delta[0];
                    int dy = y + delta[1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < n && !isVisited[dx][dy] && laboratory[dx][dy] != WALL) {
                        empty--;
                        isVisited[dx][dy] = true;
                        SPREAD_VIRUSES.offer(new int[]{dx, dy});
                    }
                }
            }
        }
        if (empty == 0) {
            minTime = Math.min(minTime, time);
        }
    }
}
