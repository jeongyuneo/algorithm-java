package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20057 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int[][] deltas = new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        // 좌
        int[][] spreadDeltas1 = new int[][]{{-1, 1}, {1, 1}, {-2, 0}, {2, 0},
                {-1, 0}, {1, 0}, {-1, -1}, {1, -1}, {0, -2}, {0, -1}};    // 1, 1, 2, 2, 7, 7, 10, 10, 5, a
        // 하
        int[][] spreadDeltas2 = new int[][]{{-1, -1}, {-1, 1}, {0, -2}, {0, 2},
                {0, -1}, {0, 1}, {1, -1}, {1, 1}, {2, 0}, {1, 0}};
        // 우
        int[][] spreadDeltas3 = new int[][]{{-1, -1}, {1, -1}, {-2, 0}, {2, 0},
                {-1, 0}, {1, 0}, {-1, 1}, {1, 1}, {0, 2}, {0, 1}};
        // 상
        int[][] spreadDeltas4 = new int[][]{{1, -1}, {1, 1}, {0, -2}, {0, 2},
                {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {-2, 0}, {-1, 0}};

        int[] moves = new int[]{1, 1, 2, 2};
        int x = n / 2;
        int y = n / 2;
        int totalSend = 0;
        while (x != 0 || y != 0) {
            for (int direction = 0; direction < 4; direction++) {
                int move = moves[direction];
                while (move-- > 0) {
                    if (x == 0 && y == 0) {
                        break;
                    }
                    x += deltas[direction][0];
                    y += deltas[direction][1];
                    int initSend = map[x][y];
                    if (initSend == 0) {
                        continue;
                    }

                    int rate = 1;
                    int spread = 0;
                    int[][] spreadDeltas = spreadDeltas1;
                    if (direction == 1) {
                        spreadDeltas = spreadDeltas2;
                    } else if (direction == 2) {
                        spreadDeltas = spreadDeltas3;
                    } else if (direction == 3) {
                        spreadDeltas = spreadDeltas4;
                    }

                    for (int i = 0; i < 10; i++) {
                        int[] spreadLocation = spreadDeltas[i];
                        int dx = x + spreadLocation[0];
                        int dy = y + spreadLocation[1];
                        int spreadSend = initSend * rate / 100;
                        if (i == 9) {
                            spreadSend = initSend - spread;
                        }

                        if (dx < 0 || dx >= n || dy < 0 || dy >= n) {
                            totalSend += spreadSend;
                        } else {
                            map[dx][dy] += spreadSend;
                        }
                        spread += spreadSend;

                        if (i == 1) {
                            rate = 2;
                        } else if (i == 3) {
                            rate = 7;
                        } else if (i == 5) {
                            rate = 10;
                        } else if (i == 7) {
                            rate = 5;
                        }
                    }
                }
                moves[direction] += 2;
            }
        }
        System.out.println(totalSend);
    }
}
