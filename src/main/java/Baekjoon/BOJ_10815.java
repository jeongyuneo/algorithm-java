package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10815 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] cards = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(cards);

        int m = Integer.parseInt(bufferedReader.readLine());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int key = Integer.parseInt(stringTokenizer.nextToken());
            answer.append(search(cards, 0, n - 1, key))
                    .append(" ");
        }
        System.out.println(answer);
    }

    private static int search(int[] cards, int start, int end, int key) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (cards[mid] < key) {
                start = mid + 1;
            } else if (cards[mid] > key) {
                end = mid - 1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
