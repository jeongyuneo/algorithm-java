package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13422 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (testCase-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            int[] money = new int[n];
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < n; i++) {
                money[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            int stolenMoney = 0;
            for (int i = 0; i < m; i++) {
                stolenMoney += money[i];
            }
            int count = n;
            if (n == m) {
                count = 1;
            }
            int idx = m;
            int caseOfTheft = 0;
            while (count-- > 0) {
                if (stolenMoney < k) {
                    caseOfTheft++;
                }
                stolenMoney -= money[idx - m];
                stolenMoney += money[(idx++) % n];
            }
            answer.append(caseOfTheft).append("\n");
        }
        System.out.println(answer);
    }
}
