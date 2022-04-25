package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1755 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int start = Integer.parseInt(stringTokenizer.nextToken());
        int end = Integer.parseInt(stringTokenizer.nextToken());

        String[] changes = new String[]{"zero ", "one ", "two ", "three ", "four ", "five ", "six ", "seven ", "eight ", "nine"};
        String[][] englishNumbers = new String[end - start + 1][2]; // 영어 숫자와 원래 숫자를 저장할 배열
        int idx = 0;
        for (int number = start; number <= end; number++) {
            StringBuilder englishNumber = new StringBuilder();
            if (number < 10) {  // 숫자가 한 자리이면 숫자를 그대로 영어로 바꾼다.
                englishNumber.append(changes[number]);
            } else {    // 숫자가 두 자리이면 십의 자리와 일의 자리를 각각 영어로 바꾼다.
                englishNumber.append(changes[number / 10]);
                englishNumber.append(changes[number % 10]);
            }
            englishNumbers[idx][0] = englishNumber.toString();  // 0번 인덱스에는 영어 숫자 저장
            englishNumbers[idx++][1] = String.valueOf(number);  // 1번 인덱스에는 원래 숫자 저장
        }
        Arrays.sort(englishNumbers, Comparator.comparing(o -> o[0]));   // 영어 숫자를 기준으로 정렬

        // 정렬된 숫자를 한 줄에 10개씩 원래 숫자로 출력
        int cnt = 0;
        for (String[] englishNumber : englishNumbers) {
            stringBuilder.append(englishNumber[1])
                    .append(" ");
            if (++cnt == 10) {  // 한 줄에 10개가 들어가면 줄을 바꾸고, cnt를 0으로 초기화
                stringBuilder.append("\n");
                cnt = 0;
            }
        }
        System.out.println(stringBuilder);
    }
}
