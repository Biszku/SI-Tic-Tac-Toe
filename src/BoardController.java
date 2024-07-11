import java.util.ArrayList;
import java.util.List;

public class BoardController extends Board {
    private List<Board> allBoards;

    public BoardController() {
        super();
        allBoards = new ArrayList<>();
        Board board = new Board();
        generateAllPossibleBoards(board, 'x');
    }

    public List<Board> getAllBoard() {
        return allBoards;
    }

    public void generateAllPossibleBoards(Board board, char currentPlayer) {
        if (isFull(board.getBoardState()) ||
                isWinner('x', board.getBoardState()) ||
                isWinner('o', board.getBoardState())) {
            allBoards.add(board);
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoardState()[i][j] == ' ') {
                    board.getBoardState()[i][j] = currentPlayer;
                    generateAllPossibleBoards(board, currentPlayer == 'x' ? 'o' : 'x');
                    board.getBoardState()[i][j] = ' ';
                }
            }
        }
    }
}
