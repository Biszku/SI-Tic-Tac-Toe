import java.util.Random;

public class Game {
    private char realPlayer;
    private char currentPlayer;
    private BoardController boardState;

    public Game() {
        Random random = new Random();
        realPlayer = random.nextInt(1,3) == 1 ? 'x' : 'o';
        currentPlayer = 'x';
        boardState = new BoardController();
        boardState.displayBoardState();
    }

    public void start() {
        while(true) {
            boardState.enterMove(currentPlayer, boardState.getBoardState());
            boolean winner = isWinner();
            if(winner) {
                System.out.println("Player " + currentPlayer + " won");
                return;
            };
            if(boardState.isFull(getBoard())) {
                System.out.println("This is draw");
                return;
            };
            changePlayer();
        }
    }

    public void changePlayer() {
        currentPlayer = currentPlayer == 'x' ? 'o' : 'x';
    }

    private boolean isWinner() {
        return boardState.isWinner(currentPlayer, boardState.getBoardState());
    }

    private char[][] getBoard() {
        return boardState.getBoardState();
    }
}
