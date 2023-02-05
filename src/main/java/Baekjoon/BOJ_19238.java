package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_19238 {

    private static final int[][] DELTAS = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
    private static final Queue<int[]> TAXI = new ArrayDeque<>();
    private static final PriorityQueue<int[]> PASSENGERS = new PriorityQueue<>((p1, p2) -> {
        if (p1[0] == p2[0]) {
            return p1[1] - p2[1];
        }
        return p1[0] - p2[0];
    });
    private static final int WALL = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int fuel = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][n];
        boolean[][] isVisited = new boolean[n][n];
        int[][] destinations = new int[m][2];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken()) * WALL;
            }
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int taxiX = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int taxiY = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        TAXI.offer(new int[]{taxiX, taxiY});
        isVisited[taxiX][taxiY] = true;
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            map[Integer.parseInt(stringTokenizer.nextToken()) - 1][Integer.parseInt(stringTokenizer.nextToken()) - 1] = i + 1;
            destinations[i][0] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            destinations[i][1] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        }
        boolean hasPassenger = false;
        int destination = 0;
        driving:
        while (m > 0 && fuel >= 0) {
            int pass = -1;
            while (!TAXI.isEmpty()) {
                pass++;
                int size = TAXI.size();
                while (size-- > 0) {
                    int[] current = TAXI.poll();
                    int x = current[0];
                    int y = current[1];
                    if (!hasPassenger && map[x][y] > 0 && map[x][y] != WALL) {
                        PASSENGERS.offer(new int[]{x, y});
                        int[] passenger = PASSENGERS.poll();
                        int passengerX = passenger[0];
                        int passengerY = passenger[1];
                        TAXI.clear();
                        TAXI.offer(new int[]{passengerX, passengerY});
                        hasPassenger = true;
                        destination = map[passengerX][passengerY] - 1;
                        map[passengerX][passengerY] = 0;
                        for (int i = 0; i < n; i++) {
                            Arrays.fill(isVisited[i], false);
                        }
                        isVisited[passengerX][passengerY] = true;
                        PASSENGERS.clear();
                        continue driving;
                    }
                    if (hasPassenger && destinations[destination][0] == x && destinations[destination][1] == y) {
                        TAXI.clear();
                        TAXI.offer(new int[]{x, y});
                        hasPassenger = false;
                        destination = 0;
                        for (int i = 0; i < n; i++) {
                            Arrays.fill(isVisited[i], false);
                        }
                        isVisited[x][y] = true;
                        fuel += pass * 2;
                        m--;
                        continue driving;
                    }
                    for (int[] delta : DELTAS) {
                        int dx = x + delta[0];
                        int dy = y + delta[1];
                        if (dx >= 0 && dx < n && dy >= 0 && dy < n && !isVisited[dx][dy] && map[dx][dy] != WALL) {
                            if (!hasPassenger && map[dx][dy] > 0) {
                                PASSENGERS.offer(new int[]{dx, dy});
                            }
                            isVisited[dx][dy] = true;
                            TAXI.offer(new int[]{dx, dy});
                        }
                    }
                }
                if (--fuel < 0) {
                    break driving;
                }
                if (!PASSENGERS.isEmpty()) {
                    int[] passenger = PASSENGERS.poll();
                    int passengerX = passenger[0];
                    int passengerY = passenger[1];
                    TAXI.clear();
                    TAXI.offer(new int[]{passengerX, passengerY});
                    hasPassenger = true;
                    destination = map[passengerX][passengerY] - 1;
                    map[passengerX][passengerY] = 0;
                    for (int i = 0; i < n; i++) {
                        Arrays.fill(isVisited[i], false);
                    }
                    isVisited[passengerX][passengerY] = true;
                    PASSENGERS.clear();
                    continue driving;
                }
            }
            fuel = -1;
            break;
        }
        System.out.println(fuel);
    }
}
