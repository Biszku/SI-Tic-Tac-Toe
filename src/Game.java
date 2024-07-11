import java.util.Random;

public class Game {
    private final char realPlayer;
    private final char siPlayer;
    private char currentPlayer;
    private Board boardState;
    private SiController siController;

    public Game() {
        currentPlayer = 'x';
        Random random = new Random();

        realPlayer = random.nextInt(1,3) == 1 ? currentPlayer : 'o';
        siPlayer = realPlayer == currentPlayer ? 'o' : currentPlayer;

        boardState = new Board();

        siController = new SiController(siPlayer);
    }

    public void start() {
        boardState.displayBoardState();

        while (true) {
            System.out.println(siController.getWinnerBoards().size());
            System.out.println(siController.getDrawBoards().size());

            if(currentPlayer == realPlayer){
                boardState.enterMove(currentPlayer, getBoard());
            }
            else {
                System.out.println("Computer move");
                boardState.enterMove(currentPlayer, getBoard());
            }

            boolean winner = isWinner();
            if (winner) {
                System.out.println("Player " + currentPlayer + " won");
                return;
            }
            if (boardState.isFull(getBoard())) {
                System.out.println("This is draw");
                return;
            }
            siController.reduceBoards(getBoard());
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
