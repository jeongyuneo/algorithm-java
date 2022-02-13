package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2669 {

    private static final int SIZE = 100;

    private static boolean[][] isSelected = new boolean[SIZE+1][SIZE+1];
    private static StringTokenizer stringTokenizer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int startX = Integer.parseInt(stringTokenizer.nextToken());
            int startY = Integer.parseInt(stringTokenizer.nextToken());
            int endX = Integer.parseInt(stringTokenizer.nextToken());
            int endY = Integer.parseInt(stringTokenizer.nextToken());
            for (int x = startX; x < endX; x++) {
                for (int y = startY; y < endY; y++) {
                    if (!isSelected[x][y]) {
                        isSelected[x][y] = true;
                        sum++;
                    }
                }
            }
        }
        System.out.println(sum);
    }
}
