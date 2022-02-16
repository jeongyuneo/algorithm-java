package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1992 {

    private static StringBuilder stringBuilder;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] data = new int[n][n];
        for (int i = 0; i < n; i++) {
            String input = bufferedReader.readLine();
            for (int j = 0; j < n; j++) {
                data[i][j] = input.charAt(j) - 48;
            }
        }
        stringBuilder = new StringBuilder();
        divide(data, 0, 0, n);
        System.out.println(stringBuilder);
    }

    private static void divide(int[][] data, int startX, int startY, int length) {
        if (length == 0) {
            return;
        }

        int area = getArea(data, startX, startY, length);
        int half = length / 2;

        if (area == 0) {
            stringBuilder.append(0);
        } else if (area == length*length) {
            stringBuilder.append(1);
        } else {
            stringBuilder.append("(");
            divide(data, startX, startY, half); // 왼쪽 위
            divide(data, startX, startY+half, half); // 오른쪽 위
            divide(data, startX+half, startY, half); // 왼쪽 아래
            divide(data, startX+half, startY+half, half); // 오른쪽 아래
            stringBuilder.append(")");
        }
    }

    private static int getArea(int[][] data, int startX, int startY, int length) {
        int sum = 0;
        for (int i = startX; i < startX+length; i++) {
            for (int j = startY; j < startY+length; j++) {
                sum += data[i][j];
            }
        }
        return sum;
    }
}
