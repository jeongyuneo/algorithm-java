package Programmers;

public class Programmers_그래프_Level3_순위 {

    public static void main(String[] args) {
        System.out.println(solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
    }

    public static int solution(int n, int[][] results) {
        boolean[][] map = new boolean[n][n];
        for (int[] result : results) {
            map[result[0] - 1][result[1] - 1] = true;
        }
        for (int pass = 0; pass < n; pass++) {
            for (int start = 0; start < n; start++) {
                for (int end = 0; end < n; end++) {
                    if (map[start][pass] && map[pass][end]) {
                        map[start][end] = true;
                    }
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int compare = 0;
            for (int j = 0; j < n; j++) {
                if (map[i][j] || map[j][i]) {
                    compare++;
                }
            }
            if (compare == n - 1) {
                answer++;
            }
        }
        return answer;
    }
}
