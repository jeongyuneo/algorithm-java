package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_14002 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int maxLength = 0;
        int[] lengths = new int[n];
        for (int i = 0; i < n; i++) {
            lengths[i] = 1;
            for (int j = 0; j < i; j++) {
                if (numbers[j] < numbers[i] && lengths[i] < lengths[j] + 1) {
                    lengths[i] = lengths[j] + 1;
                }
            }
            maxLength = Math.max(maxLength, lengths[i]);
        }
        Stack<Integer> subnet = new Stack<>();
        int idx = maxLength;
        for (int i = n - 1; i >= 0; i--) {
            if (lengths[i] == idx) {
                subnet.push(numbers[i]);
                idx--;
            }
        }
        StringBuilder answer = new StringBuilder();
        answer.append(maxLength).append("\n");
        while (!subnet.isEmpty()) {
            answer.append(subnet.pop()).append(" ");
        }
        System.out.println(answer);
    }
}
