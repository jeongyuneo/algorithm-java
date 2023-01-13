package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17135 {

    static class Enemy {

        int number;
        int x;
        int y;
        public Enemy(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Enemy enemy = (Enemy) o;
            return number == enemy.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }
    }

    private static final int ENEMY = 1;
    private static final int ARCHER_NUMBER = 3;
    private static final int EMPTY = -1;
    private static final int[] ARCHERS = new int[ARCHER_NUMBER];
    private static final List<Enemy> ENEMIES = new ArrayList<>();
    private static final Set<Enemy> ATTACKS = new HashSet<>();

    private static int n;
    private static int m;
    private static int attackableDistance;
    private static int maxAttackableEnemies;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        attackableDistance = Integer.parseInt(stringTokenizer.nextToken());
        int count = 0;
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                if (Integer.parseInt(stringTokenizer.nextToken()) == ENEMY) {
                    ENEMIES.add(new Enemy(count++, i, j));
                }
            }
        }
        setArchers(0, 0);
        System.out.println(maxAttackableEnemies);
    }

    private static void setArchers(int cnt, int start) {
        if (cnt == ARCHER_NUMBER) {
            playGame();
            return;
        }

        for (int i = start; i < m; i++) {
            ARCHERS[cnt] = i;
            setArchers(cnt + 1, i + 1);
        }
    }

    private static void playGame() {
        List<Enemy> enemies = copyEnemies();
        int attackableEnemies = 0;
        while (!enemies.isEmpty()) {
            ATTACKS.clear();
            for (int archer : ARCHERS) {
                Enemy attackingEnemy = new Enemy(EMPTY, EMPTY, m);
                int minDistance = Integer.MAX_VALUE;
                for (Enemy enemy : enemies) {
                    int distance = getDistance(n, archer, enemy.x, enemy.y);
                    if (distance > attackableDistance) {
                        continue;
                    }
                    if (minDistance > distance) {
                        minDistance = distance;
                        attackingEnemy = enemy;
                    } else if (minDistance == distance && attackingEnemy.y > enemy.y) {
                        attackingEnemy = enemy;
                    }
                }
                if (attackingEnemy.number != EMPTY) {
                    ATTACKS.add(attackingEnemy);
                }
            }
            attackableEnemies += ATTACKS.size();
            enemies.removeAll(ATTACKS);
            move(enemies);
        }
        maxAttackableEnemies = Math.max(maxAttackableEnemies, attackableEnemies);
    }

    private static List<Enemy> copyEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        for (Enemy enemy : ENEMIES) {
            enemies.add(new Enemy(enemy.number, enemy.x, enemy.y));
        }
        return enemies;
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static void move(List<Enemy> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            if (++enemies.get(i).x >= n) {
                enemies.remove(i--);
            }
        }
    }
}
