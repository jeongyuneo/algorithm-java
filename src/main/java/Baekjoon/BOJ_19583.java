package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_19583 {

    private static final String DELIMITER = ":";
    private static final int HOUR = 0;
    private static final int MINUTE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        String[] start = stringTokenizer.nextToken().split(DELIMITER);
        String[] end = stringTokenizer.nextToken().split(DELIMITER);
        String[] endOfStreaming = stringTokenizer.nextToken().split(DELIMITER);
        LocalTime startTime = LocalTime.of(Integer.parseInt(start[HOUR]), Integer.parseInt(start[MINUTE]));
        LocalTime endTime = LocalTime.of(Integer.parseInt(end[HOUR]), Integer.parseInt(end[MINUTE]));
        LocalTime endOfStreamingTime = LocalTime.of(Integer.parseInt(endOfStreaming[HOUR]), Integer.parseInt(endOfStreaming[MINUTE]));
        Set<String> chatMembers = new HashSet<>();
        Set<String> members = new HashSet<>();
        while (true) {
            String input = bufferedReader.readLine();
            if (input == null) {
                break;
            }
            stringTokenizer = new StringTokenizer(input);
            String[] chat = stringTokenizer.nextToken().split(DELIMITER);
            String nickname = stringTokenizer.nextToken();
            LocalTime chatTime = LocalTime.of(Integer.parseInt(chat[HOUR]), Integer.parseInt(chat[MINUTE]));
            if (chatTime.isBefore(startTime.plusMinutes(1))) {
                chatMembers.add(nickname);
            }
            if (chatTime.isAfter(endTime.minusMinutes(1)) && chatTime.isBefore(endOfStreamingTime.plusMinutes(1))) {
                members.add(nickname);
            }
        }
        int answer = 0;
        for (String member : members) {
            if (chatMembers.contains(member)) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
