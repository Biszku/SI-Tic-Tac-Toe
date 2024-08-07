public class SiController extends Board {

    public SiController() {
        super();
    }

    public int[] bestMove(Players currentPlayer) {
        int bestScore = Integer.MIN_VALUE;
        int[] move = new int[2];
        char[][] board = getBoardState();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = currentPlayer.getPlayer();
                    int score = minimax(board, 0, false, currentPlayer);
                    board[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }
        return move;
    }

    private int minimax(char[][] board, int depth, boolean isMaximizing, Players currentPlayer) {
        if (isWinner()) {
            return isMaximizing ? -10 + depth : 10 - depth;
        }

        if (isFull()) {
            return 0;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        char curPlayer = currentPlayer.getPlayer();
        char opponent = curPlayer == 'O' ? 'X' : 'O';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = isMaximizing ? curPlayer : opponent;
                    int score = minimax(board, depth + 1, !isMaximizing, currentPlayer);
                    board[i][j] = ' ';

                    if (isMaximizing) {
                        bestScore = Math.max(score, bestScore);
                    } else {
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }
}
