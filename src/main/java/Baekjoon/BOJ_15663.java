package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15663 {

    private static final StringBuilder ANSWER = new StringBuilder();
    private static final List<String> SELECTED_NUMBERS = new ArrayList<>();

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
        select(0, "", 0);
        System.out.println(ANSWER);
    }

    private static void select(int cnt, String selectedNumbers, int flag) {
        if (cnt == m) {
            if (!SELECTED_NUMBERS.contains(selectedNumbers)) {
                SELECTED_NUMBERS.add(selectedNumbers);
                ANSWER.append(selectedNumbers).append("\n");
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((flag & (1 << i)) == 0) {
                select(cnt + 1, selectedNumbers + numbers[i] + " ", flag | (1 << i));
            }
        }
    }
}
