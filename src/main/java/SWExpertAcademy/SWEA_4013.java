package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4013 {

    private static final int WHEEL_BLADE_NUMBER = 8;
    private static final int WHEEL_NUMBER = 4;
    private static final int[][] WHEELS = new int[WHEEL_NUMBER + 1][WHEEL_BLADE_NUMBER];
    private static final boolean[] CAN_ROTATE = new boolean[WHEEL_NUMBER];
    private static final int RIGHT_SIDE = 2;
    private static final int LEFT_SIDE = 6;
    private static final int REVERSE = -1;

    private static int[] tops;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer;
        int testCase = Integer.parseInt(bufferedReader.readLine());
        for (int t = 1; t <= testCase; t++) {
            int rotationNumber = Integer.parseInt(bufferedReader.readLine());
            for (int i = 1; i <= WHEEL_NUMBER; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < WHEEL_BLADE_NUMBER; j++) {
                    WHEELS[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            tops = new int[WHEEL_NUMBER + 1];
            for (int i = 0; i < rotationNumber; i++) {
                checkWheelSide();
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int rotationWheel = Integer.parseInt(stringTokenizer.nextToken());
                int rotationDirection = Integer.parseInt(stringTokenizer.nextToken());
                rotation(rotationWheel, rotationDirection);
            }

            int score = 0;
            for (int i = 1; i <= WHEEL_NUMBER; i++) {
                score += WHEELS[i][tops[i]] << (i - 1);
            }
            stringBuilder.append("#")
                    .append(t)
                    .append(" ")
                    .append(score)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void rotation(int rotationWheel, int rotationDirection) {
        tops[rotationWheel] = (tops[rotationWheel] - rotationDirection + WHEEL_BLADE_NUMBER) % WHEEL_BLADE_NUMBER;
        int sideRotationDirection = rotationDirection;
        for (int i = rotationWheel - 1; i > 0; i--) {
            if (CAN_ROTATE[i]) {
                sideRotationDirection *= REVERSE;
                tops[i] = (tops[i] - sideRotationDirection + WHEEL_BLADE_NUMBER) % WHEEL_BLADE_NUMBER;
            } else {
                break;
            }
        }
        sideRotationDirection = rotationDirection;
        for (int i = rotationWheel + 1; i <= WHEEL_NUMBER; i++) {
            if (CAN_ROTATE[i - 1]) {
                sideRotationDirection *= REVERSE;
                tops[i] = (tops[i] - sideRotationDirection + WHEEL_BLADE_NUMBER) % WHEEL_BLADE_NUMBER;
            } else {
                break;
            }
        }
    }

    private static void checkWheelSide() {
        for (int i = 1; i < WHEEL_NUMBER; i++) {
            if (WHEELS[i][(tops[i] + RIGHT_SIDE) % WHEEL_BLADE_NUMBER] != WHEELS[i + 1][(tops[i + 1] + LEFT_SIDE) % WHEEL_BLADE_NUMBER]) {
                CAN_ROTATE[i] = true;
            } else {
                CAN_ROTATE[i] = false;
            }
        }
    }
}
