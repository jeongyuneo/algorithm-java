package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15787 {

    private static final int TRAIN_COUNT = 20;
    private static final int GET_ON = 1;
    private static final int GET_OFF = 2;
    private static final int GO_BACK = 3;
    private static final int PERSON = 1;
    private static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] trains = new int[n][TRAIN_COUNT];
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int command = Integer.parseInt(stringTokenizer.nextToken());
            int order = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int seat = 0;
            if (stringTokenizer.hasMoreTokens()) {
                seat = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            }
            if (command == GET_ON) {
                trains[order][seat] = PERSON;
            } else if (command == GET_OFF) {
                trains[order][seat] = EMPTY;
            } else if (command == GO_BACK) {
                trains[order][TRAIN_COUNT - 1] = EMPTY;
                for (int j = TRAIN_COUNT - 2; j >= 0; j--) {
                    if (trains[order][j] == PERSON) {
                        trains[order][j + 1] = PERSON;
                    }
                    trains[order][j] = EMPTY;
                }
            } else {
                trains[order][0] = EMPTY;
                for (int j = 1; j < TRAIN_COUNT; j++) {
                    if (trains[order][j] == PERSON) {
                        trains[order][j - 1] = PERSON;
                    }
                    trains[order][j] = EMPTY;
                }
            }
        }
        List<int[]> seats = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            boolean isContained = false;
            for (int[] seat : seats) {
                if (Arrays.equals(seat, trains[j])) {
                    isContained = true;
                    break;
                }
            }
            if (!isContained) {
                seats.add(trains[j]);
            }
        }
        System.out.println(seats.size());
    }
}
