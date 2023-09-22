public class Board {
    private String[][] board;
    private char[] orderedPieces = { 'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' };
    private String[] symbolPieces = { "\u2654", "\u265A", "\u2655", "\u265B", "\u2656", "\u265C", "\u2657",
            "\u265D", "\u2658", "\u265E", "\u2659", "\u265F" };
    private String[] symbolTranslation = { "wK", "bK", "wQ", "bQ", "wR", "bR", "wB", "bB", "wN", "bN", "wP", "bP" };

    public Board(int rows, int columns) {
        board = new String[rows][columns];
    }

    public String[][] getBoard() {
        return board;
    }

    public String getCell(int row, int column) {
        return board[row][column];
    }

    public void setCell(int row, int column, String value) {
        board[row][column] = value;
    }

    public void setBoardLine(int row, char color) {
        for (int i = 0; i < 8; i++) {
            setCell(row, i, color + String.valueOf(orderedPieces[i]));

        }

    }

    public void setPawnLine(int row, char color) {
        for (int i = 0; i < 8; i++) {
            setCell(row, i, color + "P");
        }

    }

    public void convertSymbol() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < symbolPieces.length; k++) {
                    if (board[i][j].equals(symbolTranslation[k])) {
                        board[i][j] = symbolPieces[k];

                    }
                }

            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

    }

}
