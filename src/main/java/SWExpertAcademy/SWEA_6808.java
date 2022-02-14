package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_6808 {

    private static final int CARD_NUMBER = 18;
    private static final List<Integer> GYUYEONG_CARDS = new ArrayList<>();
    private static final List<Integer> INYEONG_CARDS = new ArrayList<>();
    private static final int[] SELECTED_INYEONG_CARDS = new int[9];

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

            boolean[] isSelected = new boolean[CARD_NUMBER+1];
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < 9; i++) {
                isSelected[Integer.parseInt(stringTokenizer.nextToken())] = true;
            }
            for (int i = 1; i <= CARD_NUMBER; i++) {
                if (!isSelected[i]) {
                    INYEONG_CARDS.add(i);
                } else {
                    GYUYEONG_CARDS.add(i);
                }
            }

            gyuyeongWin = 0;
            gyuyeongLose = 0;

            permutation(0, 0);

            GYUYEONG_CARDS.clear();
            INYEONG_CARDS.clear();

            stringBuilder.append(gyuyeongWin)
                    .append(" ")
                    .append(gyuyeongLose)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void permutation(int cnt, int flag) {
        if (cnt == 9) {
            int gyuyeongScore = 0;
            int inyeongScore = 0;
            for (int j = 0; j < 9; j++) {
                int gyuyeongCard = GYUYEONG_CARDS.get(j);
                int inyeongCard = SELECTED_INYEONG_CARDS[j];
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
            return;
        }
        for (int i = 0; i < 9; i++) {
            if ((flag & 1 << i) != 0) {
                continue;
            }
            SELECTED_INYEONG_CARDS[cnt] = INYEONG_CARDS.get(i);
            permutation(cnt+1, flag | 1 << i);
        }
    }
}
