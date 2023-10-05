package Programmers;

import java.util.HashSet;
import java.util.Set;

public class Programmers_깊이너비우선탐색_Level3_네트워크 {

    public static void main(String[] args) {
        System.out.println(solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
        System.out.println(solution(5, new int[][]{{1, 0, 0, 0, 1}, {0, 1, 1, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 1, 1, 1}, {1, 0, 0, 1, 1}}));
    }

    private static int[] roots;

    public static int solution(int n, int[][] computers) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
        for (int start = 0; start < n; start++) {
            for (int end = 0; end < n; end++) {
                if (start == end || computers[start][end] == 0) {
                    continue;
                }
                union(start, end);
            }
        }
        Set<Integer> networks = new HashSet<>();
        for (int root : roots) {
            networks.add(find(root));
        }
        return networks.size();
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA < rootB) {
            roots[rootB] = rootA;
        } else if (rootA > rootB) {
            roots[rootA] = rootB;
        }
    }

    private static int find(int value) {
        if (value == roots[value]) {
            return value;
        }
        return roots[value] = find(roots[value]);
    }
}
