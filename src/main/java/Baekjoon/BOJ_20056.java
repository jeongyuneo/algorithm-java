package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20056 {

    static class FireBall {

        int mass;
        int speed;
        int direction;

        public FireBall(int mass, int speed, int direction) {
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }
    }

    private static int[][] directions;

    public static void main(String[] ars) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        List<FireBall>[][] map = getMap(n);
        directions = new int[][]{{n - 1, 0}, {n - 1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, n - 1}, {0, n - 1}, {n - 1, n - 1}};
        int totalMass = 0;
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int r = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int c = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int mass = Integer.parseInt(stringTokenizer.nextToken());
            int speed = Integer.parseInt(stringTokenizer.nextToken());
            int direction = Integer.parseInt(stringTokenizer.nextToken());
            map[r][c].add(new FireBall(mass, speed, direction));
            totalMass += mass;
        }

        while (k-- > 0) {
            List<FireBall>[][] copiedMap = getMap(n);
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (map[x][y].size() > 0) {
                        List<FireBall> current = map[x][y];
                        for (FireBall fireBall : current) {
                            int dx = (x + directions[fireBall.direction][0] * fireBall.speed) % n;
                            int dy = (y + directions[fireBall.direction][1] * fireBall.speed) % n;
                            copiedMap[dx][dy].add(fireBall);
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (copiedMap[i][j].size() >= 2) {
                        List<FireBall> current = copiedMap[i][j];
                        int mass = 0;
                        int speed = 0;
                        int oddCount = 0;
                        int size = current.size();
                        for (FireBall fireBall : current) {
                            mass += fireBall.mass;
                            speed += fireBall.speed;
                            if (fireBall.direction % 2 == 1) {
                                oddCount++;
                            }
                        }
                        totalMass -= mass;
                        if ((mass /= 5) == 0) {
                            copiedMap[i][j].clear();
                            continue;
                        }
                        totalMass += mass * 4;
                        speed /= size;
                        copiedMap[i][j].clear();
                        if (oddCount == 0 || oddCount == size) {
                            for (int nextDirection = 0; nextDirection <= 6; nextDirection += 2) {
                                copiedMap[i][j].add(new FireBall(mass, speed, nextDirection));
                            }
                        } else {
                            for (int nextDirection = 1; nextDirection <= 7; nextDirection += 2) {
                                copiedMap[i][j].add(new FireBall(mass, speed, nextDirection));
                            }
                        }
                    }
                }
            }
            map = copiedMap;
        }
        System.out.println(totalMass);
    }

    private static List<FireBall>[][] getMap(int n) {
        List<FireBall>[][] map = new List[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        return map;
    }
}
