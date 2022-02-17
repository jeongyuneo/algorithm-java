package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};   // 우, 하, 좌, 상
    private static final boolean[] IS_SELECTED = new boolean[26];

    private static char[][] board;
    private static int maxMove;
    private static int r;
    private static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());
        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            String input = bufferedReader.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = input.charAt(j);
            }
        }
        move(0, 0, 1);
        System.out.println(maxMove);
    }

    private static void move(int x, int y, int cnt) {
        IS_SELECTED[board[x][y]-65] = true;
        for (int[] delta : DELTAS) {
            int dx = x + delta[0];
            int dy = y + delta[1];
            if (dx >= 0 && dx < r && dy >= 0 && dy < c && !IS_SELECTED[board[dx][dy]-65]) {
                move(dx, dy, cnt+1);
            }
        }
        IS_SELECTED[board[x][y]-65] = false;
        maxMove = Math.max(maxMove, cnt);
    }
}
