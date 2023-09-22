import java.util.ArrayList;

public class King extends Piece {

    public King(int xPosition, int yPosition, String color) {
        super(xPosition, yPosition, color);
    }

    public ArrayList<Move> validMoves(String[][] board) {
        ArrayList<Move> moves = new ArrayList<>();
        String ownColor = super.getColor();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int updatedY = getYPosition() + i;
                int updatedX = getXPosition() + j;
                if (0 <= updatedY
                        && updatedY <= 7
                        && 0 <= updatedX && updatedX <= 7 && !board[updatedY][updatedX].startsWith(ownColor)) {
                    moves.add(new Move(updatedX, updatedY));
                }

            }
        }

        return moves;
    }
}
