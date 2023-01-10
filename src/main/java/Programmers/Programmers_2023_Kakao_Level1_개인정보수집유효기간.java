package Programmers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Programmers_2023_Kakao_Level1_개인정보수집유효기간 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("2022.05.19", new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"})));
    }


    private static final String DELIMITER = " ";
    private static final int KIND = 0;
    private static final int PERIOD = 1;
    private static final int COLLECTION_DATE = 0;
    private static final int USE_KIND = 1;

    public static int[] solution(String today, String[] terms, String[] privacies) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        List<Integer> destroyedPersonalInformation = new ArrayList<>();
        Map<String, String> termMap = new HashMap<>();
        LocalDate todayDate = LocalDate.parse(today, formatter);
        for (String term : terms) {
            String[] split = term.split(DELIMITER);
            termMap.put(split[KIND], split[PERIOD]);
        }
        int number = 1;
        for (String privacy : privacies) {
            String kind = privacy.split(DELIMITER)[USE_KIND];
            String collected = privacy.split(DELIMITER)[COLLECTION_DATE];
            LocalDate destroyingDate = LocalDate.parse(collected, formatter).plusMonths(Long.parseLong(termMap.get(kind))).minusDays(1);
            if (destroyingDate.getDayOfMonth() > 28) {
                destroyingDate = destroyingDate.minusDays(destroyingDate.getDayOfMonth() - 28);
            }
            if (todayDate.isAfter(destroyingDate)) {
                destroyedPersonalInformation.add(number);
            }
            number++;
        }
        return destroyedPersonalInformation.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
