package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15649 {

    private static int[] sequence;
    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        sequence = new int[m];
        isVisited = new boolean[n + 1];
        System.out.println(getSequence(new StringBuilder(), n, m, 0));
    }

    private static StringBuilder getSequence(StringBuilder answer, int n, int m, int cnt) {
        if (cnt == m) {
            for (int i = 0; i < m; i++) {
                answer.append(sequence[i])
                        .append(" ");
            }
            answer.append("\n");
            return answer;
        }

        for (int i = 1; i <= n; i++) {
            if (!isVisited[i]) {
                sequence[cnt] = i;
                isVisited[i] = true;
                getSequence(answer, n, m, cnt + 1);
                isVisited[i] = false;
            }
        }
        return answer;
    }
}
