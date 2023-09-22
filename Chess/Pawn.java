import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(int xPosition, int yPosition, String color) {
        super(xPosition, yPosition, color);
    }

    // returns an arrayList of all the valid moves for the selected pawn piece
    public ArrayList<Move> validMoves(String[][] board) {
        ArrayList<Move> moves = new ArrayList<>();
        if ("w".equals(super.getColor())) {
            if (super.getYPosition() > 0) {
                // if the pawn is white and can move 1 up, it can move 1 space
                if (board[super.getYPosition() - 1][super.getXPosition()].equals("00")) {
                    moves.add(new Move(super.getXPosition(), super.getYPosition() - 1));

                }
                // specific use case, so it uses specific numbers, if the 6th row pawn is white
                // and can move 2 and 1 spaces, it can move 2 or 1 spaces
                if (super.getYPosition() == 6) {

                    if (board[4][super.getXPosition()].equals("00") && board[5][super.getXPosition()].equals("00")) {

                        moves.add(new Move(super.getXPosition(), 4));
                    }

                }
                if (super.getXPosition() - 1 >= 0) {
                    if (!board[super.getYPosition() - 1][super.getXPosition() - 1].equals("00")
                            && board[super.getYPosition() - 1][super.getXPosition() - 1].startsWith("b")) {
                        moves.add(new Move(super.getXPosition() - 1, super.getYPosition() - 1));
                    }
                    /*
                     * if (super.getXPosition() != 0) {
                     * if (board[3][super.getXPosition() - 1].equals("bP")
                     * && board[3][super.getXPosition()].equals("wP")) {
                     * moves.add(new Move(super.getXPosition() - 1, 2));
                     * }
                     * }
                     */
                    /*
                     * if (super.getXPosition() != 7) {
                     * if (board[3][super.getXPosition() + 1].equals("bP")
                     * && board[3][super.getXPosition()].equals("wP")) {
                     * moves.add(new Move(super.getXPosition() + 1, 2));
                     * }
                     * }
                     */
                }
                if (super.getXPosition() + 1 <= 7) {
                    if (!board[super.getYPosition() - 1][super.getXPosition() + 1].equals("00")
                            && board[super.getYPosition() - 1][super.getXPosition() + 1].startsWith("b")) {
                        moves.add(new Move(super.getXPosition() + 1, super.getYPosition() - 1));
                    }
                    /*
                     * if (super.getXPosition() != 0) {
                     * if (board[3][super.getXPosition() - 1].equals("bP")
                     * && board[3][super.getXPosition()].equals("wP")) {
                     * moves.add(new Move(super.getXPosition() - 1, 2));
                     * }
                     * }
                     */
                    /*
                     * if (super.getXPosition() != 7) {
                     * 
                     * if (board[3][super.getXPosition() + 1].equals("bP")
                     * && board[3][super.getXPosition()].equals("wP")) {
                     * moves.add(new Move(super.getXPosition() + 1, 2));
                     * }
                     * }
                     */
                }

            }
        }
        if ("b".equals(super.getColor())) {
            if (super.getYPosition() < 7) {
                if (board[super.getYPosition() + 1][super.getXPosition()].equals("00")) {
                    moves.add(new Move(super.getXPosition(), super.getYPosition() + 1));
                }
                if (super.getYPosition() == 1 && board[super.getYPosition() + 1][super.getXPosition()].equals("00")
                        && board[super.getYPosition() + 2][super.getXPosition()].equals("00")) {

                    moves.add(new Move(super.getXPosition(), super.getYPosition() + 2));

                }
                if (super.getXPosition() + 1 <= 7) {
                    if (!board[super.getYPosition() + 1][super.getXPosition() + 1].equals("00")
                            && board[super.getYPosition() + 1][super.getXPosition() + 1].startsWith("w")) {
                        moves.add(new Move(super.getXPosition() + 1, super.getYPosition() + 1));
                    }
                    /*
                     * if (super.getXPosition() != 0) {
                     * if (board[4][super.getXPosition() - 1].equals("wP")
                     * && board[3][super.getXPosition()].equals("bP")) {
                     * moves.add(new Move(super.getXPosition() - 1, 5));
                     * }
                     * }
                     */
                    /*
                     * if (super.getXPosition() != 7) {
                     * if (board[4][super.getXPosition() + 1].equals("wP")
                     * && board[3][super.getXPosition()].equals("bP")) {
                     * moves.add(new Move(super.getXPosition() + 1, 5));
                     * }
                     * }
                     */
                }
                if (super.getXPosition() - 1 >= 0) {
                    if (!board[super.getYPosition() + 1][super.getXPosition() - 1].equals("00")
                            && board[super.getYPosition() + 1][super.getXPosition() - 1].startsWith("w")) {
                        moves.add(new Move(super.getXPosition() - 1, super.getYPosition() + 1));
                    }
                    /*
                     * if (super.getXPosition() != 0) {
                     * if (board[4][super.getXPosition() - 1].equals("wP")
                     * && board[3][super.getXPosition()].equals("bP")) {
                     * moves.add(new Move(super.getXPosition() - 1, 5));
                     * }
                     * }
                     */
                    /*
                     * if (super.getXPosition() != 7) {
                     * if (board[4][super.getXPosition() + 1].equals("wP")
                     * && board[3][super.getXPosition()].equals("bP")) {
                     * moves.add(new Move(super.getXPosition() + 1, 5));
                     * }
                     * }
                     */
                }
            }
        }

        // still need to implement en passant, pretty difficult rn, so will do later

        return moves;
    }

}
