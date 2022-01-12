package Baekjoon;

import java.util.Scanner;

public class BOJ_2884 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hour = scanner.nextInt();
        int minute = scanner.nextInt();
        if (minute - 45 < 0) {
            hour--;
            minute += 15;
        } else {
            minute -= 45;
        }
        if (hour < 0) {
            hour += 24;
        }
        System.out.println(hour + " " + minute);
    }
}
