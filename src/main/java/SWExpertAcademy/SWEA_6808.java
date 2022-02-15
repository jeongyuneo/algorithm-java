package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_6808 {

    private static final int TOTAL_CARD_NUMBER = 18;
    private static final int PLAYER_CARD_NUMBER = 9;
    private static final int[] GYUYEONG_CARDS = new int[PLAYER_CARD_NUMBER];
    private static final int[] INYEONG_CARDS = new int[PLAYER_CARD_NUMBER];

    private static int gyuyeongWin;
    private static int gyuyeongLose;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int testCase = Integer.parseInt(bufferedReader.readLine());
        for (int t = 1; t <= testCase; t++) {
            stringBuilder.append("#")
                    .append(t)
                    .append(" ");

            boolean[] isSelected = new boolean[TOTAL_CARD_NUMBER +1];
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < PLAYER_CARD_NUMBER; i++) {
                isSelected[Integer.parseInt(stringTokenizer.nextToken())] = true;
            }
            int gyuyeongIndex = 0;
            int inyeongIndex = 0;
            for (int i = 1; i <= TOTAL_CARD_NUMBER; i++) {
                if (!isSelected[i]) {
                    INYEONG_CARDS[inyeongIndex++] = i;
                } else {
                    GYUYEONG_CARDS[gyuyeongIndex++] = i;
                }
            }

            gyuyeongWin = 0;
            gyuyeongLose = 0;

            Arrays.sort(INYEONG_CARDS);

            do {
                play();
            } while (nextPermutation());

            stringBuilder.append(gyuyeongWin)
                    .append(" ")
                    .append(gyuyeongLose)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void play() {
        int gyuyeongScore = 0;
        int inyeongScore = 0;
        for (int j = 0; j < PLAYER_CARD_NUMBER; j++) {
            int gyuyeongCard = GYUYEONG_CARDS[j];
            int inyeongCard = INYEONG_CARDS[j];
            if (gyuyeongCard > inyeongCard) {
                gyuyeongScore += gyuyeongCard + inyeongCard;
            } else if ((gyuyeongCard < inyeongCard)) {
                inyeongScore += gyuyeongCard + inyeongCard;
            }
        }
        if (gyuyeongScore > inyeongScore) {
            gyuyeongWin++;
        } else if (gyuyeongScore < inyeongScore) {
            gyuyeongLose++;
        }
    }

    private static boolean nextPermutation() {
        int i = PLAYER_CARD_NUMBER - 1;
        while (i > 0 && INYEONG_CARDS[i-1] >= INYEONG_CARDS[i]) {
            i--;
        }

        if (i == 0) {
            return false;
        }

        int j = PLAYER_CARD_NUMBER - 1;
        while (INYEONG_CARDS[i-1] >= INYEONG_CARDS[j]) {
            j--;
        }

        swqp(i-1, j);

        int k = PLAYER_CARD_NUMBER - 1;
        while (i < k) {
            swqp(i++, k--);
        }

        return true;
    }

    private static void swqp(int i, int j) {
        int temp = INYEONG_CARDS[i];
        INYEONG_CARDS[i] = INYEONG_CARDS[j];
        INYEONG_CARDS[j] = temp;
    }
}
