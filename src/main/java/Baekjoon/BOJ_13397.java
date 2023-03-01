package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13397 {

    private static int[] numbers;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        numbers = new int[n];
        int min = 10000;
        int max = 0;
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(stringTokenizer.nextToken());
            numbers[i] = number;
            min = Math.min(min, number);
            max = Math.max(max, number);
        }
        int start = 0;
        int end = max - min;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (canDivideBy(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start);
    }

    private static boolean canDivideBy(int gap) {
        int section = 1;
        int min = numbers[0];
        int max = numbers[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, numbers[i]);
            max = Math.max(max, numbers[i]);
            if (max - min > gap) {
                section++;
                min = numbers[i];
                max = numbers[i];
            }
        }
        return section <= m;
    }
}
