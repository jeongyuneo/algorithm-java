package Programmers;

import java.util.PriorityQueue;

public class Programmers_2023_Kakao_Level2_택배배달과수거하기 {

    public static void main(String[] args) {
        System.out.println(solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0}));
        System.out.println(solution(2, 7, new int[]{1, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0}));
    }

    private static final int DISTANCE = 0;
    private static final int PARCEL_NUMBER = 1;

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        PriorityQueue<int[]> delivery = new PriorityQueue<>((info1, info2) -> info2[DISTANCE] - info1[DISTANCE]);
        PriorityQueue<int[]> pickup = new PriorityQueue<>((info1, info2) -> info2[DISTANCE] - info1[DISTANCE]);
        for (int i = n - 1; i >= 0; i--) {
            if (deliveries[i] != 0) {
                delivery.offer(new int[]{i + 1, deliveries[i]});
            }
            if (pickups[i] != 0) {
                pickup.offer(new int[]{i + 1, pickups[i]});
            }
        }
        long minDistance = 0;
        while (!delivery.isEmpty() || !pickup.isEmpty()) {
            int distance = 0;
            distance = getDistance(delivery, cap, distance);
            distance = getDistance(pickup, cap, distance);
            minDistance += distance * 2;
        }
        return minDistance;
    }

    private static int getDistance(PriorityQueue<int[]> houses, int cap, int distance) {
        int parcel = 0;
        while (!houses.isEmpty() && parcel < cap) {
            int[] house = houses.poll();
            distance = Math.max(distance, house[DISTANCE]);
            if (parcel + house[PARCEL_NUMBER] <= cap) {
                parcel += house[PARCEL_NUMBER];
            } else {
                house[PARCEL_NUMBER] -= cap - parcel;
                houses.offer(house);
                break;
            }
        }
        return distance;
    }
}
