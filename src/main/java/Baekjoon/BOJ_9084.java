package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9084 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder result = new StringBuilder();
        while (testCase-- > 0) {
            int n = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int money = Integer.parseInt(bufferedReader.readLine());
            int[] caseOfMakingMoney = new int[money + 1];
            caseOfMakingMoney[0] = 1;
            for (int i = 0; i < n; i++) {
                int coin = Integer.parseInt(stringTokenizer.nextToken());
                for (int j = coin; j <= money; j++) {
                    caseOfMakingMoney[j] += caseOfMakingMoney[j - coin];
                }
            }
            result.append(caseOfMakingMoney[money]).append("\n");
        }
        System.out.println(result);
    }
}
