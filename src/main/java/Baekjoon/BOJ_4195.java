package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_4195 {

    private static final StringBuilder ANSWER = new StringBuilder();
    private static final Map<String, Integer> FRIENDS = new HashMap<>();

    private static int[] roots;
    private static int[] friends;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        while (testCase-- > 0) {
            FRIENDS.clear();
            int relationShipCount = Integer.parseInt(bufferedReader.readLine());
            roots = new int[relationShipCount * 2];
            friends = new int[relationShipCount * 2];
            int root = 0;
            while (relationShipCount-- > 0) {
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                String first = stringTokenizer.nextToken();
                String second = stringTokenizer.nextToken();
                if (!FRIENDS.containsKey(first)) {
                    FRIENDS.put(first, root);
                    roots[root] = root;
                    friends[root++]++;
                }
                if (!FRIENDS.containsKey(second)) {
                    FRIENDS.put(second, root);
                    roots[root] = root;
                    friends[root++]++;
                }
                join(FRIENDS.get(first), FRIENDS.get(second));
            }
        }
        System.out.println(ANSWER);
    }

    private static void join(int first, int second) {
        int firstRoot = find(first);
        int secondRoot = find(second);
        if (firstRoot != secondRoot) {
            roots[secondRoot] = firstRoot;
            friends[firstRoot] += friends[secondRoot];
        }
        ANSWER.append(friends[firstRoot]).append("\n");
    }

    private static int find(int element) {
        if (roots[element] == element) {
            return element;
        }
        return roots[element] = find(roots[element]);
    }
}
