package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_10816 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        Map<Integer, Integer> cards = new HashMap<>();
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            int card = Integer.parseInt(stringTokenizer.nextToken());
            if (cards.containsKey(card)) {
                cards.put(card, cards.get(card) + 1);
            } else {
                cards.put(card, 1);
            }
        }

        int m = Integer.parseInt(bufferedReader.readLine());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int card = Integer.parseInt(stringTokenizer.nextToken());
            int cardCount = 0;
            if (cards.containsKey(card)) {
                cardCount = cards.get(card);
            }
            answer.append(cardCount)
                    .append(" ");
        }
        System.out.println(answer);
    }
}
