package Programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Programmers_힙_Level3_디스크컨트롤러 {

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0, 3}, {1, 9}, {2, 6}}));
    }

    private static final int REQUEST_TIME = 0;
    private static final int REQUIRED_TIME = 1;

    public static int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparing(job -> job[REQUEST_TIME]));
        PriorityQueue<int[]> hardDisk = new PriorityQueue<>(Comparator.comparing(job -> job[REQUIRED_TIME]));
        int jobCount = jobs.length;
        int workingJob = 0;
        int totalTime = 0;
        int endTimeOfWorkingJob = 0;
        while (jobCount > 0) {
            while (workingJob < jobs.length && jobs[workingJob][REQUEST_TIME] <= endTimeOfWorkingJob) {
                hardDisk.offer(jobs[workingJob++]);
            }

            if (hardDisk.isEmpty()) {
                endTimeOfWorkingJob = jobs[workingJob][REQUEST_TIME];
            } else {
                int[] current = hardDisk.poll();
                totalTime += current[REQUIRED_TIME] - current[REQUEST_TIME] + endTimeOfWorkingJob;
                endTimeOfWorkingJob += current[REQUIRED_TIME];
                jobCount--;
            }
        }
        return totalTime / jobs.length;
    }
}
