package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_9229 {

    private static final BufferedReader BUFFEREDREADER = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer stringTokenizer;
    private static Stack<Integer> stack;
    private static int[] snacks;
    private static int snackNum;
    private static int limit;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(BUFFEREDREADER.readLine());
        for (int t = 1; t <= testCase; t++) {
            stringTokenizer = new StringTokenizer(BUFFEREDREADER.readLine());

            snackNum = Integer.parseInt(stringTokenizer.nextToken());
            limit = Integer.parseInt(stringTokenizer.nextToken());

            stringTokenizer = new StringTokenizer(BUFFEREDREADER.readLine());
            snacks = new int[snackNum];
            for (int i = 0; i < snackNum; i++) {
                snacks[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            int totalWeight = -1;
            stack = new Stack<>();
            all: for (int i = 0; i < snackNum-1; i++) {
                for (int j = i+1; j < snackNum; j++) {
                    int sum = snacks[i] + snacks[j];
                    if (sum == limit) {
                        totalWeight = sum;
                        break all;
                    }
                    if (sum <= limit) {
                        if (!stack.isEmpty()) {
                            totalWeight = Math.max(stack.pop(), sum);
                        } else {
                            totalWeight = sum;
                        }
                        stack.push(totalWeight);
                    }
                }
            }
            System.out.printf("#%d %d\n", t, totalWeight);
        }
    }
}
