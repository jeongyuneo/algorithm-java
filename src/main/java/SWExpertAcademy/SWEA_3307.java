package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3307 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int testCase = Integer.parseInt(bufferedReader.readLine());
        for (int t = 1; t <= testCase; t++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            int maxLength = 0;
            int[] table = new int[n];
            for (int i = 0; i < n; i++) {
                table[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (array[j] < array[i]) {
                        table[i] = Math.max(table[i], table[j] + 1);
                    }
                }
                maxLength = Math.max(maxLength, table[i]);
            }
            stringBuilder.append("#")
                    .append(t)
                    .append(" ")
                    .append(maxLength)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }
}
