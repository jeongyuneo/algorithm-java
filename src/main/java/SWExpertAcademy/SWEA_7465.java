package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_7465 {

    private static int[] people;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int testCase = Integer.parseInt(bufferedReader.readLine());
        for (int t = 1; t <= testCase; t++) {
            stringBuilder.append("#")
                    .append(t)
                    .append(" ");
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            makeSet(n);

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int firstPerson = Integer.parseInt(stringTokenizer.nextToken());
                int secondPerson = Integer.parseInt(stringTokenizer.nextToken());
                union(firstPerson, secondPerson);
            }

            for (int i = 1; i <= n; i++) {
                findSet(i);
            }

            int group = (int) Arrays.stream(people)
                    .filter(person -> person != 0)
                    .distinct()
                    .count();

            stringBuilder.append(group)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void makeSet(int n) {
        people = new int[n+1];
        for (int i = 1; i <= n; i++) {
            people[i] = i;
        }
    }

    private static int findSet(int x) {
        if (x == people[x]) {
            return x;
        }
        return people[x] = findSet(people[x]);
    }

    private static void union(int x, int y) {
        int xRoot = findSet(x);
        int yRoot = findSet(y);
        if (xRoot == yRoot) {
            return;
        }
        people[xRoot] = people[yRoot];
    }
}
