package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_1764 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        Set<String> thoseWhoHaveNeverHeard = new HashSet<>();
        for (int i = 0; i < n; i++) {
            thoseWhoHaveNeverHeard.add(bufferedReader.readLine());
        }
        Set<String> thoseWhoHaveNeverHeardAndSeen = new TreeSet<>();
        for (int i = 0; i < m; i++) {
            String input = bufferedReader.readLine();
            if (thoseWhoHaveNeverHeard.contains(input)) {
                thoseWhoHaveNeverHeardAndSeen.add(input);
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(thoseWhoHaveNeverHeardAndSeen.size())
                .append("\n");
        for (String whoHaveNeverHeardAndSeen : thoseWhoHaveNeverHeardAndSeen) {
            answer.append(whoHaveNeverHeardAndSeen)
                    .append("\n");
        }
        System.out.println(answer);
    }
}
