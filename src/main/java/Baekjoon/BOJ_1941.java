package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ_1941 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final Queue<Integer> QUEUE = new ArrayDeque<>();
    private static final int SIZE = 5;
    private static final int GROUP_NUMBER = 7;
    private static final char[][] CLASSROOM = new char[SIZE][SIZE];
    private static final boolean[] IS_VISITED = new boolean[SIZE * SIZE];
    private static final boolean[] IS_MEMBER = new boolean[SIZE * SIZE];
    private static final int[] SELECTED_STUDENTS = new int[GROUP_NUMBER];
    private static final char DASOM = 'S';

    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < SIZE; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < SIZE; j++) {
                CLASSROOM[i][j] = line.charAt(j);
            }
        }

        selectSevenStudents(0, 0);
        System.out.println(answer);
    }

    private static void selectSevenStudents(int cnt, int number) {
        if (cnt == GROUP_NUMBER) {
            checkAdjacentAndDasoms();
            return;
        }
        if (number == SIZE * SIZE) {
            return;
        }
        SELECTED_STUDENTS[cnt] = number;
        selectSevenStudents(cnt + 1, number + 1);
        selectSevenStudents(cnt, number + 1);
    }

    private static void checkAdjacentAndDasoms() {
        initialize();
        for (int selectedStudent : SELECTED_STUDENTS) {
            IS_MEMBER[selectedStudent] = true;
        }
        int cnt = 1;
        int dasoms = 0;
        QUEUE.offer(SELECTED_STUDENTS[0]);
        IS_VISITED[SELECTED_STUDENTS[0]] = true;
        while (!QUEUE.isEmpty()) {
            int current = QUEUE.poll();
            int x = current / SIZE;
            int y = current % SIZE;
            if (CLASSROOM[x][y] == DASOM) {
                dasoms++;
            }
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                int next = dx * SIZE + dy;
                if (dx >= 0 && dx < SIZE && dy >= 0 && dy < SIZE && IS_MEMBER[next] && !IS_VISITED[next]) {
                    cnt++;
                    IS_VISITED[next] = true;
                    QUEUE.offer(next);
                }
            }
        }
        if (cnt == 7 && dasoms > GROUP_NUMBER / 2) {
            answer++;
        }
    }

    private static void initialize() {
        Arrays.fill(IS_VISITED, false);
        Arrays.fill(IS_MEMBER, false);
    }
}
