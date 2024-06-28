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

    public void showAllBoards() {
//        System.out.println(board.getAllBoard().size());

for(TicTacToeBoard board : board.getAllBoard()) {
            board.displayCurrentBoard();
        }
    }

    public void start() {
        board.displayCurrentBoard();

        while(true) {
            if(currentPlayer == realPlayer) board.enterMove(currentPlayer, board.getBoard());
            else board.enterMove(currentPlayer, board.getBoard());
            board.displayCurrentBoard();
            boolean isWinner = checkWinner(currentPlayer, board.getBoard());
            if(isWinner) break;
            changePlayer();
        }

        System.out.println("Player " + currentPlayer + " win");
    }

    public void changePlayer() {
        currentPlayer = currentPlayer == 'x' ? 'o' : 'x';
    }

    public boolean checkWinner(char player, char[][] board) {
        char[] win = new char[] {player, player, player};

        // horizontal checking
        for(char[] tab : board) {
            if(Arrays.equals(tab,win)) return true;
        }

        // vertical checking
        for(int i = 0; i < board.length; i++) {
            char[] newArr = new char[] {board[0][i],board[1][i],board[2][i]};
            if(Arrays.equals(newArr,win)) return true;
        }

        // cross checking
        char[] cross1 = new char[] {board[0][0],board[1][1],board[2][2]};
        char[] cross2 = new char[] {board[0][2],board[1][1],board[2][0]};
        if(Arrays.equals(cross1,win) || Arrays.equals(cross2,win)) return true;

        return false;
    }
}
