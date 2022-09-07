package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14888 {

    private static final List<Integer> OPERATOR = new ArrayList<>();

    private static int n;
    private static int[] numbers;
    private static boolean[] isVisited;
    private static int[] selected;
    private static int operatorCount;
    private static int min;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int count = Integer.parseInt(st.nextToken());
            operatorCount += count;
            while (count-- > 0) {
                OPERATOR.add(i);
            }
        }

        isVisited = new boolean[OPERATOR.size()];
        selected = new int[OPERATOR.size()];
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        selectOperator(0);
        System.out.println(max + "\n" + min);
    }

    private static void selectOperator(int cnt) {
        if (cnt == operatorCount) {
            int result = calculate();
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        for (int i = 0; i < operatorCount; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                selected[cnt] = OPERATOR.get(i);
                selectOperator(cnt + 1);
                isVisited[i] = false;
            }
        }
    }

    private static int calculate() {
        int idx = 0;
        int result = numbers[idx++];
        int operatorIdx = 0;
        while (idx < n) {
            int operator = selected[operatorIdx++];
            if (operator == 0) {
                result += numbers[idx];
            } else if (operator == 1) {
                result -= numbers[idx];
            } else if (operator == 2) {
                result *= numbers[idx];
            } else {
                result /= numbers[idx];
            }
            idx++;
        }
        return result;
    }
}
