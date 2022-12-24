package Programmers;

import java.util.*;
import java.util.stream.Collectors;

public class Programmers_2022_Kakao_Level1_신고결과받기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}, 2)));
        System.out.println(Arrays.toString(solution(new String[]{"con", "ryan"}, new String[]{"ryan con", "ryan con", "ryan con", "ryan con"}, 3)));
    }

    private static final String DELIMITER = " ";
    private static final int REPORTED_USER = 1;

    public static int[] solution(String[] id_list, String[] reports, int k) {
        List<String> reportsWithoutOverlap = Arrays.stream(reports).distinct().collect(Collectors.toList());
        Map<String, Integer> reportedCounts = new HashMap<>();
        reportsWithoutOverlap.stream()
                .map(report -> report.split(DELIMITER)[REPORTED_USER])
                .forEach(reportedId -> reportedCounts.put(reportedId, reportedCounts.getOrDefault(reportedId, 0) + 1));
        return Arrays.stream(id_list)
                .map(id -> reportsWithoutOverlap.stream()
                        .filter(report -> report.startsWith(id + DELIMITER))
                        .collect(Collectors.toList())
                        .stream()
                        .filter(report -> reportedCounts.getOrDefault(report.split(DELIMITER)[REPORTED_USER], 0) >= k)
                        .count())
                .mapToInt(Long::intValue)
                .toArray();
    }
}
