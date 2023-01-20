package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_18405 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        List<int[]>[] viruses = new List[k];
        for (int i = 0; i < k; i++) {
            viruses[i] = new ArrayList<>();
        }
        int[][] map = new int[n][n];
        boolean[][] isVisited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                map[i][j] = input;
                if (input != EMPTY) {
                    viruses[input - 1].add(new int[]{i, j});
                }
            }
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int s = Integer.parseInt(stringTokenizer.nextToken());
        int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        while (s-- > 0) {
            for (int i = 0; i < k; i++) {
                List<int[]> spreadingViruses = new ArrayList<>(viruses[i]);
                viruses[i].clear();
                for (int[] spreadingVirus : spreadingViruses) {
                    int r = spreadingVirus[0];
                    int c = spreadingVirus[1];
                    isVisited[r][c] = true;
                    for (int[] delta : DELTAS) {
                        int dr = r + delta[0];
                        int dc = c + delta[1];
                        if (dr >= 0 && dr < n && dc >= 0 && dc < n && !isVisited[dr][dc] && map[dr][dc] == EMPTY) {
                            isVisited[dr][dc] = true;
                            map[dr][dc] = map[r][c];
                            viruses[i].add(new int[]{dr, dc});
                        }
                    }
                }
            }
        }
        System.out.println(map[x][y]);
    }
}
