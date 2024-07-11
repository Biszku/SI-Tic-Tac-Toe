import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        char reversePlayer = player == 'x' ? 'o' : 'x';
        if(board.isWinner(reversePlayer, board.getBoardState())){
            return;
        }
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
    }

    public void makeMove(char currentPlayer,char[][] board){
        if(winnerBoards.isEmpty())
            findBestMove(currentPlayer, board, drawBoards.get(0));
        else
            findBestMove(currentPlayer, board, winnerBoards.get(0));
    }

    public void findBestMove(char currentPlayer, char[][] board, char[][] bestBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentPlayer == bestBoard[i][j] && board[i][j] == ' ') {
                    board[i][j] = currentPlayer;
                    System.out.println((i + 1) + " " + (j + 1));
                    displayBoardState(board);
                    return;
                }
            }
        }
    }

    public void displayBoardState(char[][] boardState) {
        for (int i = 0; i < 3; i++) {
            System.out.println(boardState[i][0] + " | " + boardState[i][1] + " | " + boardState[i][2]);
            if (i < 2) {
                System.out.println("---------");
            }
        }

        System.out.println();
    }
}
