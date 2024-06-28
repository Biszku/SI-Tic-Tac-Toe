import java.util.ArrayList;
import java.util.Arrays;

public class BoardController extends TicTacToeBoard {

    private ArrayList<char[][]> allBoards;

    public BoardController() {
        super();
        allBoards = new ArrayList<>();
        char[][]board = new char[3][3];
        for(char[] row : board) Arrays.fill(row, ' ');
        generateAllPossibleBoards(board, 'x');
    }

    public ArrayList<char[][]> getAllBoard() {
        return allBoards;
    }

    public void generateAllPossibleBoards(char[][] board, char currentPlayer) {
        if (isFull(board) || checkWinner('x', board) || checkWinner('o', board)) {
            System.out.println(Arrays.deepToString(board));
            allBoards.add(board);
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = currentPlayer;
                    generateAllPossibleBoards(board, currentPlayer == 'x' ? 'o' : 'x');
                    board[i][j] = ' ';
                }
            }
        }
    }
}
