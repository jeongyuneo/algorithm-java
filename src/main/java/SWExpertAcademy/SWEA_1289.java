package SWExpertAcademy;

import java.util.Scanner;

public class SWEA_1289 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = scanner.nextInt();
        for (int i = 1; i <= testCase; i++) {
            char[] initialMemory = scanner.next().toCharArray();

            int count = 0;
            if (initialMemory[0] == '1') {
                count++;
            }
            for (int j = 1; j < initialMemory.length; j++) {
                if (initialMemory[j] != initialMemory[j-1]) {
                    count++;
                }
            }
            System.out.printf("#%d %d\n", i, count);
        }
    }
}
