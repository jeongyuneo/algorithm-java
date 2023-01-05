package Programmers;

import java.util.ArrayDeque;
import java.util.Deque;

public class Programmers_스택큐_Level2_다리를지나는트럭 {

    public static void main(String[] args) {
        System.out.println(solution(2, 10, new int[]{7, 4, 5, 6}));
        System.out.println(solution(100, 100, new int[]{10}));
        System.out.println(solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}));
    }

    static class Truck {

        int weight;
        int time;

        public Truck(int weight) {
            this.weight = weight;
        }
    }

    public static int solution(int bridgeLength, int maxWeight, int[] truckWeights) {
        Deque<Truck> bridge = new ArrayDeque<>();
        int truckNumber = truckWeights.length;
        int weight = 0;
        int time = 0;
        int nextTruck = 0;
        while (nextTruck < truckNumber) {
            time++;
            int truck = truckWeights[nextTruck];
            if (weight + truck <= maxWeight) {
                bridge.offer(new Truck(truck));
                weight += truck;
                nextTruck++;
            }
            int truckNumberOnBridge = bridge.size();
            while (truckNumberOnBridge-- > 0) {
                Truck truckOnBridge = bridge.poll();
                if (++truckOnBridge.time == bridgeLength) {
                    weight -= truckOnBridge.weight;
                } else {
                    bridge.offer(truckOnBridge);
                }
            }
        }
        if (!bridge.isEmpty()) {
            time += bridgeLength - bridge.pollLast().time + 1;
        }
        return time;
    }
}
