package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] array = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(array);

        int m = Integer.parseInt(bufferedReader.readLine());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            answer.append(search(array, n, Integer.parseInt(stringTokenizer.nextToken())))
                    .append("\n");
        }
        System.out.println(answer);
    }

    private static int search(int[] array, int n, int key) {
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (array[mid] < key) {
                start = mid + 1;
            } else if (array[mid] > key) {
                end = mid - 1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
