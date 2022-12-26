package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_20437 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            char[] w = bufferedReader.readLine().toCharArray();
            int k = Integer.parseInt(bufferedReader.readLine());

            if (k == 1) {
                answer.append("1 1\n");
                continue;
            }

            Map<Character, Queue<Integer>> indexInfo = new HashMap<>();
            int minLength = w.length;
            int maxLength = -1;
            for (int lastIndex = 0, wLength = w.length; lastIndex < wLength; lastIndex++) {
                char c = w[lastIndex];
                indexInfo.computeIfAbsent(c, index -> new ArrayDeque<>()).offer(lastIndex);
                Queue<Integer> indexes = indexInfo.get(c);
                if (indexes.size() >= k) {
                    int length = lastIndex - indexes.poll() + 1;
                    minLength = Math.min(minLength, length);
                    maxLength = Math.max(maxLength, length);
                }
            }

            if (maxLength == -1) {
                answer.append(maxLength);
            } else {
                answer.append(minLength)
                        .append(" ")
                        .append(maxLength);
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
