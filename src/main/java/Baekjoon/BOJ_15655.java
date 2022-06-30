package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15655 {

    private static int n;
    private static int m;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        numbers = new int[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(numbers);
        StringBuilder answer = new StringBuilder();
        getSequence(answer, new int[m], new boolean[n], 0, 0);
        System.out.println(answer);
    }

    private static void getSequence(StringBuilder answer, int[] selectedNumbers, boolean[] isSelected, int cnt, int start) {
        if (cnt == m) {
            for (int selectedNumber : selectedNumbers) {
                answer.append(selectedNumber)
                        .append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int i = start; i < n; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                selectedNumbers[cnt] = numbers[i];
                getSequence(answer, selectedNumbers, isSelected, cnt + 1, i + 1);
                isSelected[i] = false;
            }
        }
    }
}
