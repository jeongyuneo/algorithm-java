package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493 {

    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder stringBuilder = new StringBuilder();
    private static final Stack<int[]> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(BUFFERED_READER.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(BUFFERED_READER.readLine());

        for (int i = 1; i <= n; i++) {
            int height = Integer.parseInt(stringTokenizer.nextToken());
            if (stack.isEmpty()) {
                stringBuilder.append("0 ");
                stack.push(new int[]{height, i});
            } else {
                while (!stack.isEmpty()) {
                    int[] pop = stack.peek();
                    if (pop[0] < height) {
                        stack.pop();
                    } else {
                        stringBuilder.append(pop[1] + " ");
                        stack.push(new int[]{height, i});
                        break;
                    }
                }
                if (stack.isEmpty()) {
                    stringBuilder.append("0 ");
                    stack.push(new int[]{height, i});
                }

            }
        }
        System.out.println(stringBuilder);
    }
}
