package SWExpertAcademy;

import java.util.Scanner;

public class SWEA_1289 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = scanner.nextInt();
        for (int i = 1; i <= testCase; i++) {
            char[] initialMemory = scanner.next().toCharArray();
            int memoryLength = initialMemory.length;

            int count = 0;
            int[] currentMemory = new int[memoryLength];
            for (int j = 0; j < memoryLength; j++) {
                if (initialMemory[j]-48 != currentMemory[j]) {
                    for (int k = j; k < memoryLength; k++) {
                        currentMemory[k] = initialMemory[j]-48;
                    }
                    count++;
                }
            }
            System.out.printf("#%d %d\n", i, count);
        }
    }
}
