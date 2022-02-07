package SWExpertAcademy;

import java.util.Scanner;

public class SWEA_1208 {

    private static int[] boxes = new int[100];
    private static int maxIdx = 0;
    private static int minIdx = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int t = 1; t <= 10; t++) {
            int n = scanner.nextInt();
            for (int i = 0; i < 100; i++) {
                boxes[i] = scanner.nextInt();
            }
            for (int i = 0; i < n; i++) {
                dump();
            }
            findIndexes();
            System.out.printf("#%d %d\n", t, boxes[maxIdx]-boxes[minIdx]);
        }
    }

    private static void dump() {
        findIndexes();
        boxes[maxIdx]--;
        boxes[minIdx]++;
    }

    private static void findIndexes() {
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] > boxes[maxIdx]) {
                maxIdx = i;
            }
            if (boxes[i] < boxes[minIdx]) {
                minIdx = i;
            }
        }
    }
}
