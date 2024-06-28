import java.util.ArrayList;

public class BoardController extends TicTacToeBoard {

    private ArrayList<TicTacToeBoard> allBoards;
    private ArrayList<TicTacToeBoard> matchingBoards;

    public BoardController() {
        super();
        allBoards = new ArrayList<>();
        matchingBoards = new ArrayList<>();
    }

    public ArrayList<TicTacToeBoard> getAllBoard() {
        generateAllPossibleBoards();
        return allBoards;
    }

    public void generateAllPossibleBoards() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TicTacToeBoard newArr = new TicTacToeBoard();
                char activePlayer = 'x';
                makeMove(i, j, activePlayer, newArr.getBoard());
                activePlayer = changePlayer(activePlayer);
                makePossibleMove(activePlayer, newArr, true);
            }
        }
    }

    public char changePlayer(char activePlayer) {
        return activePlayer == 'x' ? 'o' : 'x';
    }

    public void makePossibleMove(char currentPlayers,TicTacToeBoard newArr, boolean isLast) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                makeMove(i, j, currentPlayers, newArr.getBoard());
                if (isLast) allBoards.add(newArr);
            }
        }
    }

    @Override
    public void makeMove(int row, int col, char player, char[][] board) {
        if(board[row][col] == ' ') super.makeMove(row, col, player, board);
    }
}
