import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

public class Chess {
    public static void main(String[] args) {

        Board baseBoard = new Board(8, 8);
        FrameClass baseFrame = new FrameClass(baseBoard);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                baseBoard.setCell(i, j, "00");
            }

        }

        baseBoard.setBoardLine(0, 'b');
        baseBoard.setPawnLine(1, 'b');
        baseBoard.setBoardLine(7, 'w');
        baseBoard.setPawnLine(6, 'w');

        baseBoard.displayBoard();
        baseFrame.display();

    }
}