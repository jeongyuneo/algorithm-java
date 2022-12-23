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

    public static int[] solution(String[] id_list, String[] reports, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Set<String>> reportingIds = new HashMap<>();
        Map<String, Integer> reportedIds = new HashMap<>();
        for (String id : id_list) {
            reportingIds.put(id, new HashSet<>());
            reportedIds.put(id, 0);
        }

        for (String report : reports) {
            String[] reportInfos = report.split(DELIMITER);
            String reportedUser = reportInfos[REPORTED_USER];
            Set<String> reportInfo = reportingIds.get(reportInfos[REPORTING_USER]);
            if (!reportInfo.contains(reportedUser)) {
                reportedIds.put(reportedUser, reportedIds.get(reportedUser) + 1);
            }
            reportInfo.add(reportedUser);
        }

        for (int i = 0; i < id_list.length; i++) {
            for (String reportedId : reportingIds.get(id_list[i])) {
                if (reportedIds.get(reportedId) >= k) {
                    answer[i]++;
                }
            }
        }
        return answer;
    }
}
