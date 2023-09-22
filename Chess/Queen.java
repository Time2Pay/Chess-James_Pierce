import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(int xPosition, int yPosition, String color) {
        super(xPosition, yPosition, color);
    }

    // for transaprencies sake, I literally just copied both the functions of bishop
    // and rook
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

        for (int i = getYPosition() - 1; i >= 0; i--) {
            if (!board[i][super.getXPosition()].startsWith(ownColor)) {
                moves.add(new Move(super.getXPosition(), i));
                if (board[i][super.getXPosition()].startsWith(oppositeColor)) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = getYPosition() + 1; i <= 7; i++) {
            if (!board[i][super.getXPosition()].startsWith(ownColor)) {
                moves.add(new Move(super.getXPosition(), i));
                if (board[i][super.getXPosition()].startsWith(oppositeColor)) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = getXPosition() - 1; i >= 0; i--) {
            if (!board[super.getYPosition()][i].startsWith(ownColor)) {
                moves.add(new Move(i, super.getYPosition()));
                if (board[super.getYPosition()][i].startsWith(oppositeColor)) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = getXPosition() + 1; i <= 7; i++) {
            if (!board[super.getYPosition()][i].startsWith(ownColor)) {
                moves.add(new Move(i, super.getYPosition()));
                if (board[super.getYPosition()][i].startsWith(oppositeColor)) {
                    break;
                }
            } else {
                break;
            }
        }

        return moves;
    }

}
