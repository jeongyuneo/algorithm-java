package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_1713 {

    private static final int RECOMMENDATION = 0;
    private static final int POSTED_TIME = 1;
    private static final int STUDENT = 2;
    private static final PriorityQueue<int[]> ORDERED_RECOMMENDATIONS = new PriorityQueue<>((recommendation1, recommendation2) -> {
        if (recommendation1[RECOMMENDATION] == recommendation2[RECOMMENDATION]) {
            return recommendation1[POSTED_TIME] - recommendation2[POSTED_TIME];
        }
        return recommendation1[RECOMMENDATION] - recommendation2[RECOMMENDATION];
    });

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int recommendationCount = Integer.parseInt(bufferedReader.readLine());
        Map<Integer, int[]> recommendations = new TreeMap<>();
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int postedTime = 0; postedTime < recommendationCount; postedTime++) {
            int student = Integer.parseInt(stringTokenizer.nextToken());
            if (recommendations.containsKey(student)) {
                int[] recommendationInfo = recommendations.get(student);
                recommendationInfo[RECOMMENDATION]++;
                recommendations.put(student, recommendationInfo);
            } else {
                if (recommendations.size() == n) {
                    removePhoto(recommendations);
                }
                recommendations.put(student, new int[]{0, postedTime});
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int student : recommendations.keySet()) {
            answer.append(student).append(" ");
        }
        System.out.println(answer);
    }

    private static void removePhoto(Map<Integer, int[]> recommendations) {
        ORDERED_RECOMMENDATIONS.clear();
        for (Map.Entry<Integer, int[]> recommendation : recommendations.entrySet()) {
            int[] recommendationInfo = recommendation.getValue();
            ORDERED_RECOMMENDATIONS.offer(new int[]{recommendationInfo[RECOMMENDATION], recommendationInfo[POSTED_TIME], recommendation.getKey()});
        }
        recommendations.remove(ORDERED_RECOMMENDATIONS.poll()[STUDENT]);
    }
}
