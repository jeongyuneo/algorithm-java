package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17265 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}};
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLY = '*';
    private static final char NONE = ' ';

    private static char[][] map;
    private static int n;
    private static int max;
    private static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = stringTokenizer.nextToken().charAt(0);
            }
        }
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        move(0, 0, toInteger(map[0][0]), NONE);
        System.out.println(max + " " + min);
    }

    private static void move(int x, int y, int operationResult, char operation) {
        if (x == n - 1 && y == n - 1) {
            max = Math.max(max, operationResult);
            min = Math.min(min, operationResult);
            return;
        }
        for (int[] delta : DELTAS) {
            int dx = x + delta[0];
            int dy = y + delta[1];
            if (dx >= 0 && dx < n && dy >= 0 && dy < n) {
                if (isNumber(map[dx][dy])) {
                    move(dx, dy, operate(operation, operationResult, toInteger(map[dx][dy])), NONE);
                } else {
                    move(dx, dy, operationResult, map[dx][dy]);
                }
            }
        }
    }

    private static int operate(char operation, int operationResult, int number) {
        if (operation == PLUS) {
            return operationResult + number;
        }
        if (operation == MINUS) {
            return operationResult - number;
        }
        if (operation == MULTIPLY) {
            return operationResult * number;
        }
        return 0;
    }

    private static boolean isNumber(char value) {
        return value >= '0' && value <= '5';
    }

    private static int toInteger(char value) {
        return value - '0';
    }
}
