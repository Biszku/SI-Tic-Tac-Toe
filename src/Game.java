import java.util.Random;

public class Game {

//    char player;
    private TicTacToeBoard board;
    private char currentPlayer;

    public Game() {
        Random random = new Random();
//        player = random.nextInt(1,3) == 1 ? 'x' : 'o';
        board = new TicTacToeBoard();
        currentPlayer = 'x';
    }

    public void start() {
        board.displayCurrentBoard();

        while(true) {
            board.enterMove(currentPlayer);
            board.displayCurrentBoard();
            boolean isWinner = board.checkWinner(currentPlayer, board.getBoard());
            if(isWinner) break;
            changePlayer();
        }

        System.out.println("Player " + currentPlayer + " win");
    }

    public void changePlayer() {
        currentPlayer = currentPlayer == 'x' ? 'o' : 'x';
    }
}
