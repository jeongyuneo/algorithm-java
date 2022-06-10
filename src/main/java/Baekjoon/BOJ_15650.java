package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15650 {

    private static int[] numbers;
    private static boolean[] isSelected;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        numbers = new int[m];
        isSelected = new boolean[n + 1];
        System.out.println(getSequence(new StringBuilder(), 1, 0));
    }

    private static StringBuilder getSequence(StringBuilder answer, int start, int cnt) {
        if (cnt == m) {
            for (int number : numbers) {
                answer.append(number)
                        .append(" ");
            }
            answer.append("\n");
            return answer;
        }

        for (int i = start; i <= n; i++) {
            if (!isSelected[i]) {
                numbers[cnt] = i;
                isSelected[i] = true;
                getSequence(answer, i + 1, cnt + 1);
                isSelected[i] = false;
            }
        }
        return answer;
    }
}
