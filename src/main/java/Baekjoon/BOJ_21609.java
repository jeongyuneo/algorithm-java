package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21609 {

    static class BlockGroup {

        int x;
        int y;
        int size;
        int rainbowNum;
        List<int[]> blocks;

        public BlockGroup(int x, int y) {
            this.x = x;
            this.y = y;
            size = 0;
            rainbowNum = 0;
            blocks = new ArrayList<>();
        }
    }

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int EMPTY = 10;
    private static final int BLACK = -1;
    private static final int RAINBOW = 0;

    private static int[][] map;
    private static int n;
    private static BlockGroup maxBlockGroup;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer.nextToken();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int score = 0;
        while (hasBlockGroup()) {
            for (int[] block : maxBlockGroup.blocks) {
                map[block[0]][block[1]] = EMPTY;
            }
            score += Math.pow(maxBlockGroup.size, 2);
            drop();
            map = rotate();
            drop();
        }
        System.out.println(score);
    }

    private static int[][] rotate() {
        int[][] newMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i][j] = map[j][n - 1 - i];
            }
        }
        return newMap;
    }

    private static void drop() {
        for (int j = 0; j < n; j++) {
            int bottom = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                if (map[i][j] == BLACK) {
                    bottom = i - 1;
                } else if (map[i][j] != EMPTY) {
                    int block = map[i][j];
                    map[i][j] = EMPTY;
                    map[bottom--][j] = block;
                }
            }
        }
    }

    private static boolean hasBlockGroup() {
        maxBlockGroup = new BlockGroup(0, 0);
        boolean[][] isVisited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isVisited[i][j] && map[i][j] != EMPTY && map[i][j] > RAINBOW) {
                    int generalBlock = map[i][j];
                    BlockGroup blockGroup = new BlockGroup(i, j);
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];
                        isVisited[x][y] = true;
                        blockGroup.size++;
                        blockGroup.blocks.add(current);
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < n && dy >= 0 && dy < n && !isVisited[dx][dy] && (map[dx][dy] == generalBlock || map[dx][dy] == RAINBOW)) {
                                isVisited[dx][dy] = true;
                                queue.offer(new int[]{dx, dy});
                                if (map[dx][dy] == RAINBOW) {
                                    blockGroup.rainbowNum++;
                                }
                            }
                        }
                    }

                    if (blockGroup.size < 2) {
                        continue;
                    }

                    for (int[] block : blockGroup.blocks) {
                        if (map[block[0]][block[1]] == RAINBOW) {
                            isVisited[block[0]][block[1]] = false;
                        }
                    }

                    if (maxBlockGroup.size < blockGroup.size) {
                        maxBlockGroup.x = blockGroup.x;
                        maxBlockGroup.y = blockGroup.y;
                        maxBlockGroup.size = blockGroup.size;
                        maxBlockGroup.rainbowNum = blockGroup.rainbowNum;
                        maxBlockGroup.blocks = blockGroup.blocks;
                    } else if (maxBlockGroup.size == blockGroup.size && maxBlockGroup.rainbowNum < blockGroup.rainbowNum) {
                        maxBlockGroup.x = blockGroup.x;
                        maxBlockGroup.y = blockGroup.y;
                        maxBlockGroup.rainbowNum = blockGroup.rainbowNum;
                        maxBlockGroup.blocks = blockGroup.blocks;
                    } else if (maxBlockGroup.size == blockGroup.size && maxBlockGroup.rainbowNum == blockGroup.rainbowNum && maxBlockGroup.x < blockGroup.x) {
                        maxBlockGroup.x = blockGroup.x;
                        maxBlockGroup.y = blockGroup.y;
                        maxBlockGroup.blocks = blockGroup.blocks;
                    } else if (maxBlockGroup.size == blockGroup.size && maxBlockGroup.rainbowNum == blockGroup.rainbowNum && maxBlockGroup.x == blockGroup.x && maxBlockGroup.y < blockGroup.y) {
                        maxBlockGroup.y = blockGroup.y;
                        maxBlockGroup.blocks = blockGroup.blocks;
                    }
                }
            }
        }
        return maxBlockGroup.size != 0;
    }
}
