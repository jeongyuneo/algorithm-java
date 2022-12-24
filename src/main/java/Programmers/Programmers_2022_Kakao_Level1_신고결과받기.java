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
        Map<String, Set<String>> reportedInfos = new HashMap<>();
        Map<String, Integer> ids = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];
            ids.put(id, i);
            reportedInfos.put(id, new HashSet<>());
        }

        for (String report : reports) {
            String[] reportInfos = report.split(DELIMITER);
            reportedInfos.get(reportInfos[REPORTED_USER]).add(reportInfos[REPORTING_USER]);
        }

        int[] answer = new int[id_list.length];
        for (Set<String> reportingIds : reportedInfos.values()) {
            if (reportingIds.size() >= k) {
                for (String reportingId : reportingIds) {
                    answer[ids.get(reportingId)]++;
                }
            }
        }
        return answer;
    }
}
