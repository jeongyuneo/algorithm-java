package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109 {

    private static final int[] DELTAS = {-1, 0, 1};

    private static char[][] space;
    private static boolean isReached;
    private static int r;
    private static int c;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());
        space = new char[r][c];
        for (int i = 0; i < r; i++) {
            String input = bufferedReader.readLine();
            for (int j = 0; j < c; j++) {
                space[i][j] = input.charAt(j);
            }
        }

        for (int i = 0; i < r; i++) {
            isReached = false;
            setUpPipe(i, 0);
        }
        System.out.println(result);
    }

    private static void setUpPipe(int x, int y) {
        if (y == c-1) {
            result++;
            isReached = true;
            return;
        } else if (space[x][y] == 'x') {
            return;
        }
        space[x][y] = 'x';
        for (int delta : DELTAS) {
            int dx = x + delta;
            if (dx >= 0 && dx < r && !isReached) {
                setUpPipe(dx, y+1);
            }
        }
    }
}
