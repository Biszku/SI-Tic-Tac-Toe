import gameController.Players;
import gameController.SiController;

import java.util.Random;

public class Game {
    private Players currentPlayer = Players.getInitialPlayer();
    private final Players realPlayer;
    private final Players siPlayer;
    private final SiController siController;

    public Game() {
        Random random = new Random();

        realPlayer = Players.getRandomPlayer();
        siPlayer = realPlayer.getOppositePlayer();

        siController = new SiController();
    }

    public void start() {
        siController.printBoard();
        while (true) {
            if(currentPlayer == realPlayer) siController.enterMove(currentPlayer);
            else {
                int[] move = siController.bestMove(currentPlayer);
                siController.makeMove(move[0], move[1], currentPlayer);
            }
            boolean winner = siController.isWinner();
            if (winner) {
                System.out.println("Player " + currentPlayer + " won");
                break;
            }
            if (siController.isFull()) {
                System.out.println("This is draw");
                break;
            }
            currentPlayer = currentPlayer.changePlayer();
        }
    }
}
