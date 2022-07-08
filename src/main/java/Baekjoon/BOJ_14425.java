package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_14425 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        HashSet<String> group = new HashSet<>();
        for (int i = 0; i < n; i++) {
            group.add(bufferedReader.readLine());
        }

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (group.contains(bufferedReader.readLine())) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
