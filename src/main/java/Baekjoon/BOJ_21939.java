package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21939 {

    static class Problem implements Comparable<Problem> {

        int number;
        int level;

        public Problem(int number, int level) {
            this.number = number;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            if (level == o.level) {
                return number - o.number;
            }
            return level - o.level;
        }
    }

    private static final int[] PROBLEMS = new int[100001];
    private static final String RECOMMEND = "recommend";
    private static final String ADD = "add";
    private static final String SOLVED = "solved";
    private static final String DELIMITER = " ";
    private static final int PROBLEM = 0;
    private static final int LEVEL = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        TreeSet<Problem> problems = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            String[] inputs = bufferedReader.readLine().split(DELIMITER);
            int number = Integer.parseInt(inputs[PROBLEM]);
            int level = Integer.parseInt(inputs[LEVEL]);
            problems.add(new Problem(number, level));
            PROBLEMS[number] = level;
        }

        StringBuilder answer = new StringBuilder();
        int m = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            String operator = stringTokenizer.nextToken();
            if (operator.equals(RECOMMEND)) {
                int x = Integer.parseInt(stringTokenizer.nextToken());
                if (x == 1) {
                    answer.append(problems.last().number);
                } else {
                    answer.append(problems.first().number);
                }
                answer.append("\n");
            } else if (operator.equals(ADD)) {
                int number = Integer.parseInt(stringTokenizer.nextToken());
                int level = Integer.parseInt(stringTokenizer.nextToken());
                problems.add(new Problem(number, level));
                PROBLEMS[number] = level;
            } else if (operator.equals(SOLVED)) {
                int number = Integer.parseInt(stringTokenizer.nextToken());
                problems.remove(new Problem(number, PROBLEMS[number]));
            }
        }
        System.out.println(answer);
    }
}
