package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1392 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        final int scoreCount = Integer.parseInt(stringTokenizer.nextToken());
        final int questionCount = Integer.parseInt(stringTokenizer.nextToken());
        List<Integer> singTimes = new ArrayList<>();
        for (int score = 0; score < scoreCount; score++) {
            int time = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < time; i++) {
                singTimes.add(score + 1);
            }
        }
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < questionCount; i++) {
            int question = Integer.parseInt(bufferedReader.readLine());
            result.append(singTimes.get(question)).append("\n");
        }
        System.out.println(result);
    }
}
