package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18405 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        Queue<int[]> viruses = new ArrayDeque<>();
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                map[i][j] = input;
                if (input != EMPTY) {
                    viruses.offer(new int[]{i, j, input});
                }
            }
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int s = Integer.parseInt(stringTokenizer.nextToken());
        int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        while (s-- > 0 && map[x][y] == EMPTY) {
            int size = viruses.size();
            while (size-- > 0) {
                int[] current = viruses.poll();
                int r = current[0];
                int c = current[1];
                int type = current[2];
                for (int[] delta : DELTAS) {
                    int dr = r + delta[0];
                    int dc = c + delta[1];
                    if (dr >= 0 && dr < n && dc >= 0 && dc < n && (map[dr][dc] == EMPTY || map[dr][dc] > type)) {
                        map[dr][dc] = type;
                        viruses.offer(new int[]{dr, dc, type});
                    }
                }
            }
        }
        System.out.println(map[x][y]);
    }
}
