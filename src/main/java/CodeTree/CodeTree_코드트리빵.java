package CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CodeTree_코드트리빵 {

    private static final int[][] DELTAS = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    private static final int EMPTY = 0;
    private static final int BASECAMP = 1;
    private static final int MOVE = 2;
    private static final PriorityQueue<int[]> NEXT_MOVES = new PriorityQueue<>(Comparator.comparing(person -> person[MOVE]));
    private static final PriorityQueue<int[]> MOVES = new PriorityQueue<>((p1, p2) -> {
        if (p1[MOVE] == p2[MOVE]) {
            if (p1[0] == p2[0]) {
                return p1[1] - p2[1];
            }
            return p1[0] - p2[0];
        }
        return p1[MOVE] - p2[MOVE];
    });

    private static int[][] map;
    private static int[][] convenienceStores;
    private static boolean[][] isVisited;
    private static PriorityQueue<int[]>[] people;
    private static int n;
    private static int m;
    private static int arrive;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[n][n];
        isVisited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        convenienceStores = new int[m + 1][2];
        people = new PriorityQueue[m + 1];
        for (int i = 1; i <= m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            convenienceStores[i][0] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            convenienceStores[i][1] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            people[i] = new PriorityQueue<>(Comparator.comparing(person -> person[MOVE]));
        }
        int time = 0;
        while (arrive < m) {
            time++;
            moveToConvenienceStore();
            if (time <= m) {
                moveToBasecamp(time);
            }
        }
        System.out.println(time);
    }

    private static void moveToConvenienceStore() {
        for (int i = 1; i <= m; i++) {
            while (!people[i].isEmpty()) {
                int[] current = people[i].poll();
                int x = current[0];
                int y = current[1];
                for (int[] delta : DELTAS) {
                    int dx = x + delta[0];
                    int dy = y + delta[1];
                    if (isInRange(dx, dy) && map[dx][dy] >= EMPTY) {
                        if (convenienceStores[i][0] == dx && convenienceStores[i][1] == dy) {
                            arrive++;
                            map[dx][dy] = -i;
                            people[i].clear();
                            NEXT_MOVES.clear();
                            break;
                        }
                        NEXT_MOVES.offer(new int[]{dx, dy, current[MOVE] + 1});
                    }
                }
            }
            people[i].addAll(NEXT_MOVES);
            NEXT_MOVES.clear();
        }
    }

    private static void moveToBasecamp(int time) {
        MOVES.offer(new int[]{convenienceStores[time][0], convenienceStores[time][1], 0});
        for (int i = 0; i < n; i++) {
            Arrays.fill(isVisited[i], false);
        }
        while (!MOVES.isEmpty()) {
            int[] current = MOVES.poll();
            int x = current[0];
            int y = current[1];
            if (map[x][y] == BASECAMP) {
                map[x][y] = -time;
                people[time].offer(new int[]{x, y, 0});
                MOVES.clear();
                return;
            }
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (isInRange(dx, dy) && !isVisited[dx][dy] && map[dx][dy] >= EMPTY) {
                    isVisited[dx][dy] = true;
                    MOVES.offer(new int[]{dx, dy, current[MOVE] + 1});
                }
            }
        }
    }

    private static boolean isInRange(int dx, int dy) {
        return dx >= 0 && dx < n && dy >= 0 && dy < n;
    }
}
