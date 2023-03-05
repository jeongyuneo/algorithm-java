package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2239 {

    private static final int SIZE = 9;
    private static final int SMALL_SQUARE_SIZE = 3;
    private static final int BLANK = 0;
    private static final int[][] SUDOKU = new int[SIZE][SIZE];
    private static final List<Integer> BLANKS = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < SIZE; i++) {
            String input = bufferedReader.readLine();
            for (int j = 0; j < SIZE; j++) {
                int inputNumber = input.charAt(j) - 48;
                SUDOKU[i][j] = inputNumber;
                if (inputNumber == BLANK) {
                    BLANKS.add(i * SIZE + j);
                }
            }
        }
        playSudoku(0);
    }

    private static void playSudoku(int cnt) {
        if (cnt == BLANKS.size()) {
            StringBuilder answer = new StringBuilder();
            for (int[] line : SUDOKU) {
                for (int number : line) {
                    answer.append(number);
                }
                answer.append("\n");
            }
            System.out.println(answer);
            System.exit(0);
        }
        int blank = BLANKS.get(cnt);
        int x = blank / SIZE;
        int y = blank % SIZE;
        for (int i = 1; i <= SIZE; i++) {
            if (!hasNumber(x, y, i)) {
                SUDOKU[x][y] = i;
                playSudoku(cnt + 1);
                SUDOKU[x][y] = 0;
            }
        }
    }

    private static boolean hasNumber(int x, int y, int number) {
        boolean[] hasNumber = new boolean[10];
        for (int i = 0; i < SIZE; i++) {
            hasNumber[SUDOKU[x][i]] = true;
            hasNumber[SUDOKU[i][y]] = true;
        }
        int startX = (x / SMALL_SQUARE_SIZE) * SMALL_SQUARE_SIZE;
        int startY = (y / SMALL_SQUARE_SIZE) * SMALL_SQUARE_SIZE;
        for (int i = startX; i < startX + SMALL_SQUARE_SIZE; i++) {
            for (int j = startY; j < startY + SMALL_SQUARE_SIZE; j++) {
                hasNumber[SUDOKU[i][j]] = true;
            }
        }
        return hasNumber[number];
    }
}
