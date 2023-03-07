package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1253 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Map<Integer, Integer> checkedNumbers = new HashMap<>();
        Arrays.sort(numbers);
        int count = 0;
        for (int i = 0; i < n; i++) {
            int number = numbers[i];
            if (checkedNumbers.containsKey(number)) {
                count += checkedNumbers.get(number);
                continue;
            }
            int start = 0;
            int end = n - 1;
            while (start < end) {
                if (start == i) {
                    start++;
                    continue;
                }
                if (end == i) {
                    end--;
                    continue;
                }
                int sum = numbers[start] + numbers[end];
                if (sum > number) {
                    end--;
                } else if (sum < number) {
                    start++;
                } else {
                    count++;
                    checkedNumbers.put(number, 1);
                    break;
                }
            }
            checkedNumbers.putIfAbsent(number, 0);
        }
        System.out.println(count);
    }
}
