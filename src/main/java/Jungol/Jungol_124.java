package Jungol;

import java.util.Scanner;

public class Jungol_124 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (number == 2) {
            System.out.println(28);
        } else if (number < 8 && number % 2 == 1 || number >= 8 && number % 2 == 0) {
            System.out.println(31);
        } else {
            System.out.println(30);
        }
    }
}
