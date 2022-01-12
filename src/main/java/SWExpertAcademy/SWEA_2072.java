package SWExpertAcademy;

import java.util.Scanner;
import java.util.stream.IntStream;

public class SWEA_2072 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println(number);
        for (int i = 1; i <= number; i++) {
            int sum = IntStream.range(0, 10)
                    .map(s -> scanner.nextInt())
                    .filter(num -> num % 2 == 1)
                    .sum();
            System.out.println("#" + i + " " + sum);
        }
    }
}
