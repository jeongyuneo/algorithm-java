package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16724 {

    private static final int[][] DELTAS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int[] roots;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String inputLine = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = getDirection(inputLine.charAt(j));
            }
        }
        setRoots(n, m);
        System.out.println(getSafeZone(n, m, map));
    }

    private static int getDirection(char input) {
        int direction = 0;
        if (input == 'D') {
            direction = 1;
        } else if (input == 'L') {
            direction = 2;
        } else if (input == 'R') {
            direction = 3;
        }
        return direction;
    }

    private static void setRoots(int n, int m) {
        roots = new int[n * m];
        for (int i = 1; i < n * m; i++) {
            roots[i] = i;
        }
    }

    private static int getSafeZone(int n, int m, int[][] map) {
        findSafeZone(n, m, map);
        int safeZone = 0;
        for (int i = 0; i < n * m; i++) {
            if (i == roots[i]) {
                safeZone++;
            }
        }
        return safeZone;
    }

    private static void findSafeZone(int n, int m, int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int direction = map[i][j];
                int current = i * m + j;
                int next = (i + DELTAS[direction][0]) * m + j + DELTAS[direction][1];
                combine(current, next);
            }
        }
    }

    private static void combine(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA > rootB) {
            roots[rootA] = rootB;
        } else if (rootA < rootB) {
            roots[rootB] = rootA;
        }
    }

    private static int find(int value) {
        if (value == roots[value]) {
            return value;
        }
        return roots[value] = find(roots[value]);
    }
}
