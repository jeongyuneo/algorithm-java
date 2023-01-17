package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2473 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        long[] numbers = new long[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(stringTokenizer.nextToken());
        }
        Arrays.sort(numbers);
        long[] answers = getAnswers(n, numbers);
        System.out.println(answers[0] + " " + answers[1] + " " + answers[2]);
    }

    private static long[] getAnswers(int n, long[] numbers) {
        long minGap = Long.MAX_VALUE;
        long[] answers = new long[3];
        for (int start = 0; start < n - 2; start++) {
            int mid = start + 1;
            int end = n - 1;
            while (mid < end) {
                long sum = numbers[start] + numbers[mid] + numbers[end];
                long gapFromZero = Math.abs(sum);
                if (minGap > gapFromZero) {
                    answers[0] = numbers[start];
                    answers[1] = numbers[mid];
                    answers[2] = numbers[end];
                    minGap = gapFromZero;
                }

                if (sum > 0) {
                    end--;
                } else if (sum < 0) {
                    mid++;
                } else {
                    return answers;
                }
            }
        }
        return answers;
    }
}
