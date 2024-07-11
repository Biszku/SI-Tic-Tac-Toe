import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private char[][] boardState;

    public Board() {
        boardState = new char[3][3];
        makeBoardEmpty(boardState);
    }

    public char[][] getBoardState() {
        return boardState;
    }

    public void makeMove(int row, int col, char player,char[][] board) {
        board[row][col] = player;
        displayBoardState();
    }

    public void displayBoardState() {
        for (int i = 0; i < 3; i++) {
            System.out.println(boardState[i][0] + " | " + boardState[i][1] + " | " + boardState[i][2]);
            if (i < 2) {
                System.out.println("---------");
            }
        }

        System.out.println();
    }

    public void enterMove(char player,char[][] board) {
        Scanner scanner = new Scanner(System.in);
        int row;
        int col;
        try {
            do {
                String[] input = scanner.nextLine().trim().split(" ");
                row = Integer.parseInt(input[0]) - 1;
                col = Integer.parseInt(input[1]) - 1;
                if(board[row][col] != ' ') displayBoardState();
            } while (board[row][col] != ' ');
        } catch (Exception e) {
            enterMove(player,board);
            return;
        }
        makeMove(row, col, player, board);
    }

    public boolean isFull(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                if (c == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWinner(char player, char[][] board) {
        char[] win = new char[] {player, player, player};

        // horizontal checking
        for(char[] tab : board) {
            if(Arrays.equals(tab,win)) return true;
        }

        // vertical checking
        for(int i = 0; i < board.length; i++) {
            char[] newArr = new char[] {board[0][i],board[1][i],board[2][i]};
            if(Arrays.equals(newArr,win)) return true;
        }

        // cross-checking
        char[] cross1 = new char[] {board[0][0],board[1][1],board[2][2]};
        char[] cross2 = new char[] {board[0][2],board[1][1],board[2][0]};
        if(Arrays.equals(cross1,win) || Arrays.equals(cross2,win)) return true;

        return false;
    }

    private void makeBoardEmpty(char[][] board) {
        for(char[] row : board) {
            Arrays.fill(row, ' ');
        }
    }
}
