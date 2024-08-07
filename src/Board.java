import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private char[][] boardState;

    public Board() {
        boardState = new char[3][3];
        makeBoardEmpty();
    }

    public char[][] getBoardState() {
        return boardState;
    }

    public void makeMove(int row, int col, Players currentPlayer) {
        boardState[row][col] = currentPlayer.getPlayer();
        printBoard();
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println(boardState[i][0] + " | " + boardState[i][1] + " | " + boardState[i][2]);
            if (i < 2) {
                System.out.println("---------");
            }
        }

        System.out.println();
    }

    public void enterMove(Players currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        int row;
        int col;
        try {
            do {
                String[] input = scanner.nextLine().trim().split(" ");
                row = Integer.parseInt(input[0]) - 1;
                col = Integer.parseInt(input[1]) - 1;
                if(boardState[row][col] != ' ') printBoard();
            } while (boardState[row][col] != ' ');
        } catch (Exception e) {
            printBoard();
            enterMove(currentPlayer);
            return;
        }
        makeMove(row, col, currentPlayer);
    }

    public boolean isFull() {
        for (char[] row : boardState) {
            for (char field : row) {
                if (field == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWinner() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(boardState[i][0], boardState[i][1], boardState[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(boardState[0][i], boardState[1][i], boardState[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return ((checkRowCol(boardState[0][0], boardState[1][1], boardState[2][2])) ||
                (checkRowCol(boardState[0][2], boardState[1][1], boardState[2][0])));
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != ' ') && (c1 == c2) && (c2 == c3));
    }

    private void makeBoardEmpty() {
        for(char[] row : boardState) {
            Arrays.fill(row, ' ');
        }
    }
}
