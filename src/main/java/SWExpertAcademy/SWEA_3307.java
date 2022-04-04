package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

            int[] table = new int[n];
            int size = 0;
            for (int i = 0; i < n; i++) {
                int insertIndex = Arrays.binarySearch(table, 0, size, array[i]);
                insertIndex = Math.abs(insertIndex) - 1;
                table[insertIndex] = array[i];

                if (size == insertIndex) {
                    size++;
                }
            }

            stringBuilder.append("#")
                    .append(t)
                    .append(" ")
                    .append(size)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }
}
