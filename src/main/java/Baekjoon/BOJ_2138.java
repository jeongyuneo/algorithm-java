package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2138 {

    private static final int ON = 0;
    private static final int OFF = 1;
    private static final int AIM = 2;

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        String init = bufferedReader.readLine();
        String aim = bufferedReader.readLine();
        int[][] bulbs = new int[3][n + 1];
        for (int i = 0; i < n; i++) {
            bulbs[ON][i] = init.charAt(i) - '0';
            bulbs[OFF][i] = init.charAt(i) - '0';
            bulbs[AIM][i] = aim.charAt(i) - '0';
        }
        bulbs[ON][0] = convert(bulbs[ON][0]);
        bulbs[ON][1] = convert(bulbs[ON][1]);
        bulbs[ON][n] = 1;
        for (int i = 1; i < n; i++) {
            if (bulbs[ON][i - 1] != bulbs[AIM][i - 1]) {
                switchOn(bulbs, ON, i);
                bulbs[ON][n]++;
            }
            if (bulbs[OFF][i - 1] != bulbs[AIM][i - 1]) {
                switchOn(bulbs, OFF, i);
                bulbs[OFF][n]++;
            }
        }
        compareWithAim(bulbs, ON);
        compareWithAim(bulbs, OFF);
        if (bulbs[ON][n] == Integer.MAX_VALUE && bulbs[OFF][n] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(bulbs[ON][n], bulbs[OFF][n]));
        }
    }

    private static int convert(int bulb) {
        return 1 - bulb;
    }

    private static void switchOn(int[][] bulbs, int start, int i) {
        bulbs[start][i - 1] = convert(bulbs[start][i - 1]);
        bulbs[start][i] = convert(bulbs[start][i]);
        if (i < n - 1) {
            bulbs[start][i + 1] = convert(bulbs[start][i + 1]);
        }
    }

    private static void compareWithAim(int[][] bulbs, int start) {
        if (bulbs[start][n - 1] != bulbs[AIM][n - 1]) {
            bulbs[start][n] = Integer.MAX_VALUE;
        }
    }
}
