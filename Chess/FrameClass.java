import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class FrameClass {
    private JFrame frame;
    private Board board;
    private int totalNumPieces;
    private ArrayList<Move> highlightedMoves = new ArrayList<>();
    private boolean isPieceSelected = false;
    private String prevColor = "0";
    private String oppColor;
    private String colorToMove = "w";
    private boolean successfullMove;
    private String prevPiece = "00";
    private int prevX;
    private int prevY;
    private boolean unneededRepaint;
    private boolean isKingChecked = false;
    private int blackKingX;
    private int blackKingY;
    private int whiteKingX;
    private int whiteKingY;
    private boolean willBeChecked;
    private boolean givePromotionVisualWhite;
    private boolean givePromotionVisualBlack;
    private boolean isCheckmate;
    private boolean isChecked;
    private boolean isStalematedCondition;

    private int whitePromotionPawnX;
    private int blackPromotionPawnX;
    private boolean blackWinScreen = false;
    private boolean whiteWinScreen = false;
    private boolean stalemateWinScreen = false;
    private boolean freeze;

    // BELOW ARE OPTIONS/CHANGEABLES
    private boolean doubleBoards = true;

    /*
     * For basically everything, it is completed, polished, and functional. Though I
     * will stop working on this project now, if in the future you stumble upon this
     * here are a few things that are still missing:
     * - Castling - a very important part of chess but hard to implement since both
     * the King and Rook must move and each possible moves are held in their
     * respective classes
     * - En Passant - a more minor rule but still a rule, too difficult to implement
     * as a possible move, since you need to input if the last move was a double
     * move, and the logic for that is not until FrameClass
     * -Different Draw rules - I have stalemate, probably the most common, but I
     * haven't implemented the 30 move rule and 3 repetitve move just bc of their
     * extra complicated logic
     * - A board switch feature- I was planning to add a feature to switch the board
     * perspective, but I decided against it bc that would overcomplicate the
     * simplistic Frame approach I have.
     * 
     * 
     * 
     * TO DO LIST / CURRENT ERRORS:
     * IMPLEMENT PROMOTION - DONE
     * IMPLEMENT A BOARD PERSPECTIVE SWITCH SYSTEM, OR PERHAPS TWO BOARDS SWITCHED
     * BETWEEN - LEAVE OUT FOR NOW
     * IMPLEMENT CHECKMATE FUNCTIONALITY - DONE
     * IMPLEMENT EN PASSANT - LEAVE OUT FOR NOW
     * IMPLEMENT KING CAPTURE FUNCTIONALITY - DONE
     * IMPLEMENT STALEMATE FUNCTIONALITY - DONE
     * ERROR: WHITE PAWNS WHEN ONLY GOING ONE MOVE, WHEN YOU CAN DO TWO WILL PROVIDE
     * AN EXTRA MOVE; ONLY FOR WHITE PAWNS, BLACK PAWNS ARE FINE - FIXED - SOLUTION
     * -> WHITE PAWNS WERE GIVEN THREE MOVES WHEN THEY SHOUL:DVE ONLY HAD TWO BC OF
     * THE INITIAL TWO SQUARE RULE
     * 
     */
    public FrameClass(Board board) {

        this.board = board;
        this.totalNumPieces = 32;
        ImageIcon blackPawn = new ImageIcon("blackPawn.png");
        ImageIcon blackBishop = new ImageIcon("blackBishop.png");
        ImageIcon blackKing = new ImageIcon("blackKing.png");
        ImageIcon blackKnight = new ImageIcon("blackKnight.png");
        ImageIcon blackQueen = new ImageIcon("blackQueen.png");
        ImageIcon blackRook = new ImageIcon("blackRook.png");
        ImageIcon whitePawn = new ImageIcon("whitePawn.png");
        ImageIcon whiteBishop = new ImageIcon("whiteBishop.png");
        ImageIcon whiteKing = new ImageIcon("whiteKing.png");
        ImageIcon whiteKnight = new ImageIcon("whiteKnight.png");
        ImageIcon whiteQueen = new ImageIcon("whiteQueen.png");
        ImageIcon whiteRook = new ImageIcon("whiteRook.png");
        ImageIcon blackWins = new ImageIcon("Black_Wins.png");
        ImageIcon whiteWins = new ImageIcon("White_Wins.png");
        ImageIcon stalemateWins = new ImageIcon("Stalemate.png");

        frame = new JFrame();

        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);
        JPanel pn = new JPanel() {
            @Override
            public void paint(Graphics g) {
                String[][] baseBoardArray = board.getBoard();
                boolean isWhite = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (isWhite) {
                            g.setColor(Color.white);
                        } else {
                            g.setColor(Color.gray);
                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        isWhite = !isWhite;
                    }
                    isWhite = !isWhite;
                }
                if (unneededRepaint) {
                    g.setColor(Color.GREEN);
                    for (Move move : highlightedMoves) {
                        g.fillRect(move.getToX() * 64, move.getToY() * 64, 64, 64);
                    }
                }

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        String piece = baseBoardArray[j][i];

                        if (piece.equals("wP")) {
                            g.drawImage(whitePawn.getImage(), i * 64, j * 64, 64, 64, this);
                        } else if (piece.equals("wR")) {
                            g.drawImage(whiteRook.getImage(), i * 64, j * 64, 64, 64, this);
                        } else if (piece.equals("wB")) {
                            g.drawImage(whiteBishop.getImage(), i * 64, j * 64, 64, 64, this);
                        } else if (piece.equals("wN")) {
                            g.drawImage(whiteKnight.getImage(), i * 64, j * 64, 64, 64, this);
                        } else if (piece.equals("wK")) {
                            g.drawImage(whiteKing.getImage(), i * 64, j * 64, 64, 64, this);
                        } else if (piece.equals("wQ")) {
                            g.drawImage(whiteQueen.getImage(), i * 64, j * 64, 64, 64, this);
                        } else if (piece.equals("bP")) {
                            g.drawImage(blackPawn.getImage(), i * 64, j * 64, 64, 64, this);
                        } else if (piece.equals("bR")) {
                            g.drawImage(blackRook.getImage(), i * 64, j * 64, 64, 64, this);
                        } else if (piece.equals("bB")) {
                            g.drawImage(blackBishop.getImage(), i * 64, j * 64, 64, 64, this);
                        } else if (piece.equals("bN")) {
                            g.drawImage(blackKnight.getImage(), i * 64, j * 64, 64, 64, this);
                        } else if (piece.equals("bK")) {
                            g.drawImage(blackKing.getImage(), i * 64, j * 64, 64, 64, this);
                        } else if (piece.equals("bQ")) {
                            g.drawImage(blackQueen.getImage(), i * 64, j * 64, 64, 64, this);
                        }
                    }
                }
                if (givePromotionVisualWhite) {
                    g.setColor(Color.black);
                    g.fillRect(35, 155, 410, 210);
                    g.setColor(Color.white);
                    g.fillRect(40, 160, 400, 200);

                    /*
                     * g.setColor(Color.black);
                     * g.fillRect(43, 195, 110, 110);
                     * g.setColor(Color.white);
                     * g.fillRect(47, 200, 100, 100);
                     * 
                     * g.setColor(Color.black);
                     * g.fillRect(148, 195, 110, 110);
                     * g.setColor(Color.white);
                     * g.fillRect(147, 200, 100, 100);
                     */
                    g.drawImage(whiteQueen.getImage(), 48, 200, 100, 100, this);
                    g.drawImage(whiteBishop.getImage(), 148, 200, 100, 100, this);
                    g.drawImage(whiteKnight.getImage(), 248, 200, 100, 100, this);
                    g.drawImage(whiteRook.getImage(), 348, 200, 100, 100, this);

                }
                if (givePromotionVisualBlack) {
                    g.setColor(Color.black);
                    g.fillRect(35, 155, 410, 210);
                    g.setColor(Color.white);
                    g.fillRect(40, 160, 400, 200);

                    g.drawImage(blackQueen.getImage(), 48, 200, 100, 100, this);
                    g.drawImage(blackBishop.getImage(), 148, 200, 100, 100, this);
                    g.drawImage(blackKnight.getImage(), 248, 200, 100, 100, this);
                    g.drawImage(blackRook.getImage(), 348, 200, 100, 100, this);
                }
                if (blackWinScreen) {
                    g.setColor(Color.black);
                    g.fillRect(35, 155, 420, 210);
                    g.setColor(Color.white);
                    g.fillRect(40, 160, 410, 200);

                    g.drawImage(blackWins.getImage(), -70, 233, 700, 100, this);
                    g.drawImage(blackKing.getImage(), 30, 200, 100, 100, this);

                    freeze = true;
                } else if (whiteWinScreen) {
                    g.setColor(Color.black);
                    g.fillRect(35, 155, 440, 210);
                    g.setColor(Color.white);
                    g.fillRect(40, 160, 430, 200);

                    g.drawImage(whiteWins.getImage(), -5, 233, 600, 100, this);
                    g.drawImage(whiteKing.getImage(), 30, 200, 100, 100, this);

                    freeze = true;
                } else if (stalemateWinScreen) {
                    g.setColor(Color.black);
                    g.fillRect(35, 155, 463, 210);
                    g.setColor(Color.white);
                    g.fillRect(40, 160, 453, 200);

                    g.drawImage(whiteKing.getImage(), 30, 200, 100, 100, this);
                    g.drawImage(stalemateWins.getImage(), -130, 213, 800, 166, this);
                    g.drawImage(blackKing.getImage(), 400, 200, 100, 100, this);

                    freeze = true;
                }

            }
        };

        frame.add(pn);

        frame.setDefaultCloseOperation(3);
        if (!freeze) {
            pn.addMouseListener(new MouseAdapter() {

                String[][] baseBoardArray = board.getBoard();

                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println("Frozen Status is" + freeze);
                    // System.out.println("Your Current X Coordinate is: " + e.getX());
                    // System.out.println("Your Current Y Coordinate is: " + e.getY());
                    int pressedX = e.getX() / 64;
                    int pressedY = e.getY() / 64;

                    String piece = baseBoardArray[pressedY][pressedX];

                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (baseBoardArray[i][j].equals("bK")) {
                                blackKingX = j;
                                blackKingY = i;
                            } else if (baseBoardArray[i][j].equals("wK")) {
                                whiteKingX = j;
                                whiteKingY = i;
                            }
                        }
                    }

                    if (isPieceSelected && prevColor.equals(colorToMove)) {
                        successfullMove = false;
                        System.out.println("You can't put yourself in check white king");

                        for (Move move : highlightedMoves) {

                            if (move.getToX() == pressedX && move.getToY() == pressedY) {
                                String originalState = board.getCell(move.getToY(), move.getToX());
                                board.setCell(move.getToY(), move.getToX(),
                                        prevPiece);
                                board.setCell(prevY, prevX, "00");
                                oppColor = prevColor.equals("w") ? "b" : "w";
                                ArrayList<Move> allPossibleMoves = allPossibleMoves(oppColor);
                                willBeChecked = false;
                                for (Move check : allPossibleMoves) {
                                    if (prevColor.equals("w")) {
                                        for (int i = 0; i < 8; i++) {
                                            for (int j = 0; j < 8; j++) {
                                                if (baseBoardArray[i][j].equals("bK")) {
                                                    blackKingX = j;
                                                    blackKingY = i;
                                                } else if (baseBoardArray[i][j].equals("wK")) {
                                                    whiteKingX = j;
                                                    whiteKingY = i;
                                                }
                                            }
                                        }
                                        if (check.getToX() == whiteKingX && check.getToY() == whiteKingY) {

                                            board.setCell(prevY, prevX, prevPiece);
                                            board.setCell(move.getToY(), move.getToX(), originalState);
                                            willBeChecked = true;

                                        }
                                    } else if (prevColor.equals("b")) {
                                        for (int i = 0; i < 8; i++) {
                                            for (int j = 0; j < 8; j++) {
                                                if (baseBoardArray[i][j].equals("bK")) {
                                                    blackKingX = j;
                                                    blackKingY = i;
                                                } else if (baseBoardArray[i][j].equals("wK")) {
                                                    whiteKingX = j;
                                                    whiteKingY = i;
                                                }
                                            }
                                        }
                                        if (check.getToX() == blackKingX && check.getToY() == blackKingY) {
                                            System.out.println("You can't put yourself in check black king");

                                            board.setCell(prevY, prevX, prevPiece);
                                            board.setCell(move.getToY(), move.getToX(), originalState);
                                            willBeChecked = true;

                                        }
                                    }

                                }

                                /*
                                 * LOGIC FOR CHECKMATE & STALEMATE FUNCTIONALITY - DIFFICULT
                                 * 
                                 * for(Move opposingCheck : allPossibleMovesWhite){
                                 * for(Move defendingCheck : allPossibleMovesBlack){
                                 * 
                                 * board.setCell(defendingCheck.getToX(), defendingCheck.getToY(), )
                                 * }
                                 * }
                                 */
                                if (!willBeChecked) {

                                    givePromotionVisualWhite = false;
                                    givePromotionVisualBlack = false;
                                    board.setCell(move.getToY(), move.getToX(),
                                            prevPiece);
                                    board.setCell(prevY, prevX, "00");
                                    if (prevPiece.equals("wP")) {
                                        if (move.getToY() == 0) {
                                            givePromotionVisualWhite = true;
                                            whitePromotionPawnX = move.getToX();

                                        }
                                    } else if (prevPiece.equals("bP")) {
                                        if (move.getToY() == 7) {

                                            givePromotionVisualBlack = true;
                                            blackPromotionPawnX = move.getToX();
                                        }
                                    }

                                    // System.out.println("Successfull Move!");
                                    // System.out.println(prevColor);
                                    // System.out.println(prevPiece);
                                    // System.out.println("opposite color is" + oppColor);
                                    board.displayBoard();
                                    successfullMove = true;
                                    unneededRepaint = false;
                                    // System.out.println("unneedeRepaint Val:" + unneededRepaint);
                                    pn.repaint();
                                    // System.out.println("Post Repaint");
                                    // System.out.println("unneedeRepaint Val Post:" + unneededRepaint);

                                    pressedX = move.getToX();
                                    pressedY = move.getToY();
                                    colorToMove = prevColor.equals("w") ? "b" : "w";
                                    prevColor = "0";
                                }
                            }

                        }

                        if (!successfullMove && !piece.startsWith(colorToMove)) {
                            pressedX = prevX;
                            pressedY = prevY;
                            piece = baseBoardArray[pressedY][pressedX];

                            System.out.println("Invalid Move");
                        } else {
                            // System.out.println("That is your color");
                        }

                    }
                    if (colorToMove.equals("w")) {
                        isCheckmate = checkForCheckmateWhite();
                        isChecked = isChecked("w");
                        isStalematedCondition = checkForStalemateCondition("w", baseBoardArray);

                        if (isCheckmate && isChecked) {
                            System.out.println("you are checkmated white");
                            blackWinScreen = true;

                        } else if (isCheckmate && !isChecked && isStalematedCondition) {
                            System.out.println("you are stalemated white");
                            stalemateWinScreen = true;

                        } else {
                            System.out.println("checked for checkmate and stalemate, you are neither white");
                        }
                        isCheckmate = false;
                        isChecked = false;
                        isStalematedCondition = false;
                    }
                    if (colorToMove.equals("b")) {
                        System.out
                                .println("Right before callikng if isCheckmate and isChecked on black");
                        isCheckmate = checkForCheckmateBlack();
                        isChecked = isChecked("b");
                        isStalematedCondition = checkForStalemateCondition("b", baseBoardArray);
                        if (isCheckmate && isChecked) {
                            System.out.println("You are CheckMated black");
                            whiteWinScreen = true;

                        } else if (isCheckmate && !isChecked && isStalematedCondition) {
                            System.out.println("You are stalemated black");
                            stalemateWinScreen = true;

                        } else {
                            System.out.println(
                                    "Checked for checkmate and stalemate, you are neither black");
                        }
                        isCheckmate = false;
                        isChecked = false;
                        isStalematedCondition = false;
                    }

                    if (givePromotionVisualWhite) {
                        /*
                         * try {
                         * Thread.sleep(1000);
                         * } catch (InterruptedException ev) {
                         * System.out.println("Hit an Interrupted Exception, e: " + ev);
                         * }
                         */

                        if (e.getX() >= 45 && e.getX() <= 153 && e.getY() >= 209 && e.getY() <= 288) {
                            board.setCell(0, whitePromotionPawnX, "wQ");
                            givePromotionVisualWhite = false;
                            pn.repaint();

                        } else if (e.getX() >= 153 && e.getX() <= 243 && e.getY() >= 209 && e.getY() <= 288) {
                            board.setCell(0, whitePromotionPawnX, "wB");
                            givePromotionVisualWhite = false;
                            pn.repaint();

                        } else if (e.getX() >= 243 && e.getX() <= 352 && e.getY() >= 209 && e.getY() <= 288) {
                            board.setCell(0, whitePromotionPawnX, "wN");
                            givePromotionVisualWhite = false;
                            pn.repaint();

                        } else if (e.getX() >= 352 && e.getX() <= 436 && e.getY() >= 209 && e.getY() <= 288) {
                            board.setCell(0, whitePromotionPawnX, "wR");
                            givePromotionVisualWhite = false;
                            pn.repaint();

                        }

                    }
                    if (givePromotionVisualBlack) {
                        if (e.getX() >= 45 && e.getX() <= 153 && e.getY() >= 209 && e.getY() <= 288) {
                            board.setCell(7, blackPromotionPawnX, "bQ");
                            givePromotionVisualBlack = false;
                            pn.repaint();

                        } else if (e.getX() >= 153 && e.getX() <= 243 && e.getY() >= 209 && e.getY() <= 288) {
                            board.setCell(7, blackPromotionPawnX, "bB");
                            givePromotionVisualBlack = false;
                            pn.repaint();

                        } else if (e.getX() >= 243 && e.getX() <= 352 && e.getY() >= 209 && e.getY() <= 288) {
                            board.setCell(7, blackPromotionPawnX, "bN");
                            givePromotionVisualBlack = false;
                            pn.repaint();

                        } else if (e.getX() >= 352 && e.getX() <= 436 && e.getY() >= 209 && e.getY() <= 288) {
                            board.setCell(7, blackPromotionPawnX, "bR");
                            givePromotionVisualBlack = false;
                            pn.repaint();

                        }

                    }
                    piece = baseBoardArray[pressedY][pressedX];

                    if (piece.endsWith("B") && (piece.startsWith(colorToMove)
                            && (prevColor.equals("0") || prevColor.equals(colorToMove)))) {
                        Bishop currentBishop = new Bishop(pressedX, pressedY, Character.toString(piece.charAt(0)));
                        ArrayList<Move> valids = currentBishop.validMoves(baseBoardArray);
                        highlightedMoves = valids;
                        unneededRepaint = true;
                        pn.repaint();
                        isPieceSelected = true;
                        prevColor = Character.toString(piece.charAt(0));
                        prevPiece = prevColor + "B";
                        prevX = pressedX;
                        prevY = pressedY;

                        // System.out.println(valids);
                    } else if (piece.endsWith("R") && (piece.startsWith(colorToMove)
                            && (prevColor.equals("0") || prevColor.equals(colorToMove)))) {
                        Rook currentRook = new Rook(pressedX, pressedY, Character.toString(piece.charAt(0)));
                        ArrayList<Move> valids = currentRook.validMoves(baseBoardArray);
                        // System.out.println(valids);
                        highlightedMoves = valids;
                        unneededRepaint = true;
                        pn.repaint();
                        isPieceSelected = true;
                        prevColor = Character.toString(piece.charAt(0));
                        prevPiece = prevColor + "R";
                        prevX = pressedX;
                        prevY = pressedY;
                    } else if (piece.endsWith("N") && (piece.startsWith(colorToMove)
                            && (prevColor.equals("0") || prevColor.equals(colorToMove)))) {
                        Knight currentKnight = new Knight(pressedX, pressedY, Character.toString(piece.charAt(0)));
                        ArrayList<Move> valids = currentKnight.validMoves(baseBoardArray);
                        // System.out.println(valids);
                        highlightedMoves = valids;
                        unneededRepaint = true;
                        pn.repaint();
                        isPieceSelected = true;
                        prevColor = Character.toString(piece.charAt(0));
                        prevPiece = prevColor + "N";
                        prevX = pressedX;
                        prevY = pressedY;
                    } else if (piece.endsWith("Q") && (piece.startsWith(colorToMove)
                            && (prevColor.equals("0") || prevColor.equals(colorToMove)))) {
                        Queen currentQueen = new Queen(pressedX, pressedY, Character.toString(piece.charAt(0)));
                        ArrayList<Move> valids = currentQueen.validMoves(baseBoardArray);
                        // System.out.println(valids);
                        highlightedMoves = valids;
                        unneededRepaint = true;
                        pn.repaint();
                        isPieceSelected = true;
                        prevColor = Character.toString(piece.charAt(0));
                        prevPiece = prevColor + "Q";
                        prevX = pressedX;
                        prevY = pressedY;
                    } else if (piece.endsWith("K") && (piece.startsWith(colorToMove)
                            && (prevColor.equals("0") || prevColor.equals(colorToMove)))) {
                        King currentKing = new King(pressedX, pressedY, Character.toString(piece.charAt(0)));
                        ArrayList<Move> valids = currentKing.validMoves(baseBoardArray);
                        // System.out.println(valids);
                        highlightedMoves = valids;
                        unneededRepaint = true;
                        pn.repaint();
                        isPieceSelected = true;
                        prevColor = Character.toString(piece.charAt(0));
                        prevPiece = prevColor + "K";
                        prevX = pressedX;
                        prevY = pressedY;
                    } else if (piece.endsWith("P") && (piece.startsWith(colorToMove)
                            && (prevColor.equals("0") || prevColor.equals(colorToMove)))) {
                        Pawn currentPawn = new Pawn(pressedX, pressedY, Character.toString(piece.charAt(0)));
                        ArrayList<Move> valids = currentPawn.validMoves(baseBoardArray);
                        // Move firstValid = valids.get(0);
                        // Move secondValid = valids.get(1);
                        // Move thirdValid = valids.get(2);
                        // System.out.println(firstValid.getToX() + "," + firstValid.getToY());
                        // System.out.println(secondValid.getToX() + "," + secondValid.getToY());
                        // System.out.println(thirdValid.getToX() + "," + thirdValid.getToY());

                        highlightedMoves = valids;
                        unneededRepaint = true;
                        pn.repaint();
                        isPieceSelected = true;
                        prevColor = Character.toString(piece.charAt(0));
                        prevPiece = prevColor + "P";
                        prevX = pressedX;
                        prevY = pressedY;

                        /*
                         * Example of how to use the toX and toY, remember valids is an arraylist so you
                         * can't do 1 thing to all, have to make an element a part of the Move class to
                         * use getToX() and getToY()
                         * Move firstValid = valids.get(0);
                         * System.out.println(firstValid.getToX() + "," + firstValid.getToY());
                         */
                        // System.out.println(valids);
                    } else {
                        prevPiece = "00";
                    }

                    System.out.println(piece + " Was Pressed");

                }

                /*
                 * @Override
                 * public void mouseReleased(MouseEvent e) {
                 * int releasedX = e.getX() / 64;
                 * int releasedY = e.getY() / 64;
                 * 
                 * String piece = baseBoardArray[releasedY][releasedX];
                 * 
                 * if (piece.equals("bP")) {
                 * System.out.println("Black pawn has been dropped");
                 * }
                 * else if(piece.equals("bR"){
                 * System.out.println()
                 * })
                 * }
                 */
            });

        }
    }
    // color is one letter like "w" or "c"

    public ArrayList<Move> allPossibleMoves(String color) {
        String[][] baseBoardArr = board.getBoard();

        String[][] boardCopy = new String[8][8];
        for (int r = 0; r < baseBoardArr.length; r++) {
            for (int d = 0; d < baseBoardArr[r].length; d++) {
                boardCopy[r][d] = baseBoardArr[r][d];
            }
        }

        ArrayList<Move> totalMoves = new ArrayList<Move>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardCopy[i][j].endsWith("B") && boardCopy[i][j].startsWith(color)) {
                    Bishop arrayedBishop = new Bishop(j, i, color);
                    ArrayList<Move> arrayBishopMoves = arrayedBishop.validMoves(boardCopy);
                    totalMoves.addAll(arrayBishopMoves);

                } else if (boardCopy[i][j].endsWith("P") && boardCopy[i][j].startsWith(color)) {
                    Pawn arrayedPawn = new Pawn(j, i, color);
                    ArrayList<Move> arrayPawnMoves = arrayedPawn.validMoves(boardCopy);
                    totalMoves.addAll(arrayPawnMoves);
                } else if (boardCopy[i][j].endsWith("R") && boardCopy[i][j].startsWith(color)) {
                    Rook arrayedRook = new Rook(j, i, color);
                    ArrayList<Move> arrayRookMoves = arrayedRook.validMoves(boardCopy);
                    totalMoves.addAll(arrayRookMoves);
                } else if (boardCopy[i][j].endsWith("N") && boardCopy[i][j].startsWith(color)) {
                    Knight arrayedKnight = new Knight(j, i, color);
                    ArrayList<Move> arrayKnightMoves = arrayedKnight.validMoves(boardCopy);
                    totalMoves.addAll(arrayKnightMoves);
                } else if (boardCopy[i][j].endsWith("Q") && boardCopy[i][j].startsWith(color)) {
                    Queen arrayedQueen = new Queen(j, i, color);
                    ArrayList<Move> arrayQueenMoves = arrayedQueen.validMoves(boardCopy);
                    totalMoves.addAll(arrayQueenMoves);
                }

                else if (boardCopy[i][j].endsWith("K") && boardCopy[i][j].startsWith(color)) {
                    King arrayedKing = new King(j, i, color);
                    ArrayList<Move> arrayKingMoves = arrayedKing.validMoves(boardCopy);
                    totalMoves.addAll(arrayKingMoves);
                }
            }
        }
        return totalMoves;

    }

    public int convertPieceToNumber(String piece) {
        if (piece.endsWith("P")) {
            return 0;
        } else if (piece.endsWith("R")) {
            return 1;
        } else if (piece.endsWith("Q")) {
            return 2;
        } else if (piece.endsWith("B")) {
            return 3;
        } else if (piece.endsWith("N")) {
            return 4;
        } else if (piece.endsWith("K")) {
            return 5;
        } else {
            return 90;
        }
    }

    public boolean checkForCheckmateWhite() {
        String[][] baseBoardArr = board.getBoard();

        String[][] boardCopyCheck = new String[8][8];
        for (int r = 0; r < baseBoardArr.length; r++) {
            for (int d = 0; d < baseBoardArr[r].length; d++) {
                boardCopyCheck[r][d] = baseBoardArr[r][d];
            }
        }

        // key for Pieces is 0 - Pawn, 1 - Rook, 2 - Queen, 3 -Bishop, 4 - Knight,
        // 5-King
        int[][][] counts = new int[8][8][6];

        int whiteKingCheckX = 4;
        int whiteKingCheckY = 7;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (boardCopyCheck[i][j].equals("wK")) {
                    whiteKingCheckX = j;
                    whiteKingCheckY = i;
                }
            }
        }
        ArrayList<Move> allPossibleBlackMoves = allPossibleMoves("b");
        for (Move opposingCheck : allPossibleBlackMoves) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    if (boardCopyCheck[i][j].endsWith("B") && boardCopyCheck[i][j].startsWith("w")) {
                        // bishop assigns to ishop board using constructor
                        Bishop arrayedBishopCheck = new Bishop(j, i, "w");
                        ArrayList<Move> arrayBishopMovesCheck = arrayedBishopCheck.validMoves(boardCopyCheck);
                        for (Move defensiveBishop : arrayBishopMovesCheck) {
                            boardCopyCheck[i][j] = "00";
                            boardCopyCheck[defensiveBishop.getToY()][defensiveBishop.getToX()] = "wB";
                            if (opposingCheck.getToX() == whiteKingCheckX
                                    && opposingCheck.getToY() == whiteKingCheckY) {

                            } else {
                                counts[defensiveBishop.getToX()][defensiveBishop.getToY()][convertPieceToNumber(
                                        "wB")]++;

                            }

                            boardCopyCheck[i][j] = "wB";
                            boardCopyCheck[defensiveBishop.getToY()][defensiveBishop.getToX()] = "00";
                        }

                    } else if (boardCopyCheck[i][j].endsWith("P") && boardCopyCheck[i][j].startsWith("w")) {
                        Pawn arrayedPawnCheck = new Pawn(j, i, "w");
                        ArrayList<Move> arrayPawnMovesCheck = arrayedPawnCheck.validMoves(boardCopyCheck);
                        for (Move defensivePawn : arrayPawnMovesCheck) {
                            boardCopyCheck[i][j] = "00";
                            boardCopyCheck[defensivePawn.getToY()][defensivePawn.getToX()] = "wP";
                            if (opposingCheck.getToX() == whiteKingCheckX
                                    && opposingCheck.getToY() == whiteKingCheckY) {

                            } else {
                                counts[defensivePawn.getToX()][defensivePawn.getToY()][convertPieceToNumber("wP")]++;
                            }
                            boardCopyCheck[i][j] = "wP";
                            boardCopyCheck[defensivePawn.getToY()][defensivePawn.getToX()] = "00";
                        }

                    } else if (boardCopyCheck[i][j].endsWith("R") && boardCopyCheck[i][j].startsWith("w")) {
                        Rook arrayedRookCheck = new Rook(j, i, "w");
                        ArrayList<Move> arrayRookMovesCheck = arrayedRookCheck.validMoves(boardCopyCheck);
                        for (Move defensiveRook : arrayRookMovesCheck) {
                            boardCopyCheck[i][j] = "00";
                            boardCopyCheck[defensiveRook.getToY()][defensiveRook.getToX()] = "wR";
                            if (opposingCheck.getToX() == whiteKingCheckX
                                    && opposingCheck.getToY() == whiteKingCheckY) {

                            } else {
                                counts[defensiveRook.getToX()][defensiveRook.getToY()][convertPieceToNumber("wR")]++;
                            }
                            boardCopyCheck[i][j] = "wR";
                            boardCopyCheck[defensiveRook.getToY()][defensiveRook.getToX()] = "00";
                        }

                    } else if (boardCopyCheck[i][j].endsWith("N") && boardCopyCheck[i][j].startsWith("w")) {
                        Knight arrayedKnightCheck = new Knight(j, i, "w");
                        ArrayList<Move> arrayKnightMovesCheck = arrayedKnightCheck.validMoves(boardCopyCheck);
                        for (Move defensiveKnight : arrayKnightMovesCheck) {
                            boardCopyCheck[i][j] = "0";
                            boardCopyCheck[defensiveKnight.getToY()][defensiveKnight.getToX()] = "wN";
                            if (opposingCheck.getToY() == whiteKingCheckY
                                    && opposingCheck.getToX() == whiteKingCheckX) {

                            } else {
                                counts[defensiveKnight.getToX()][defensiveKnight.getToY()][convertPieceToNumber(
                                        "wN")]++;
                            }
                            boardCopyCheck[i][j] = "wN";
                            boardCopyCheck[defensiveKnight.getToY()][defensiveKnight.getToX()] = "00";
                        }

                    } else if (boardCopyCheck[i][j].endsWith("Q") && boardCopyCheck[i][j].startsWith("w")) {
                        Queen arrayedQueenCheck = new Queen(j, i, "w");
                        ArrayList<Move> arrayQueenMovesCheck = arrayedQueenCheck.validMoves(boardCopyCheck);
                        for (Move defensiveQueen : arrayQueenMovesCheck) {
                            boardCopyCheck[i][j] = "00";
                            boardCopyCheck[defensiveQueen.getToY()][defensiveQueen.getToX()] = "wQ";
                            if (opposingCheck.getToX() == whiteKingCheckX
                                    && opposingCheck.getToY() == whiteKingCheckY) {

                            } else {
                                counts[defensiveQueen.getToX()][defensiveQueen.getToY()][convertPieceToNumber("wQ")]++;
                            }
                            boardCopyCheck[i][j] = "wQ";
                            boardCopyCheck[defensiveQueen.getToY()][defensiveQueen.getToX()] = "00";
                        }

                    }

                    else if (boardCopyCheck[i][j].endsWith("K") && boardCopyCheck[i][j].startsWith("w")) {
                        King arrayedKingCheck = new King(j, i, "w");
                        ArrayList<Move> arrayKingMovesCheck = arrayedKingCheck.validMoves(boardCopyCheck);
                        for (Move defensiveKing : arrayKingMovesCheck) {
                            boardCopyCheck[i][j] = "00";
                            boardCopyCheck[defensiveKing.getToY()][defensiveKing.getToX()] = "wK";
                            whiteKingCheckX = defensiveKing.getToX();
                            whiteKingCheckY = defensiveKing.getToY();
                            if (opposingCheck.getToX() == whiteKingCheckX
                                    && opposingCheck.getToY() == whiteKingCheckY) {

                            } else {
                                counts[defensiveKing.getToX()][defensiveKing.getToY()][convertPieceToNumber("wK")]++;
                            }
                            boardCopyCheck[i][j] = "wK";

                            boardCopyCheck[defensiveKing.getToY()][defensiveKing.getToX()] = "00";
                            whiteKingCheckX = j;
                            whiteKingCheckY = i;

                        }

                    }
                }
            }
        }
        // System.out.println("All Poss Move Size is: " + allPossibleBlackMoves.size());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 6; k++) {
                    // System.out.println(counts[i][j][k]);
                    if (counts[i][j][k] >= allPossibleMoves("b").size()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkForCheckmateBlack() {
        String[][] baseBoardArr = board.getBoard();

        String[][] boardCopyCheck = new String[8][8];
        for (int r = 0; r < baseBoardArr.length; r++) {
            for (int d = 0; d < baseBoardArr[r].length; d++) {
                boardCopyCheck[r][d] = baseBoardArr[r][d];
            }
        }

        // key for Pieces is 0 - Pawn, 1 - Rook, 2 - Queen, 3 -Bishop, 4 - Knight,
        // 5-King
        int[][][] counts = new int[8][8][6];

        int blackKingCheckX = 4;
        int blackKingCheckY = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (boardCopyCheck[i][j].equals("bK")) {
                    blackKingCheckX = j;
                    blackKingCheckY = i;
                }
            }
        }
        ArrayList<Move> allPossibleWhiteMoves = allPossibleMoves("w");
        for (Move opposingCheck : allPossibleWhiteMoves) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    if (boardCopyCheck[i][j].endsWith("B") && boardCopyCheck[i][j].startsWith("b")) {
                        // bishop assigns to ishop board using constructor
                        Bishop arrayedBishopCheck = new Bishop(j, i, "b");
                        ArrayList<Move> arrayBishopMovesCheck = arrayedBishopCheck.validMoves(boardCopyCheck);
                        for (Move defensiveBishop : arrayBishopMovesCheck) {
                            boardCopyCheck[i][j] = "00";
                            boardCopyCheck[defensiveBishop.getToY()][defensiveBishop.getToX()] = "bB";
                            if (opposingCheck.getToX() == blackKingCheckX
                                    && opposingCheck.getToY() == blackKingCheckY) {

                            } else {
                                counts[defensiveBishop.getToX()][defensiveBishop.getToY()][convertPieceToNumber(
                                        "bB")]++;

                            }

                            boardCopyCheck[i][j] = "bB";
                            boardCopyCheck[defensiveBishop.getToY()][defensiveBishop.getToX()] = "00";
                        }

                    } else if (boardCopyCheck[i][j].endsWith("P") && boardCopyCheck[i][j].startsWith("b")) {
                        Pawn arrayedPawnCheck = new Pawn(j, i, "b");
                        ArrayList<Move> arrayPawnMovesCheck = arrayedPawnCheck.validMoves(boardCopyCheck);
                        for (Move defensivePawn : arrayPawnMovesCheck) {
                            boardCopyCheck[i][j] = "00";
                            boardCopyCheck[defensivePawn.getToY()][defensivePawn.getToX()] = "bP";
                            if (opposingCheck.getToX() == blackKingCheckX
                                    && opposingCheck.getToY() == blackKingCheckY) {

                            } else {
                                counts[defensivePawn.getToX()][defensivePawn.getToY()][convertPieceToNumber("bP")]++;
                            }
                            boardCopyCheck[i][j] = "bP";
                            boardCopyCheck[defensivePawn.getToY()][defensivePawn.getToX()] = "00";
                        }

                    } else if (boardCopyCheck[i][j].endsWith("R") && boardCopyCheck[i][j].startsWith("b")) {
                        Rook arrayedRookCheck = new Rook(j, i, "b");
                        ArrayList<Move> arrayRookMovesCheck = arrayedRookCheck.validMoves(boardCopyCheck);
                        for (Move defensiveRook : arrayRookMovesCheck) {
                            boardCopyCheck[i][j] = "00";
                            boardCopyCheck[defensiveRook.getToY()][defensiveRook.getToX()] = "bR";
                            if (opposingCheck.getToX() == blackKingCheckX
                                    && opposingCheck.getToY() == blackKingCheckY) {

                            } else {
                                counts[defensiveRook.getToX()][defensiveRook.getToY()][convertPieceToNumber("bR")]++;
                            }
                            boardCopyCheck[i][j] = "bR";
                            boardCopyCheck[defensiveRook.getToY()][defensiveRook.getToX()] = "00";
                        }

                    } else if (boardCopyCheck[i][j].endsWith("N") && boardCopyCheck[i][j].startsWith("b")) {
                        Knight arrayedKnightCheck = new Knight(j, i, "b");
                        ArrayList<Move> arrayKnightMovesCheck = arrayedKnightCheck.validMoves(boardCopyCheck);
                        for (Move defensiveKnight : arrayKnightMovesCheck) {
                            boardCopyCheck[i][j] = "00";
                            boardCopyCheck[defensiveKnight.getToY()][defensiveKnight.getToX()] = "bN";
                            if (opposingCheck.getToY() == blackKingCheckY
                                    && opposingCheck.getToX() == blackKingCheckX) {

                            } else {
                                counts[defensiveKnight.getToX()][defensiveKnight.getToY()][convertPieceToNumber(
                                        "bN")]++;
                            }
                            boardCopyCheck[i][j] = "bN";
                            boardCopyCheck[defensiveKnight.getToY()][defensiveKnight.getToX()] = "00";
                        }

                    } else if (boardCopyCheck[i][j].endsWith("Q") && boardCopyCheck[i][j].startsWith("b")) {
                        Queen arrayedQueenCheck = new Queen(j, i, "b");
                        ArrayList<Move> arrayQueenMovesCheck = arrayedQueenCheck.validMoves(boardCopyCheck);
                        for (Move defensiveQueen : arrayQueenMovesCheck) {
                            boardCopyCheck[i][j] = "00";
                            boardCopyCheck[defensiveQueen.getToY()][defensiveQueen.getToX()] = "bQ";
                            if (opposingCheck.getToX() == blackKingCheckX
                                    && opposingCheck.getToY() == blackKingCheckY) {

                            } else {
                                counts[defensiveQueen.getToX()][defensiveQueen.getToY()][convertPieceToNumber("bQ")]++;
                            }
                            boardCopyCheck[i][j] = "bQ";
                            boardCopyCheck[defensiveQueen.getToY()][defensiveQueen.getToX()] = "00";
                        }

                    }

                    else if (boardCopyCheck[i][j].endsWith("K") && boardCopyCheck[i][j].startsWith("b")) {
                        King arrayedKingCheck = new King(j, i, "b");
                        ArrayList<Move> arrayKingMovesCheck = arrayedKingCheck.validMoves(boardCopyCheck);
                        for (Move defensiveKing : arrayKingMovesCheck) {
                            boardCopyCheck[i][j] = "00";
                            boardCopyCheck[defensiveKing.getToY()][defensiveKing.getToX()] = "bK";
                            blackKingCheckX = defensiveKing.getToX();
                            blackKingCheckY = defensiveKing.getToY();
                            if (opposingCheck.getToX() == blackKingCheckX
                                    && opposingCheck.getToY() == blackKingCheckY) {

                            } else {
                                counts[defensiveKing.getToX()][defensiveKing.getToY()][convertPieceToNumber("bK")]++;
                            }
                            boardCopyCheck[i][j] = "bK";

                            boardCopyCheck[defensiveKing.getToY()][defensiveKing.getToX()] = "00";
                            blackKingCheckX = j;
                            blackKingCheckY = i;

                        }

                    }
                }
            }
        }
        // System.out.println("All Poss Move Size is: " + allPossibleWhiteMoves.size());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 6; k++) {
                    if (counts[i][j][k] >= allPossibleMoves("w").size()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isChecked(String color) {
        String[][] baseBoardArr = board.getBoard();
        String oppoColor = color.equals("w") ? "b" : "w";
        int whiteKingCheckX = 4;
        int whiteKingCheckY = 7;
        int blackKingCheckX = 4;
        int blackKingCheckY = 0;

        String[][] boardCopyCheck = new String[8][8];
        for (int r = 0; r < baseBoardArr.length; r++) {
            for (int d = 0; d < baseBoardArr[r].length; d++) {
                boardCopyCheck[r][d] = baseBoardArr[r][d];
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardCopyCheck[i][j].equals("wK")) {
                    whiteKingCheckY = i;
                    whiteKingCheckX = j;
                } else if (boardCopyCheck[i][j].equals("bK")) {
                    blackKingCheckX = j;
                    blackKingCheckY = i;
                }
            }
        }

        for (Move check : allPossibleMoves(oppoColor)) {
            if (color.equals("w")) {
                // System.out.println("color.equals white");
                if (check.getToX() == whiteKingCheckX && check.getToY() == whiteKingCheckY) {
                    return true;
                }
            }
            if (color.equals("b")) {
                // System.out.println("color.equals blackl");
                if (check.getToX() == blackKingCheckX && check.getToY() == blackKingCheckY) {
                    return true;
                }
            }
        }
        return false;
    }

    public int numPiecesLeft(String color, String[][] board) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].startsWith(color)) {
                    count++;
                }
            }
        }
        return count;
    }

    // checks if there are any available moves other than the king
    public boolean checkForStalemateCondition(String color, String[][] board) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].equals(color + "B")) {
                    Bishop stalemateBishop = new Bishop(j, i, color);
                    if (stalemateBishop.validMoves(board).size() > 0) {
                        return false;
                    }
                } else if (board[i][j].equals(color + "R")) {
                    Rook stalemateRook = new Rook(j, i, color);
                    if (stalemateRook.validMoves(board).size() > 0) {
                        return false;
                    }

                } else if (board[i][j].equals(color + "N")) {
                    Knight stalemateKnight = new Knight(j, i, color);
                    if (stalemateKnight.validMoves(board).size() > 0) {
                        return false;
                    }
                } else if (board[i][j].equals(color + "Q")) {
                    Queen stalemateQueen = new Queen(j, i, color);
                    if (stalemateQueen.validMoves(board).size() > 0) {
                        return false;
                    }
                } else if (board[i][j].equals(color + "P")) {
                    Pawn stalematePawn = new Pawn(j, i, color);
                    if (stalematePawn.validMoves(board).size() > 0) {
                        return false;
                    }

                }
            }
        }
        return true;

    }

    public void display() {
        frame.setVisible(true);
    }

}
