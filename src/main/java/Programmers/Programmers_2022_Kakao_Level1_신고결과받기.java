package Programmers;

import java.util.*;

public class Programmers_2022_Kakao_Level1_신고결과받기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}, 2)));
        System.out.println(Arrays.toString(solution(new String[]{"con", "ryan"}, new String[]{"ryan con", "ryan con", "ryan con", "ryan con"}, 3)));
    }

    private static final String DELIMITER = " ";
    private static final int REPORTING_USER = 0;
    private static final int REPORTED_USER = 1;

    public static int[] solution(String[] id_list, String[] report, int k) {
        List<String> ids = Arrays.asList(id_list);
        Map<String, Set<String>> reportingInfos = new HashMap<>();
        Map<String, Integer> reportedCounts = new HashMap<>();
        Arrays.stream(report)
                .distinct()
                .forEach(reportInfo -> {
                    String[] reportInfos = reportInfo.split(DELIMITER);
                    String reportingId = reportInfos[REPORTING_USER];
                    String reportedId = reportInfos[REPORTED_USER];
                    Set<String> reportingIds = reportingInfos.getOrDefault(reportingId, new HashSet<>());
                    reportingIds.add(reportedId);
                    reportingInfos.put(reportingId, reportingIds);
                    reportedCounts.put(reportedId, reportedCounts.getOrDefault(reportedId, 0) + 1);
                });

        int[] answer = new int[id_list.length];
        reportingInfos.keySet()
                .forEach(reportingId -> reportingInfos.get(reportingId)
                        .stream()
                        .filter(reportedId -> reportedCounts.get(reportedId) >= k)
                        .forEach(reportedId -> answer[ids.indexOf(reportingId)]++));
        return answer;
    }
}
