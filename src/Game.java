import java.util.Arrays;
import java.util.Random;

public class Game {

    private char realPlayer;
    private BoardController board;
    private char currentPlayer;

    public Game() {
        Random random = new Random();
        realPlayer = random.nextInt(1,3) == 1 ? 'x' : 'o';
        board = new BoardController();
        currentPlayer = 'x';
    }

    public void start() {
        board.displayCurrentBoard();

        while(true) {
            if(currentPlayer == realPlayer) board.enterMove(currentPlayer, board.getBoard());
            else board.enterMove(currentPlayer, board.getBoard());
            board.displayCurrentBoard();
            boolean isWinner = board.checkWinner(currentPlayer, board.getBoard());
            if(isWinner) break;
            if(board.isFull(board.getBoard())) {
                System.out.println("This is draw");
                return;
            };
            changePlayer();
        }

        System.out.println("Player " + currentPlayer + " win");
    }

    public void changePlayer() {
        currentPlayer = currentPlayer == 'x' ? 'o' : 'x';
    }
}
