package Baekjoon;

import java.util.Scanner;

public class BOJ_1244 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int switchNum = scanner.nextInt();
        int[] switchs = new int[switchNum+1];
        for (int i = 1; i <= switchNum; i++) {
            switchs[i] = scanner.nextInt();
        }
        int studentNum = scanner.nextInt();
        for (int i = 0; i < studentNum; i++) {
            int gender = scanner.nextInt();
            int givenNumber = scanner.nextInt();
            if (gender == 1) {
                int multipleNum = 2;
                int idx;
                while ((idx = givenNumber * multipleNum) <= switchNum) {
                    switchs[idx] = Math.abs(switchs[idx] - 1);
                    multipleNum += 1;
                }
            } else {
                int preIdx = givenNumber-1;
                int nextIdx = givenNumber+1;
                while (preIdx > 0 && nextIdx <= switchNum) {
                    if (switchs[preIdx] == switchs[nextIdx]) {
                        switchs[preIdx] = Math.abs(switchs[preIdx--] - 1);
                        switchs[nextIdx] = Math.abs(switchs[nextIdx++] - 1);
                    } else {
                        break;
                    }
                }
            }
            switchs[givenNumber] = Math.abs(switchs[givenNumber] - 1);
        }
        for (int i = 1; i <= switchNum; i++) {
            System.out.print(switchs[i] + " ");
            if (i % 20 == 0) {
                System.out.println();
            }
        }
    }
}
