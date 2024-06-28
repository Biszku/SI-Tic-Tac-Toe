import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeBoard {

    private char[][] board;

    public TicTacToeBoard() {
        board = new char[3][3];
        for(char[] row : board) {
            Arrays.fill(row, ' ');
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void makeMove(int row, int col, char player,char[][] board) {
        board[row][col] = player;
    }

    public void displayCurrentBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println(board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) {
                System.out.println("---------");
            }
        }

        System.out.println();
    }

    public void enterMove(char player,char[][] board) {
        Scanner scanner = new Scanner(System.in);
        int row ;
        int col;

        try {
            do {
                String[] input = scanner.nextLine().trim().split(" ");
                row = Integer.parseInt(input[0]) - 1;
                col = Integer.parseInt(input[1]) - 1;
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

    public boolean checkWinner(char player, char[][] board) {
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

        // cross checking
        char[] cross1 = new char[] {board[0][0],board[1][1],board[2][2]};
        char[] cross2 = new char[] {board[0][2],board[1][1],board[2][0]};
        if(Arrays.equals(cross1,win) || Arrays.equals(cross2,win)) return true;

        return false;
    }
}
