package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17837 {

    static class ChessPiece {

        int number;
        int x;
        int y;
        int direction;

        public ChessPiece(int number, int x, int y, int direction) {
            this.number = number;
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    private static final int[][] DELTAS = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    private static final int RED = 1;
    private static final int BLUE = 2;

    private static int[][] chessBoard;
    private static List[][] chessBoardWithPiece;
    private static ChessPiece[] chessPieces;
    private static int n;
    private static int k;
    private static int turn;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());
        chessBoard = new int[n][n];
        chessBoardWithPiece = new List[n][n];
        chessPieces = new ChessPiece[k + 1];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                chessBoard[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                chessBoardWithPiece[i][j] = new ArrayList<ChessPiece>();
            }
        }
        for (int i = 0; i < k; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int direction = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            ChessPiece chessPiece = new ChessPiece(i + 1, x, y, direction);
            chessPieces[i + 1] = chessPiece;
            chessBoardWithPiece[x][y].add(chessPiece);
        }
        play();
    }

    private static void play() {
        turn = 1;
        while (turn <= 1000) {
            for (int number = 1; number <= k; number++) {
                move(number);
            }
            turn++;
        }
        System.out.println(-1);
    }

    private static void move(int number) {
        ChessPiece chessPiece = chessPieces[number];
        int x = chessPiece.x;
        int y = chessPiece.y;
        int direction = chessPiece.direction;

        List<ChessPiece> board = chessBoardWithPiece[x][y];
        int split = 0;
        while (board.get(split).number != number) {
            split++;
        }

        int dx = x + DELTAS[direction][0];
        int dy = y + DELTAS[direction][1];

        if (dx < 0 || dx >= n || dy < 0 || dy >= n || chessBoard[dx][dy] == BLUE) {
            chessPieces[number].direction ^= 1;
            dx -= DELTAS[direction][0] * 2;
            dy -= DELTAS[direction][1] * 2;
            if (dx < 0 || dx >= n || dy < 0 || dy >= n || chessBoard[dx][dy] == BLUE) { // 범위 벗어나거나 파란 색 칸이면 종료
                return;
            }
            move(number);
        } else if (chessBoard[dx][dy] == RED) {
            for (int i = board.size() - 1; i >= split; i--) {
                ChessPiece piece = board.remove(i);
                piece.x = dx;
                piece.y = dy;
                chessBoardWithPiece[dx][dy].add(piece);
            }
        } else {
            for (int i = split; i < board.size(); i++) {
                ChessPiece piece = board.remove(i--);
                piece.x = dx;
                piece.y = dy;
                chessBoardWithPiece[dx][dy].add(piece);
            }
        }

        if (chessBoardWithPiece[dx][dy].size() >= 4) {
            System.out.println(turn);
            System.exit(0);
        }
        chessPieces[number].x = dx;
        chessPieces[number].y = dy;
    }
}
