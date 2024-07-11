import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SiController {
    private List<char[][]> winnerBoards;
    private List<char[][]> drawBoards;

    public SiController(char siPlayer) {
        winnerBoards = new ArrayList<>();
        drawBoards = new ArrayList<>();
        generateBoards(new Board(), siPlayer, 'x');
    }

    public List<char[][]> getWinnerBoards() {
        return winnerBoards;
    }

    public List<char[][]> getDrawBoards() {
        return drawBoards;
    }

    public void generateBoards(Board board, char player, char currentPlayer) {
        if (board.isWinner(player, board.getBoardState())) {
            winnerBoards.add(copyArray(board.getBoardState()));
            return;
        }
        if(board.isFull(board.getBoardState()) &&
                !board.isWinner('x', board.getBoardState()) &&
                !board.isWinner('x', board.getBoardState())){
            drawBoards.add(copyArray(board.getBoardState()));
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoardState()[i][j] == ' ') {
                    board.getBoardState()[i][j] = currentPlayer;
                    generateBoards(board, player, currentPlayer == 'x' ? 'o' : 'x');
                    board.getBoardState()[i][j] = ' ';
                }
            }
        }
    }

    public char[][] copyArray(char[][] board){
        char[][] copy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copy;
    }

    public void reduceBoards(char[][] board){
        winnerBoards = reduceBoard(winnerBoards, board);
        drawBoards = reduceBoard(drawBoards, board);
    }

    public List<char[][]> reduceBoard(List<char[][]> boardType, char[][] board){
        List<char[][]> copy = new ArrayList<>();
        for (char[][] innerBoard : boardType) {
            boolean isSame = true;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(innerBoard[i][j] != board[i][j] && board[i][j] != ' '){
                        isSame = false;
                        break;
                    }
                }
                if(!isSame) break;
            }
            if(isSame)
                copy.add(innerBoard);
        }
        return copy;
    };
}
