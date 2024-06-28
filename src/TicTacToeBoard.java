import java.util.Scanner;

public class TicTacToeBoard {

    private char[][] board;

    public TicTacToeBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
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
        System.out.println();
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
}
