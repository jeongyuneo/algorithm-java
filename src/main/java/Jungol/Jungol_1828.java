package Jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Jungol_1828 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] temperatureInfos = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int minimumTemperature = Integer.parseInt(stringTokenizer.nextToken());
            int maximumTemperature = Integer.parseInt(stringTokenizer.nextToken());
            temperatureInfos[i] = new int[]{minimumTemperature, maximumTemperature};
        }

        Arrays.sort(temperatureInfos, Comparator.comparing(temp -> temp[1]));

        int minimumTemperature = temperatureInfos[0][1];
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if (temperatureInfos[i][0] > minimumTemperature) {
                minimumTemperature = temperatureInfos[i][1];
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
