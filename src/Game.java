import java.util.Random;

public class Game {
    private final char realPlayer;
    private final char siPlayer;
    private final SiController siController;

    public Game() {
        Random random = new Random();

        realPlayer = random.nextInt(2) == 0 ? 'x' : 'o';
        siPlayer = realPlayer == 'x' ? 'o' : 'x';

        siController = new SiController('x');
    }

    public void start() {
        siController.printBoard();
        while (true) {
            char currentPlayer = siController.getCurrentPlayer();
            if(currentPlayer == realPlayer){
                siController.enterMove();
            }
            else {
                int[] move = siController.bestMove();
                siController.makeMove(move[0], move[1]);
            }
            boolean winner = siController.isWinner();
            if (winner) {
                System.out.println("Player " + currentPlayer + " won");
                return;
            }
            if (siController.isFull()) {
                System.out.println("This is draw");
                return;
            }
            changePlayer();
        }
    }

    public void changePlayer() {
        char currentPlayer = siController.getCurrentPlayer() == 'x' ? 'o' : 'x';
        siController.setCurrentPlayer(currentPlayer);
    }
}
