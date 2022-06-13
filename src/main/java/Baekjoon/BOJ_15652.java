package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15652 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        System.out.println(getSequence(new StringBuilder(), new int[m], n, m, 1, 0));
    }

    private static StringBuilder getSequence(StringBuilder answer, int[] numbers, int n, int m, int start, int cnt) {
        if (cnt == m) {
            for (int number : numbers) {
                answer.append(number)
                        .append(" ");
            }
            answer.append("\n");
            return answer;
        }

        for (int i = start; i <= n; i++) {
            numbers[cnt] = i;
            getSequence(answer, numbers, n, m, i, cnt + 1);
        }
        return answer;
    }
}
