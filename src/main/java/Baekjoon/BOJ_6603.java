package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_6603 {

    private static final List<int[]> LOTTOS = new ArrayList<>();
    private static final int[] LOTTO = new int[6];
    private static final boolean[] IS_SELECTED = new boolean[50];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        while (true) {
            LOTTOS.clear();
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            if (k == 0) {
                break;
            }
            int[] numbers = new int[k];
            for (int i = 0; i < k; i++) {
                numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            chooseLotto(answer, numbers, k, 0, 0);
            answer.append("\n");
        }
        System.out.println(answer);
    }

    private static void chooseLotto(StringBuilder answer, int[] numbers, int k, int start, int cnt) {
        if (cnt == 6) {
            for (int lotto : LOTTO) {
                answer.append(lotto)
                        .append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int i = start; i < k; i++) {
            if (!IS_SELECTED[numbers[i]]) {
                LOTTO[cnt] = numbers[i];
                IS_SELECTED[numbers[i]] = true;
                chooseLotto(answer, numbers, k, i + 1, cnt + 1);
                IS_SELECTED[numbers[i]] = false;
            }
        }
    }
}
