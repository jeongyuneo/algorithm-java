package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17822 {

    private static final Queue<int[]> ADJACENT_NUMBERS = new ArrayDeque<>();
    private static final Queue<int[]> MOVES = new ArrayDeque<>();
    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int CLOCKWISE = 0;
    private static final int DELETE = 0;

    private static int[][] circles;
    private static int n;
    private static int m;
    private static int count;
    private static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        int t = Integer.parseInt(stringTokenizer.nextToken());
        circles = new int[n + 1][m];
        for (int i = 1; i <= n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                int number = Integer.parseInt(stringTokenizer.nextToken());
                circles[i][j] = number;
                sum += number;
            }
        }
        count = n * m;
        while (t-- > 0) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            rotate(x, d, k);
            deleteAdjacentNumbers();
        }
        System.out.println(sum);
    }

    private static void rotate(int x, int d, int k) {
        int rotatingNumber = x;
        while (rotatingNumber <= n) {
            int[] rotatingCircle = circles[rotatingNumber].clone();
            for (int i = 0; i < m; i++) {
                if (d == CLOCKWISE) {
                    circles[rotatingNumber][(i + k) % m] = rotatingCircle[i];
                } else {
                    circles[rotatingNumber][(i - k + m) % m] = rotatingCircle[i];
                }
            }
            rotatingNumber += x;
        }
    }

    private static void deleteAdjacentNumbers() {
        boolean canNotDelete = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                if (circles[i][j] == DELETE) {
                    continue;
                }
                ADJACENT_NUMBERS.offer(new int[]{i, j});
                MOVES.offer(new int[]{i, j});
                boolean[][] isVisited = new boolean[n + 1][m];
                isVisited[i][j] = true;
                while (!MOVES.isEmpty()) {
                    int[] current = MOVES.poll();
                    int x = current[0];
                    int y = current[1];
                    for (int[] delta : DELTAS) {
                        int dx = x + delta[0];
                        int dy = (y + delta[1] + m) % m;
                        if (dx >= 1 && dx <= n && !isVisited[dx][dy] && circles[x][y] == circles[dx][dy]) {
                            isVisited[dx][dy] = true;
                            ADJACENT_NUMBERS.offer(new int[]{dx, dy});
                            MOVES.offer(new int[]{dx, dy});
                        }
                    }
                }
                if (ADJACENT_NUMBERS.size() > 1) {
                    canNotDelete = false;
                    count -= ADJACENT_NUMBERS.size();
                    while (!ADJACENT_NUMBERS.isEmpty()) {
                        int[] adjacentNumber = ADJACENT_NUMBERS.poll();
                        int x = adjacentNumber[0];
                        int y = adjacentNumber[1];
                        sum -= circles[x][y];
                        circles[x][y] = DELETE;
                    }
                } else {
                    ADJACENT_NUMBERS.clear();
                }
            }
        }
        if (canNotDelete) {
            double avg = (double) sum / count;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < m; j++) {
                    if (circles[i][j] == DELETE) {
                        continue;
                    }
                    if (circles[i][j] > avg) {
                        circles[i][j]--;
                        sum--;
                    } else if (circles[i][j] < avg) {
                        circles[i][j]++;
                        sum++;
                    }
                }
            }
        }
    }
}
