package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1210 {

    private static final int TOP = -1;
    private static final int RIGHT = 1;
    private static final int LEFT = -1;

    private static List<Integer> legs;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        int n = 100;
        for (int t = 1; t <= 10; t++) {
            bufferedReader.readLine();
            int[][] space = new int[n][n];
            int y = n-1;
            legs = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < n; j++) {
                    space[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    if (space[i][j] == 2) {
                        y = j;
                    }
                    if (i == 0 && space[i][j] == 1) {
                        legs.add(j);
                    }
                }
            }
            int x = n-2;
            while (x > 0) {
                if (y + RIGHT >= 0 && y + RIGHT < n && space[x][y + RIGHT] == 1) {
                    y = nextY(y, RIGHT);
                } else if (y + LEFT >= 0 && y + LEFT < n && space[x][y + LEFT] == 1) {
                    y = nextY(y, LEFT);
                }
                x += TOP;
            }
            stringBuilder.append("#" + t + " " + y + "\n");
        }
        System.out.println(stringBuilder);
    }

    private static int nextY(int y, int direction) {
        for (int i = 0; i < legs.size(); i++) {
            if (legs.get(i) == y) {
                return legs.get(i + direction);
            }
        }
        return 0;
    }
}
