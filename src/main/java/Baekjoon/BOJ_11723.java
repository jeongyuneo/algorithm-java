package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11723 {

    private static final int INITIALIZED_S = 1048575;
    private static final int CLEAR = 0;
    private static final String ADD = "add";
    private static final String REMOVE = "remove";
    private static final String CHECK = "check";
    private static final String TOGGLE = "toggle";
    private static final String ALL = "all";
    private static final String EMPTY = "empty";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(bufferedReader.readLine());
        int s = 0;
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            String operator = stringTokenizer.nextToken();
            int number = 0;
            if (stringTokenizer.hasMoreTokens()) {
                number = Integer.parseInt(stringTokenizer.nextToken());
            }
            int binary = 1 << (number - 1);
            if (operator.equals(ADD)) {
                s = s | binary;
            } else if (operator.equals(REMOVE)) {
                s = s & ~binary;
            } else if (operator.equals(CHECK)) {
                if ((s & binary) == binary) {
                    answer.append(1).append("\n");
                } else {
                    answer.append(0).append("\n");
                }
            } else if (operator.equals(TOGGLE)) {
                s = s ^ binary;
            } else if (operator.equals(ALL)) {
                s = INITIALIZED_S;
            } else if (operator.equals(EMPTY)) {
                s = CLEAR;
            }
        }
        System.out.println(answer);
    }
}
