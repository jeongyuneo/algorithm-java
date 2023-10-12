package Programmers;

public class Programmers_startup_internship_Level3_기지국설치 {

    public static void main(String[] args) {
        System.out.println(solution(11, new int[]{4, 11}, 1));
        System.out.println(solution(16, new int[]{9}, 2));
    }

    public static int solution(int n, int[] stations, int w) {
        int count = 0;
        int station = 0;
        int location = 1;
        while (location <= n) {
            if (station < stations.length && location + w >= stations[station]) {
                location = stations[station] + w + 1;
                station++;
            } else {
                location += w * 2 + 1;
                count++;
            }
        }
        return count;
    }
}
