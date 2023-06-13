package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_19583 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        String start = stringTokenizer.nextToken();
        String end = stringTokenizer.nextToken();
        String endOfStreaming = stringTokenizer.nextToken();
        Set<String> attendingMembers = new HashSet<>();
        Set<String> remainingMembers = new HashSet<>();
        String input;
        while ((input = bufferedReader.readLine()) != null) {
            stringTokenizer = new StringTokenizer(input);
            String chat = stringTokenizer.nextToken();
            String nickname = stringTokenizer.nextToken();
            if (isBefore(start, chat)) {
                attendingMembers.add(nickname);
            }
            if (isAfter(end, chat) && isBefore(endOfStreaming, chat) && attendingMembers.contains(nickname)) {
                remainingMembers.add(nickname);
            }
        }
        System.out.println(remainingMembers.size());
    }

    private static boolean isBefore(String start, String end) {
        return start.compareTo(end) >= 0;
    }

    private static boolean isAfter(String start, String end) {
        return start.compareTo(end) <= 0;
    }
}
