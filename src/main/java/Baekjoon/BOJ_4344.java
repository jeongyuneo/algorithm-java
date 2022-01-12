package Baekjoon;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ_4344 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        for (int i = 0; i < number; i++) {
            int count = scanner.nextInt();
            List<Integer> scores = IntStream.range(0, count)
                    .mapToObj(n -> scanner.nextInt())
                    .collect(Collectors.toList());
            double average = scores.stream()
                    .mapToDouble(Double::new)
                    .sum() / count;
            double studentRate = (double) scores.stream()
                    .filter(n -> n > average)
                    .count() / count * 100;
            String result = String.format("%.3f", studentRate);
            System.out.println(result + "%");
        }
    }
}
