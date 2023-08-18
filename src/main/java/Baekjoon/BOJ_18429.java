package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18429 {

    private static final int MIN_WEIGHT = 500;

    private static int[] weights;
    private static boolean[] isSelected;
    private static int n;
    private static int k;
    private static int caseOfExcercise;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());
        weights = new int[n];
        isSelected = new boolean[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        selectWeight(0, MIN_WEIGHT);
        System.out.println(caseOfExcercise);
    }

    private static void selectWeight(int count, int sum) {
        if (count == n) {
            caseOfExcercise++;
            return;
        }
        for (int i = 0; i < n; i++) {
            int next = sum + weights[i] - k;
            if (!isSelected[i] && next >= MIN_WEIGHT) {
                isSelected[i] = true;
                selectWeight(count + 1, next);
                isSelected[i] = false;
            }
        }
    }
}
