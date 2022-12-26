package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            Map<Character, List<Integer>> indexInfo = new HashMap<>();
            int[] counts = new int[26];
            for (int j = 0, wLength = w.length; j < wLength; j++) {
                char c = w[j];
                indexInfo.computeIfAbsent(c, index -> new ArrayList<>()).add(j);
                counts[c - 97]++;
            }

            int minLength = w.length;
            int maxLength = -1;
            for (Map.Entry<Character, List<Integer>> indexes : indexInfo.entrySet()) {
                if (counts[indexes.getKey() - 97] >= k) {
                    List<Integer> index = indexes.getValue();
                    for (int j = 0; j <= index.size() - k; j++) {
                        minLength = Math.min(minLength, index.get(j + k - 1) - index.get(j) + 1);
                        maxLength = Math.max(maxLength, index.get(j + k - 1) - index.get(j) + 1);
                    }
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
