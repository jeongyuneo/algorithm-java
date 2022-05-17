package Programmers;

import java.util.*;

public class Programmers_2022_Kakao_Level2_주차요금계산 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"})));
        System.out.println(Arrays.toString(solution(new int[]{120, 0, 60, 591}, new String[]{"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"})));
        System.out.println(Arrays.toString(solution(new int[]{1, 461, 1, 10}, new String[]{"00:00 1234 IN"})));
    }

    private static final int BASIC_TIME = 0;
    private static final int BASIC_PAY = 1;
    private static final int UNIT_TIME = 2;
    private static final int UNIT_PAY = 3;
    private static final int TIME = 0;
    private static final int CAR_NUMBER = 1;
    private static final int MINUTE = 1;
    private static final String END_OF_THE_DAY = "23:59";
    private static final String SPACE = " ";
    private static final String DELIMITER = ":";

    public static int[] solution(int[] fees, String[] records) {
        return calculatePayment(fees, getParkingTimeInfos(records));
    }

    private static int[] calculatePayment(int[] fees, Map<String, Integer> timeInfos) {
        List<String> carNumbers = new ArrayList<>(timeInfos.keySet());
        Collections.sort(carNumbers);
        int[] answer = new int[carNumbers.size()];
        int idx = 0;
        for (String carNumber : carNumbers) {
            int accumulatedTime = timeInfos.get(carNumber);
            if (accumulatedTime <= fees[BASIC_TIME]) {
                answer[idx++] = fees[BASIC_PAY];
            } else {
                int additionalTime = accumulatedTime - fees[BASIC_TIME];
                int additionalPay = (int) Math.ceil((double) additionalTime / fees[UNIT_TIME]) * fees[UNIT_PAY];
                answer[idx++] = fees[BASIC_PAY] + additionalPay;
            }
        }
        return answer;
    }

    private static Map<String, Integer> getParkingTimeInfos(String[] records) {
        Map<String, String> parkingInfos = new HashMap<>();
        Map<String, Integer> timeInfos = new HashMap<>();
        for (String record : records) {
            String[] parkingInfo = record.split(SPACE);
            String carNumber = parkingInfo[CAR_NUMBER];
            String time = parkingInfo[TIME];
            if (!parkingInfos.containsKey(carNumber)) {
                parkingInfos.put(carNumber, time);
            } else {
                String inTime = parkingInfos.remove(carNumber);
                updateTimeInfos(timeInfos, carNumber, getAccumulatedTime(time, inTime));
            }
        }
        for (Map.Entry<String, String> leftCar : parkingInfos.entrySet()) {
            updateTimeInfos(timeInfos, leftCar.getKey(), getAccumulatedTime(END_OF_THE_DAY, leftCar.getValue()));
        }
        return timeInfos;
    }

    private static void updateTimeInfos(Map<String, Integer> timeInfos, String carNumber, int accumulatedTime) {
        if (timeInfos.containsKey(carNumber)) {
            timeInfos.put(carNumber, timeInfos.get(carNumber) + accumulatedTime);
        } else {
            timeInfos.put(carNumber, accumulatedTime);
        }
    }

    private static int getAccumulatedTime(String outTime, String inTime) {
        String[] out = outTime.split(DELIMITER);
        String[] in = inTime.split(DELIMITER);
        int parkingTime = Integer.parseInt(out[TIME]) - Integer.parseInt(in[TIME]);
        int parkingMinute = Integer.parseInt(out[MINUTE]) - Integer.parseInt(in[MINUTE]);
        if (parkingMinute < 0) {
            parkingMinute += 60;
            parkingTime--;
        }
        return parkingTime * 60 + parkingMinute;
    }
}
