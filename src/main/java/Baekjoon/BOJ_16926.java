package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926 {

    private static final int[][] DELTAS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static int[][] array;
    private static int n;
    private static int m;
    private static int r;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        r = Integer.parseInt(stringTokenizer.nextToken());

        array = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= m; j++) {
                array[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = 0; i < r; i++) {
            rotate();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                stringBuilder.append(array[i][j])
                        .append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void rotate() {
        int rotationNum = Math.min(n, m) / 2;
        for (int i = 1; i <= rotationNum; i++) {
            int x = i;
            int y = i;
            int currentNumber = array[x][y];

            int direction = 0;
            while (direction < 4) {
                int dx = x + DELTAS[direction][0];
                int dy = y + DELTAS[direction][1];
                if (dx >= i && dx <= n-i+1 && dy >= i && dy <= m-i+1) {
                    x += DELTAS[direction][0];
                    y += DELTAS[direction][1];

                    int nextNumber = array[x][y];
                    array[x][y] = currentNumber;
                    currentNumber = nextNumber;
                } else {
                    direction++;
                }
            }
        }
    }
}
