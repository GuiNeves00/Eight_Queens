import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Solver extends Board {
    private final Random random;
    private int attempts = 1;

    public Solver(char[][] board) {
        super(board);
        random = new Random();
        insertQueenRandom();
    }

    public void solve() {
        while (!isGameOver()) {
            insertBestQueen();
            showBoard();
        }
        System.out.println("number of attempts: " + attempts);
    }

    private void insertQueenRandom() {
        int row = random.nextInt(8);
        int col = random.nextInt(8);
        getGameBoard()[row][col] = 'Q';
        drawAttack(row, col);
    }

    private void insertQueenAt(int row, int col) {
        if (isValid(row, col)) {
            getGameBoard()[row][col] = 'Q';
            drawAttack(row, col);
        }
    }
    private void insertQueenAt(int row, int col, char[][] copyBoard) {
        if (isValid(row, col, copyBoard)) {
            copyBoard[row][col] = 'Q';
            drawAttack(row, col, copyBoard);
        }
    }

    public void insertBestQueen() {
        // best queen -> position that takes the least amount of available ones
        // creates a copy from the current board and use it to find the best queen, positioning it on the real board
        int best = -1;
        int row = -1;
        int col = -1;
        char[][] copyBoard;

        copyBoard = Arrays.stream(getGameBoard())
                .map(char[]::clone)
                .toArray(char[][]::new);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValid(i, j, copyBoard)) {
                    insertQueenAt(i, j, copyBoard);
                    if(calcTotalBlankSpaces(copyBoard) > best) {
                        best = calcTotalBlankSpaces(copyBoard);
                        row = i;
                        col = j;
                    }
                }
                // even if copyboard is being reset, the indexes we're trying to add a queen is always different bc this is inside the loop
                copyBoard = Arrays.stream(getGameBoard())
                        .map(char[]::clone)
                        .toArray(char[][]::new);
            }
        }
        insertQueenAt(row, col);
    }

    private boolean isValid(int row, int col) {
        try {
            if (getGameBoard()[row][col] == '-') {
                return true;
            }
            return false;
        }catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
    private boolean isValid(int row, int col, char[][] copyBoard) {
        try {
            if (copyBoard[row][col] == '-') {
                return true;
            }
            return false;
        }catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean isGameOver() {
        int countQueen = 0;
        int countBlank = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getGameBoard()[i][j] == 'Q') {
                    countQueen += 1;
                }
                if(getGameBoard()[i][j] == '-') {
                    countBlank += 1;
                }
            }
        }
        if (countQueen == 8) {
            return true;
        } else if (countBlank == 0) {
            reset(); //resets the game
            insertQueenRandom();
            showBoard();
            attempts += 1;
            return false;
        }
        return false;
    }

    public int calcTotalBlankSpaces(char[][] copyBoard) {
        int blankSpaces = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (copyBoard[i][j] == '-') {
                    blankSpaces += 1;
                }
            }
        }
        return blankSpaces;
    }

    public void drawAttack(int row, int col) {

        //vertical
        for (int r = 0; r < 8; r++) {
            if (getGameBoard()[r][col] == '-') {
                getGameBoard()[r][col] = 'x';
            }
        }
        // horizontal
        for (int c = 0; c < 8; c++) {
            if (getGameBoard()[row][c] == '-') {
                getGameBoard()[row][c] = 'x';
            }
        }
        // up right diagonal
        for (int r = row, c = col; r >= 0 && c < 8; r--, c++) {
            if (getGameBoard()[r][c] == '-') {
                getGameBoard()[r][c] = 'x';
            }
        }
        // down right diagonal
        for (int r = row, c = col; r < 8 && c < 8; r++, c++) {
            if (getGameBoard()[r][c] == '-') {
                getGameBoard()[r][c] = 'x';
            }
        }
        // down left diagonal
        for (int r = row, c = col; r < 8 && c >= 0; r++, c--) {
            if (getGameBoard()[r][c] == '-') {
                getGameBoard()[r][c] = 'x';
            }
        }
        // up left diagonal
        for (int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
            if (getGameBoard()[r][c] == '-') {
                getGameBoard()[r][c] = 'x';
            }
        }
    }
    public void drawAttack(int row, int col, char[][] copyBoard) {

        //vertical
        for (int r = 0; r < 8; r++) {
            if (copyBoard[r][col] == '-') {
                copyBoard[r][col] = 'x';
            }
        }
        // horizontal
        for (int c = 0; c < 8; c++) {
            if (copyBoard[row][c] == '-') {
                copyBoard[row][c] = 'x';
            }
        }
        // up right diagonal
        for (int r = row, c = col; r >= 0 && c < 8; r--, c++) {
            if (copyBoard[r][c] == '-') {
                copyBoard[r][c] = 'x';
            }
        }
        // down right diagonal
        for (int r = row, c = col; r < 8 && c < 8; r++, c++) {
            if (copyBoard[r][c] == '-') {
                copyBoard[r][c] = 'x';
            }
        }
        // down left diagonal
        for (int r = row, c = col; r < 8 && c >= 0; r++, c--) {
            if (copyBoard[r][c] == '-') {
                copyBoard[r][c] = 'x';
            }
        }
        // up left diagonal
        for (int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
            if (copyBoard[r][c] == '-') {
                copyBoard[r][c] = 'x';
            }
        }
    }
}
