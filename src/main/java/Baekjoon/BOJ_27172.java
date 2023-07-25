package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_27172 {

    private static final int MAX = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] cards = new int[n];
        boolean[] isCard = new boolean[MAX + 1];
        for (int i = 0; i < n; i++) {
            int card = Integer.parseInt(stringTokenizer.nextToken());
            cards[i] = card;
            isCard[card] = true;
        }
        int[] scores = new int[MAX + 1];
        for (int card : cards) {
            for (int j = 2; card * j <= MAX; j++) {
                if (isCard[card * j]) {
                    scores[card * j]--;
                    scores[card]++;
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (int card : cards) {
            result.append(scores[card]).append(" ");
        }
        System.out.println(result);
    }
}
