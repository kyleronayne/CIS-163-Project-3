package ChessPrj;

import java.awt.Dimension;

import javax.swing.JFrame;

/**********************************************************************
 * A GUI that represents a chess game. Simply portrays the board and
 * calls a panel which holds the fundamental rules and play of the
 * game. This consists of two players, a functional AI, undo, and
 * standard chess rules in terms of how pieces move and how to win
 * the game.
 *
 * @author: Charlie Dorn, Kyle Ronayne, Brad Samack
 * @version Winter 2019 CIS 163 v1.0
 *********************************************************************/
public class ChessGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChessPanel panel = new ChessPanel();
        frame.getContentPane().add(panel);

        frame.setResizable(true);
        //frame.setPreferredSize(new Dimension(800, 637));
        //frame.pack();
        frame.setSize(new Dimension(800, 700));
        frame.setVisible(true);
    }
}