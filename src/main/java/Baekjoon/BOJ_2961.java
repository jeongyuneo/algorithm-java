package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2961 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int ingredientNum = Integer.parseInt(bufferedReader.readLine());
        int[][] ingredients = new int[ingredientNum][2];
        for (int n = 0; n < ingredientNum; n++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            ingredients[n] = new int[]{s, b};
        }

        int min = Integer.MAX_VALUE;
        for (int flag = 1; flag < 1 << ingredientNum; flag++) {
            int totalS = 1;
            int totalB = 0;
            for (int i = 0; i < ingredientNum; i++) {
                if ((flag & 1 << i) != 0) {
                    totalS *= ingredients[i][0];
                    totalB += ingredients[i][1];
                }
            }
            min = Math.min(min, Math.abs(totalB - totalS));
        }
        System.out.println(min);
    }
}
