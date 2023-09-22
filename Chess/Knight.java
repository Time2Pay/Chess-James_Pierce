import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(int xPosition, int yPosition, String color) {
        super(xPosition, yPosition, color);
    }

    public ArrayList<Move> validMoves(String[][] board) {
        ArrayList<Move> moves = new ArrayList<>();

        if ("w".equals(super.getColor())) {
            if (super.getYPosition() - 2 >= 0 && super.getXPosition() - 1 >= 0
                    && board[super.getYPosition() - 2][super.getXPosition() - 1].charAt(0) != 'w') {
                moves.add(new Move(super.getXPosition() - 1, super.getYPosition() - 2));
            }
            if (super.getYPosition() - 2 >= 0 && super.getXPosition() + 1 <= 7
                    && board[super.getYPosition() - 2][super.getXPosition() + 1].charAt(0) != 'w') {
                moves.add(new Move(super.getXPosition() + 1, super.getYPosition() - 2));
            }
            if (super.getYPosition() + 2 <= 7 && super.getXPosition() - 1 >= 0
                    && board[super.getYPosition() + 2][super.getXPosition() - 1].charAt(0) != 'w') {
                moves.add(new Move(super.getXPosition() - 1, super.getYPosition() + 2));
            }
            if (super.getYPosition() + 2 <= 7 && super.getXPosition() + 1 <= 7
                    && board[super.getYPosition() + 2][super.getXPosition() + 1].charAt(0) != 'w') {
                moves.add(new Move(super.getXPosition() + 1, super.getYPosition() + 2));
            }
            if (super.getYPosition() - 1 >= 0 && super.getXPosition() - 2 >= 0
                    && board[super.getYPosition() - 1][super.getXPosition() - 2].charAt(0) != 'w') {
                moves.add(new Move(super.getXPosition() - 2, super.getYPosition() - 1));
            }
            if (super.getYPosition() + 1 <= 7 && super.getXPosition() - 2 >= 0
                    && (board[super.getYPosition() + 1][super.getXPosition() - 2].charAt(0) != 'w')) {
                moves.add(new Move(super.getXPosition() - 2, super.getYPosition() + 1));
            }
            if (super.getYPosition() + 1 <= 7 && super.getXPosition() + 2 <= 7
                    && board[super.getYPosition() + 1][super.getXPosition() + 2].charAt(0) != 'w') {
                moves.add(new Move(super.getXPosition() + 2, super.getYPosition() + 1));
            }
            if (super.getYPosition() - 1 >= 0 && super.getXPosition() + 2 <= 7
                    && board[super.getYPosition() - 1][super.getXPosition() + 2].charAt(0) != 'w') {
                moves.add(new Move(super.getXPosition() + 2, super.getYPosition() - 1));
            }

        }
        if ("b".equals(super.getColor())) {
            if (super.getYPosition() - 2 >= 0 && super.getXPosition() - 1 >= 0
                    && board[super.getYPosition() - 2][super.getXPosition() - 1].charAt(0) != 'b') {
                moves.add(new Move(super.getXPosition() - 1, super.getYPosition() - 2));
            }
            if (super.getYPosition() - 2 >= 0 && super.getXPosition() + 1 <= 7
                    && board[super.getYPosition() - 2][super.getXPosition() + 1].charAt(0) != 'b') {
                moves.add(new Move(super.getXPosition() + 1, super.getYPosition() - 2));
            }
            if (super.getYPosition() + 2 <= 7 && super.getXPosition() - 1 >= 0
                    && board[super.getYPosition() + 2][super.getXPosition() - 1].charAt(0) != 'b') {
                moves.add(new Move(super.getXPosition() - 1, super.getYPosition() + 2));
            }
            if (super.getYPosition() + 2 <= 7 && super.getXPosition() + 1 <= 7
                    && board[super.getYPosition() + 2][super.getXPosition() + 1].charAt(0) != 'b') {
                moves.add(new Move(super.getXPosition() + 1, super.getYPosition() + 2));
            }
            if (super.getYPosition() - 1 >= 0 && super.getXPosition() - 2 >= 0
                    && board[super.getYPosition() - 1][super.getXPosition() - 2].charAt(0) != 'b') {
                moves.add(new Move(super.getXPosition() - 2, super.getYPosition() - 1));
            }
            if (super.getYPosition() + 1 <= 7 && super.getXPosition() - 2 >= 0
                    && board[super.getYPosition() + 1][super.getXPosition() - 2].charAt(0) != 'b') {
                moves.add(new Move(super.getXPosition() - 2, super.getYPosition() + 1));
            }
            if (super.getYPosition() + 1 <= 7 && super.getXPosition() + 2 <= 7
                    && board[super.getYPosition() + 1][super.getXPosition() + 2].charAt(0) != 'b') {
                moves.add(new Move(super.getXPosition() + 2, super.getYPosition() + 1));
            }
            if (super.getYPosition() - 1 >= 0 && super.getXPosition() + 2 <= 7
                    && board[super.getYPosition() - 1][super.getXPosition() + 2].charAt(0) != 'b') {
                moves.add(new Move(super.getXPosition() + 2, super.getYPosition() - 1));
            }

        }

        return moves;
    }
}
