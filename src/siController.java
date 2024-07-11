import java.util.ArrayList;
import java.util.List;

public class siController  {
    private List<Board> winnerBoards;
    private List<Board> drawBoards;

    public siController() {
        super();
        winnerBoards = new ArrayList<>();
        drawBoards = new ArrayList<>();
    }

    public List<Board> getWinnerBoards() {
        return winnerBoards;
    }

    public List<Board> getDrawBoards() {
        return drawBoards;
    }

    public void generateBoards(Board board, char currentPlayer) {
        if (board.isWinner(currentPlayer, board.getBoardState())) {
            winnerBoards.add(board);
            return;
        }
        if(board.isFull(board.getBoardState()) &&
                !board.isWinner('x', board.getBoardState()) &&
                !board.isWinner('x', board.getBoardState())){
            drawBoards.add(board);
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoardState()[i][j] == ' ') {
                    board.getBoardState()[i][j] = currentPlayer;
                    generateBoards(board, currentPlayer == 'x' ? 'o' : 'x');
                    board.getBoardState()[i][j] = ' ';
                }
            }
        }
    }
}
