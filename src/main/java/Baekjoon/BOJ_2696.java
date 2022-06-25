package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2696 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = null;
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        List<Integer> numbers = new ArrayList<>();
        for (int t = 0; t < testCase; t++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            answer.append(n / 2 + 1)
                    .append("\n");

            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (i % 10 == 1) {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                }
                numbers.add(Integer.parseInt(stringTokenizer.nextToken()));
                if (i % 2 == 1) {
                    Collections.sort(numbers);
                    answer.append(numbers.get(i / 2))
                            .append(" ");
                    if (++cnt % 10 == 0) {
                        answer.append("\n");
                    }
                }
            }
            answer.append("\n");
            numbers.clear();
        }
        System.out.println(answer);
    }
}
