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
        Map<String, Set<String>> reportingIds = new HashMap<>();
        for (String id : id_list) {
            reportingIds.put(id, new HashSet<>());
        }

        List<String> ids = Arrays.asList(id_list);
        int idCount = id_list.length;
        int[] reportedCounts = new int[idCount];
        for (String report : reports) {
            String[] reportInfos = report.split(DELIMITER);
            String reportingUser = reportInfos[REPORTING_USER];
            String reportedUser = reportInfos[REPORTED_USER];
            Set<String> reportInfo = reportingIds.get(reportedUser);
            if (!reportInfo.contains(reportingUser)) {
                reportedCounts[ids.indexOf(reportedUser)]++;
            }
            reportInfo.add(reportingUser);
        }

        int[] answer = new int[idCount];
        for (int i = 0; i < idCount; i++) {
            if (reportedCounts[i] >= k) {
                for (String reportingUser : reportingIds.get(ids.get(i))) {
                    answer[ids.indexOf(reportingUser)]++;
                }
            }
        }
        return answer;
    }
}
