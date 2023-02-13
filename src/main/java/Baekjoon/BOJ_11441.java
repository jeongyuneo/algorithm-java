package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11441 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] sums = new int[n + 1];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= n; i++) {
            sums[i] = Integer.parseInt(stringTokenizer.nextToken()) + sums[i - 1];
        }
        int m = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (m-- > 0) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken());
            answer.append(sums[to] - sums[from]).append("\n");
        }
        System.out.println(answer);
    }
}
