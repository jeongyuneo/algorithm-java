package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1210 {

    private static final int TOP = -1;
    private static final int[] RIGHT_AND_LEFT = {1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        int n = 100;
        for (int t = 1; t <= 10; t++) {
            bufferedReader.readLine();
            int[][] space = new int[n][n];
            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < n; j++) {
                    space[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            List<Integer> legs = new ArrayList<>();
            int y = n - 1;
            for (int i = 0; i < n; i++) {
                if (space[99][i] == 2) {
                    y = i;
                }
                if (space[0][i] == 1) {
                    legs.add(i);
                }
            }
            int x = n - 2;
            while (x > 0) {
                if (y + RIGHT_AND_LEFT[0] >= 0 && y + RIGHT_AND_LEFT[0] < n && space[x][y + RIGHT_AND_LEFT[0]] == 1) {
                    for (int i = 0; i < legs.size(); i++) {
                        if (legs.get(i) == y) {
                            y = legs.get(i + 1);
                            break;
                        }
                    }
                } else if (y + RIGHT_AND_LEFT[1] >= 0 && y + RIGHT_AND_LEFT[1] < n && space[x][y + RIGHT_AND_LEFT[1]] == 1) {
                    for (int i = 0; i < legs.size(); i++) {
                        if (legs.get(i) == y) {
                            y = legs.get(i - 1);
                            break;
                        }
                    }
                }
                x += TOP;
                stringBuilder.append("#" + t + " " + y + "\n");
            }
            System.out.println(stringBuilder);
        }
    }
}
