package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15656 {

    private static final StringBuilder ANSWER = new StringBuilder();

    private static int[] numbers;
    private static int n;
    private static int m;
    private static int[] selectedNumbers;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        numbers = new int[n];
        selectedNumbers = new int[m];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(numbers);
        generateRepeatedPermutation(0);
        System.out.println(ANSWER);
    }

    private static void generateRepeatedPermutation(int cnt) {
        if (cnt == m) {
            for (int i = 0; i < m; i++) {
                ANSWER.append(selectedNumbers[i] + " ");
            }
            ANSWER.append("\n");
            return;
        }
        for (int i = 0; i < n; i++) {
            selectedNumbers[cnt] = numbers[i];
            generateRepeatedPermutation(cnt + 1);
        }
    }
}
