package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class BOJ_7662 {

    private static final TreeMap<Integer, Integer> NUMBERS = new TreeMap<>();
    private static final String DELIMITER = " ";
    private static final String INSERT = "I";
    private static final int NUMBER = 1;
    private static final int MAX_VALUE_DELETE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (testCase-- > 0) {
            int k = Integer.parseInt(bufferedReader.readLine());
            while (k-- > 0) {
                String operation = bufferedReader.readLine();
                int number = Integer.parseInt(operation.split(DELIMITER)[NUMBER]);
                if (operation.startsWith(INSERT)) {
                    NUMBERS.put(number, NUMBERS.getOrDefault(number, 0) + 1);
                } else {
                    if (NUMBERS.isEmpty()) {
                        continue;
                    }
                    int key;
                    if (number == MAX_VALUE_DELETE) {
                        key = NUMBERS.lastKey();
                    } else {
                        key = NUMBERS.firstKey();
                    }
                    int count = NUMBERS.get(key);
                    if (count == 1) {
                        NUMBERS.remove(key);
                    } else {
                        NUMBERS.put(key, count - 1);
                    }
                }
            }
            if (NUMBERS.isEmpty()) {
                answer.append("EMPTY");
            } else {
                answer.append(NUMBERS.lastKey()).append(DELIMITER).append(NUMBERS.firstKey());
            }
            answer.append("\n");
            NUMBERS.clear();
        }
        System.out.println(answer);
    }
}
