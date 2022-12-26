package Programmers;

import java.util.*;
import java.util.stream.IntStream;

public class Programmers_2022_Kakao_Level1_신고결과받기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}, 2)));
        System.out.println(Arrays.toString(solution(new String[]{"con", "ryan"}, new String[]{"ryan con", "ryan con", "ryan con", "ryan con"}, 3)));
    }

    private static final String DELIMITER = " ";
    private static final int REPORTING_USER = 0;
    private static final int REPORTED_USER = 1;

    public static int[] solution(String[] id_list, String[] reports, int k) {
        Map<String, Set<String>> reportedInfos = new HashMap<>();
        Map<String, Integer> ids = new HashMap<>();
        IntStream.range(0, id_list.length)
                .forEach(i -> {
                    String id = id_list[i];
                    ids.put(id, i);
                    reportedInfos.put(id, new HashSet<>());
                });

        Arrays.stream(reports)
                .map(report -> report.split(DELIMITER))
                .forEach(reportInfos -> reportedInfos.get(reportInfos[REPORTED_USER]).add(reportInfos[REPORTING_USER]));

        int[] answer = new int[id_list.length];
        reportedInfos.values()
                .stream()
                .filter(reportingIds -> reportingIds.size() >= k)
                .flatMap(Collection::stream)
                .forEach(reportingId -> answer[ids.get(reportingId)]++);
        return answer;
    }
}
