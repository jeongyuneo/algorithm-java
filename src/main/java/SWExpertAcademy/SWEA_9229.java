package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_9229 {

    private static final BufferedReader BUFFEREDREADER = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer stringTokenizer;
    private static int[] snacks;
    private static int result;
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
            result = -1;
            find(0, 0, 0);
            System.out.printf("#%d %d\n", t, result);
        }
    }

    private static void find(int cnt, int start, int sum) {
        if (sum > limit) {
            return;
        }
        if (cnt == 2) {
            result = Math.max(sum, result);
            return;
        }
        for (int i = start; i < snackNum; i++) {
            find(cnt+1, i+1, sum+snacks[i]);
        }
    }
}
