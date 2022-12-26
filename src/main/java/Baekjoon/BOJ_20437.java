package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_20437 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String w = bufferedReader.readLine();
            int k = Integer.parseInt(bufferedReader.readLine());

            if (k == 1) {
                answer.append("1 1\n");
                continue;
            }

            int minLength = w.length() + 1;
            int maxLength = -1;
            for (int j = 0; j < w.length() - k; j++) {
                char character = w.charAt(j);
                int count = 1;
                for (int l = j + 1; l < w.length(); l++) {
                    if (character == w.charAt(l)) {
                        count++;
                    }
                    if (count == k) {
                        minLength = Math.min(minLength, l - j + 1);
                        maxLength = Math.max(maxLength, l - j + 1);
                        break;
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
