package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3151 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(numbers);
        long team = 0L;
        for (int first = 0; first < n - 2; first++) {
            if (numbers[first] > 0) {
                break;
            }
            int second = first + 1;
            int third = n - 1;
            while (second < third) {
                int sum = numbers[first] + numbers[second] + numbers[third];
                if (sum == 0) {
                    if (numbers[second] == numbers[third]) {
                        team += selectTwoOf(third - second + 1);
                        break;
                    }
                    long sameAsSecond = 1;
                    long sameAsThird = 1;
                    while (numbers[second] == numbers[second + 1]) {
                        sameAsSecond++;
                        second++;
                    }
                    while (numbers[third] == numbers[third - 1]) {
                        sameAsThird++;
                        third--;
                    }
                    team += sameAsSecond * sameAsThird;
                }
                if (sum < 0) {
                    second++;
                } else {
                    third--;
                }
            }
        }
        System.out.println(team);
    }

    private static long selectTwoOf(int n) {
        return (long) n * (n - 1) / 2;
    }
}
