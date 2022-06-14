package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15654 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[] numbers = new int[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(numbers);
        System.out.println(getSequence(new StringBuilder(), numbers, new int[m], new boolean[10001], n, m, 0));
    }

    private static StringBuilder getSequence(StringBuilder answer, int[] numbers, int[] selectedNumbers, boolean[] isSelected, int n, int m, int cnt) {
        if (cnt == m) {
            for (int selectedNumber : selectedNumbers) {
                answer.append(selectedNumber)
                        .append(" ");
            }
            answer.append("\n");
            return answer;
        }

        for (int i = 0; i < n; i++) {
            if (!isSelected[numbers[i]]) {
                selectedNumbers[cnt] = numbers[i];
                isSelected[numbers[i]] = true;
                getSequence(answer, numbers, selectedNumbers, isSelected, n, m, cnt + 1);
                isSelected[numbers[i]] = false;
            }
        }
        return answer;
    }
}
