import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeBoard {

    private char[][] board;

    public char[][] getBoard() {
        return board;
    }

    public TicTacToeBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void makeMove(int row, int col, char player) {
        board[row][col] = player;
    }

    public void displayCurrentBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println(board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) {
                System.out.println("---------");
            }
        }
    }

    public void enterMove(char player) {
        Scanner scanner = new Scanner(System.in);
        int row = 100;
        int col = 100;

        try {
            do {
                String[] input = scanner.nextLine().split(" ");
                row = Integer.parseInt(input[0]) - 1;
                col = Integer.parseInt(input[1]) - 1;
                System.out.println(row + " " + col);
            } while (board[row][col] != ' ');
        } catch (Exception e) {
            enterMove(player);
            return;
        }

        makeMove(row, col, player);
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

        return false;
    }
}
