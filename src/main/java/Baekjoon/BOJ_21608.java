package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_21608 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] classroom = new int[n][n];
        List[] favorites = new List[n * n + 1];
        for (int i = 0; i < n * n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int student = Integer.parseInt(stringTokenizer.nextToken());
            favorites[student] = new ArrayList<>();
            favorites[student].add(Integer.parseInt(stringTokenizer.nextToken()));
            favorites[student].add(Integer.parseInt(stringTokenizer.nextToken()));
            favorites[student].add(Integer.parseInt(stringTokenizer.nextToken()));
            favorites[student].add(Integer.parseInt(stringTokenizer.nextToken()));

            int preFavorite = 0;
            int preEmpty = 0;
            int preX = n;
            int preY = n;
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (classroom[x][y] == EMPTY) {
                        int favorite = 0;
                        int empty = 0;
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < n && dy >= 0 && dy < n) {
                                if (classroom[dx][dy] == EMPTY) {
                                    ++empty;
                                } else if (favorites[student].contains(classroom[dx][dy])) {
                                    ++favorite;
                                }
                            }
                        }

                        if (preFavorite < favorite) {
                            preFavorite = favorite;
                            preEmpty = empty;
                            preX = x;
                            preY = y;
                        } else if (preFavorite == favorite && preEmpty < empty) {
                            preEmpty = empty;
                            preX = x;
                            preY = y;
                        } else if (preFavorite == favorite && preEmpty == empty && preX > x) {
                            preX = x;
                            preY = y;
                        } else if (preFavorite == favorite && preEmpty == empty && preX == x && preY > y) {
                            preY = y;
                        }
                    }
                }
            }
            classroom[preX][preY] = student;
        }

        int sum = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                int favorite = 0;
                for (int[] delta : DELTAS) {
                    int dx = x + delta[0];
                    int dy = y + delta[1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < n && favorites[classroom[x][y]].contains(classroom[dx][dy])) {
                        ++favorite;
                    }
                }
                if (favorite != 0) {
                    sum += Math.pow(10, favorite - 1);
                }
            }
        }
        System.out.println(sum);
    }
}
