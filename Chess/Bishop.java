import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(int xPosition, int yPosition, String color) {
        super(xPosition, yPosition, color);
    }

    public ArrayList<Move> validMoves(String[][] board) {
        ArrayList<Move> moves = new ArrayList<>();

        String ownColor = super.getColor();
        String oppositeColor = ownColor.equals("w") ? "b" : "w";

        for (int i = getYPosition() - 1, j = getXPosition() - 1; i >= 0 && j >= 0; i--, j--) {
            if (!board[i][j].startsWith(ownColor)) {
                moves.add(new Move(j, i));
                if (board[i][j].startsWith(oppositeColor)) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = getYPosition() - 1, j = getXPosition() + 1; i >= 0 && j <= 7; i--, j++) {
            if (!board[i][j].startsWith(ownColor)) {
                moves.add(new Move(j, i));
                if (board[i][j].startsWith(oppositeColor)) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = getYPosition() + 1, j = getXPosition() + 1; i <= 7 && j <= 7; i++, j++) {
            if (!board[i][j].startsWith(ownColor)) {
                moves.add(new Move(j, i));
                if (board[i][j].startsWith(oppositeColor)) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = getYPosition() + 1, j = getXPosition() - 1; i <= 7 && j >= 0; i++, j--) {
            if (!board[i][j].startsWith(ownColor)) {
                moves.add(new Move(j, i));
                if (board[i][j].startsWith(oppositeColor)) {
                    break;
                }
            } else {
                break;
            }
        }

        return moves;
    }

}
