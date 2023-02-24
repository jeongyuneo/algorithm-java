package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663 {

    private static int[] map;
    private static int n;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        map = new int[n];
        selectN(0);
        System.out.println(answer);
    }

    private static void selectN(int depth) {
        if (depth == n) {
            answer++;
            return;
        }
        for (int i = 0; i < n; i++) {
            map[depth] = i;
            if (canSelect(depth)) {
                selectN(depth + 1);
            }
        }
    }

    private static boolean canSelect(int depth) {
        for (int i = 0; i < depth; i++) {
            if (map[i] == map[depth] || Math.abs(i - depth) == Math.abs(map[i] - map[depth])) {
                return false;
            }
        }
        return true;
    }
}
