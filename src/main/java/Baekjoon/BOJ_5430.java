package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BOJ_5430 {

    private static final Deque<Integer> DEQUE = new ArrayDeque<>();
    private static final StringBuilder ARRAY = new StringBuilder();
    private static final String DELIMITER = ",";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            String functions = bufferedReader.readLine();
            int n = Integer.parseInt(bufferedReader.readLine());
            String numbers = bufferedReader.readLine();
            if (n > 0) {
                Arrays.stream(numbers.substring(1, numbers.length() - 1).split(DELIMITER))
                        .mapToInt(Integer::parseInt)
                        .forEach(DEQUE::offer);
            }
            answer.append(getResult(functions)).append("\n");
        }
        System.out.println(answer);
    }

    private static String getResult(String functions) {
        boolean isFront = true;
        for (int i = 0; i < functions.length(); i++) {
            char function = functions.charAt(i);
            if (function == 'R') {
                isFront = !isFront;
            } else {
                if (DEQUE.isEmpty()) {
                    return "error";
                }
                if (isFront) {
                    DEQUE.pollFirst();
                } else {
                    DEQUE.pollLast();
                }
            }
        }
        return toArray(isFront);
    }

    private static String toArray(boolean isFront) {
        ARRAY.setLength(0);
        ARRAY.append("[");
        if (isFront) {
            while (!DEQUE.isEmpty()) {
                ARRAY.append(DEQUE.pollFirst()).append(DELIMITER);
            }
        } else {
            while (!DEQUE.isEmpty()) {
                ARRAY.append(DEQUE.pollLast()).append(DELIMITER);
            }
        }
        if (ARRAY.length() > 1) {
            ARRAY.setLength(ARRAY.length() - 1);
        }
        ARRAY.append("]");
        return ARRAY.toString();
    }
}
