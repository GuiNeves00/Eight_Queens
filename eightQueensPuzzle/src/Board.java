public class Board {
    private char[][] gameBoard;

    public Board(char[][] gameBoard) {
        this.gameBoard = gameBoard;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.gameBoard[i][j] = '-';
            }
        }
    }

    public void showBoard() {
        System.out.println("--------------------------------");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(gameBoard[i][j]+"   ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
    }

    protected void reset() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.gameBoard[i][j] = '-';
            }
        }
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }
}

