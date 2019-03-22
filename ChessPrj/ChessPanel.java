package ChessPrj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**********************************************************************
 * The panel for the chess board. Houses the bulk of everything that
 * goes on visually in the game. Shows the actual movement of the
 * pieces and displays messages for being in check, checkmated, and
 * reverting to the previous board.
 *********************************************************************/
public class ChessPanel extends JPanel {

    /** Creates a board of buttons */
    private JButton[][] board;

    /** The undo button to take back ones turn*/
    private JButton undo;

    /** Radio Button to represents playing against the AI */
    private JRadioButton onePlayer;

    /** Radio Button to represents playing with 2 people */
    private JRadioButton twoPlayer;

    /** The over all logic of the game of Chess */
    private ChessModel model;

    /** The image for the White Rook */
    private ImageIcon wRook;

    /** The image for the Black Rook */
    private ImageIcon bRook;

    /** The image for the White Bishop */
    private ImageIcon wBishop;

    /** The image for the Black Bishop */
    private ImageIcon bBishop;

    /** The image for the White Queen */
    private ImageIcon wQueen;

    /** The image for the Black Queen */
    private ImageIcon bQueen;

    /** The image for the White King */
    private ImageIcon wKing;

    /** THe image for the Black King */
    private ImageIcon bKing;

    /** The image for the White Pawn */
    private ImageIcon wPawn;

    /** The image for the Black Pawn */
    private ImageIcon bPawn;

    /** The image for the White Knight */
    private ImageIcon wKnight;

    /** The image for the Black Knight */
    private ImageIcon bKnight;

    /** Boolean to determine if this is the first turn */
    private boolean firstTurnFlag;

    /** fromRow to hold the current row before a move */
    private int fromRow;

    /** toRow to hold the row being moved to */
    private int toRow;

    /** fromCol to hold the current column before a move */
    private int fromCol;

    /** toCol to hold the column being moved to */
    private int toCol;

    /** The current player. White goes first */
    private Player currentPlayer = Player.WHITE;

    /** Boolean to hold if en_Passant is possible */
    private boolean en_passant = false;

    /** Listener to keep track of the players movements on the chess
      board*/
    private listener listener;

    /******************************************************************
     * Constructor sets up the game logic, board, pieces, buttons, etc.
     *****************************************************************/
    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        JPanel popup = new JPanel();
        popup.setLayout(new BoxLayout(popup, BoxLayout.Y_AXIS));
        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE)
                    placeWhitePieces(r, c);
                else if(model.pieceAt(r, c).player() == Player.BLACK)
                    placeBlackPieces(r, c);

                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }
        onePlayer = new JRadioButton("Play against the Computer", null);
        onePlayer.setSelected(false);
        onePlayer.addActionListener(listener);
        twoPlayer = new JRadioButton("Play against a Friend", null);
        twoPlayer.setSelected(true);
        twoPlayer.addActionListener(listener);
        JLabel label1 = new JLabel("Please enter your game mode below");
        JLabel label2 = new JLabel("Default is two player mode");
        popup.add(label1);
        popup.add(label2);
        popup.add(onePlayer);
        popup.add(twoPlayer);
        JOptionPane.showMessageDialog(null, popup);

        undo = new JButton("Undo", null);
        undo.addActionListener(listener);
        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        buttonpanel.add(undo);
        add(buttonpanel);
        firstTurnFlag = true;
    }

    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    /******************************************************************
     * Creates the image icons for each piece. Uses the given files
     * provided for the project.
     *****************************************************************/
    private void createIcons() {
        wRook = new ImageIcon("ChessPrj/wRook.png");
        bRook = new ImageIcon("ChessPrj/bRook.png");

        wBishop = new ImageIcon("ChessPrj/wBishop.png");
        bBishop = new ImageIcon("ChessPrj/bBishop.png");

        wQueen = new ImageIcon("ChessPrj/wQueen.png");
        bQueen = new ImageIcon("ChessPrj/bQueen.png");

        wKing = new ImageIcon("ChessPrj/wKing.png");
        bKing = new ImageIcon("ChessPrj/bKing.png");

        wPawn = new ImageIcon("ChessPrj/wPawn.png");
        bPawn = new ImageIcon("ChessPrj/bPawn.png");

        wKnight = new ImageIcon("ChessPrj/wKnight.png");
        bKnight = new ImageIcon("ChessPrj/bKnight.png");

    }

    /******************************************************************
     * Method that displays the board with the pieces at their
     * specified space.
     *****************************************************************/
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else    {
                    if (model.pieceAt(r, c).player() == Player.WHITE) {
                        if (model.pieceAt(r, c).type().equals("Pawn"))
                            board[r][c].setIcon(wPawn);

                        if (model.pieceAt(r, c).type().equals("Rook"))
                            board[r][c].setIcon(wRook);

                        if (model.pieceAt(r, c).type().equals("Knight"))
                            board[r][c].setIcon(wKnight);

                        if (model.pieceAt(r, c).type().equals("Bishop"))
                            board[r][c].setIcon(wBishop);

                        if (model.pieceAt(r, c).type().equals("Queen"))
                            board[r][c].setIcon(wQueen);

                        if (model.pieceAt(r, c).type().equals("King"))
                            board[r][c].setIcon(wKing);
                    }
                    else if(model.pieceAt(r, c).player() == Player.BLACK)   {
                        if(model.pieceAt(r, c).type().equals("Pawn"))
                            board[r][c].setIcon(bPawn);

                        if (model.pieceAt(r, c).type().equals("Rook"))
                            board[r][c].setIcon(bRook);

                        if (model.pieceAt(r, c).type().equals("Knight"))
                            board[r][c].setIcon(bKnight);

                        if (model.pieceAt(r, c).type().equals("Bishop"))
                            board[r][c].setIcon(bBishop);

                        if (model.pieceAt(r, c).type().equals("Queen"))
                            board[r][c].setIcon(bQueen);

                        if (model.pieceAt(r, c).type().equals("King"))
                            board[r][c].setIcon(bKing);
                    }

                }
        }
        repaint();
    }

    /******************************************************************
     * Inner class that represents the action listener and determines
     * what is to happen when provided a given event.
     *****************************************************************/
    private class listener implements ActionListener {
        /**************************************************************
         * Action Performed method that gets the current event and
         * determines what is to happend with it.
         * @param event the current action that was attempted.
         *************************************************************/
        public void actionPerformed(ActionEvent event) {
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++) {
                    if (model.pieceAt(i, j) != null)
                        if (model.pieceAt(i, j).player() == currentPlayer)
                            if (model.pieceAt(i, j).type().equals("Pawn"))
                                ((Pawn) model.pieceAt(i, j)).setFirstMove(false);
                }

            if (undo == event.getSource()) {
                boolean isAtStart = model.goToLastBoard();
                displayBoard();
                if (!isAtStart)
                    currentPlayer = currentPlayer.next();
                else
                    currentPlayer = Player.WHITE;
                return;
            }

            if (onePlayer == event.getSource()) {
                if (twoPlayer.isSelected())
                    twoPlayer.setSelected(false);
                if (onePlayer.isSelected())
                    model.useAI(true);
                else
                    model.useAI(false);
                return;
            }

            if (twoPlayer == event.getSource()) {
                if (onePlayer.isSelected())
                    onePlayer.setSelected(false);
                if (twoPlayer.isSelected())
                    model.useAI(false);
                else if (!twoPlayer.isSelected() && !onePlayer.isSelected())
                    model.useAI(false);
                return;
            }
            if(!model.AIisUsed())    {
                for (int r = 0; r < model.numRows(); r++)
                    for (int c = 0; c < model.numColumns(); c++)
                        if (board[r][c] == event.getSource()) {
                            if (firstTurnFlag) {
                                if (model.pieceAt(r, c) != null) {
                                    if (model.pieceAt(r, c).player() == currentPlayer) {
                                        fromRow = r;
                                        fromCol = c;
                                        firstTurnFlag = false;
                                    }
                                }
                            } else {
                                toRow = r;
                                toCol = c;
                                firstTurnFlag = true;
                                Move m = new Move(fromRow, fromCol, toRow, toCol);
                                if ((model.isValidMove(m)) == true) {
                                    if (model.pieceAt(fromRow, fromCol) != null) {
                                        if (model.pieceAt(fromRow, fromCol).player() == Player.WHITE) {
                                            if ((toRow == 2) && (toRow == fromRow - 1) &&
                                                    ((toCol == fromCol + 1) ||
                                                            (toCol == fromCol - 1))) {
                                                //if enPassant is legal
                                                if (model.pieceAt(toRow + 1, toCol).type().equals("Pawn"))
                                                    if (((Pawn) model.pieceAt(toRow + 1, toCol)).getFirstMove()) {
                                                        en_passant = true;
                                                        Move enPassant = new Move(fromRow, fromCol, fromRow, toCol);
                                                        model.saveBoard();
                                                        model.move(enPassant);
                                                        m = new Move(fromRow, toCol, toRow, toCol);
                                                    }
                                            }
                                        }
                                        else if (model.pieceAt(fromRow, fromCol) != null) {
                                            if (model.pieceAt(fromRow, fromCol).player() == Player.BLACK) {
                                                if ((toRow == 5) && (toRow == fromRow + 1) &&
                                                        ((toCol == fromCol + 1) ||
                                                                (toCol == fromCol - 1))) {
                                                    //if enPassant is legal
                                                    if (model.pieceAt(toRow - 1, toCol).type().equals("Pawn"))
                                                        if (((Pawn) model.pieceAt(toRow - 1, toCol)).getFirstMove()) {
                                                            en_passant = true;
                                                            Move enPassant = new Move(fromRow, fromCol, fromRow, toCol);
                                                            model.saveBoard();
                                                            model.move(enPassant);
                                                            m = new Move(fromRow, toCol, toRow, toCol);
                                                        }
                                                }
                                            }
                                        }
                                    }
                                    int OgCol = fromCol;
                                    int OgRow = fromRow;
                                    if (!en_passant)
                                        model.saveBoard();
                                    model.move(m);
                                    en_passant = false;

                                    if (model.pieceAt(r, c).type().equals("King")) {
                                        if (currentPlayer == Player.WHITE) {
                                            if (toCol == fromCol - 2) {
                                                Move leftRookMove =
                                                        new Move(7, 0, 7, 3);
                                                model.move(leftRookMove);
                                            }

                                            if (toCol == fromCol + 2) {
                                                Move rightRookMove =
                                                        new Move(7, 7, 7, 5);
                                                model.move(rightRookMove);
                                            }
                                        }

                                        if (currentPlayer == Player.BLACK) {
                                            if (toCol == fromCol - 2) {
                                                Move leftRookMove =
                                                        new Move(0, 0, 0, 2);
                                                model.move(leftRookMove);
                                            }

                                            if (toCol == fromCol + 2) {
                                                Move rightRookMove =
                                                        new Move(0, 7, 0, 4);
                                                model.move(rightRookMove);
                                            }
                                        }
                                    }

                                    if (model.inCheck(currentPlayer)) {
                                        Move newM = new Move(toRow, toCol, OgRow, OgCol);
                                        model.move(newM);
                                        return;
                                    }

                                    displayBoard();
                                    currentPlayer = currentPlayer.next();
                                    if (model.inCheck(currentPlayer)) {
                                        if (model.isComplete()) {
                                            JOptionPane.showMessageDialog(null,
                                                    currentPlayer +
                                                            " Has been CHECKMATED!\n" +
                                                            "GAME OVER!");
                                        } else {
                                            JOptionPane.showMessageDialog(null,
                                                    currentPlayer +
                                                            " Is In Check!\n" +
                                                            "Next move must get out of check!");
                                        }
                                    }

                                }
                            }
                        }
            }
            else if(model.AIisUsed())   {
                boolean blackTurn = false;
                if(currentPlayer == Player.WHITE) {
                    System.out.println("White turn using AI");
                    for (int r = 0; r < model.numRows(); r++)
                        for (int c = 0; c < model.numColumns(); c++)
                            if (board[r][c] == event.getSource()) {
                                if (firstTurnFlag) {
                                    if (model.pieceAt(r, c) != null) {
                                        if (model.pieceAt(r, c).player() == currentPlayer) {
                                            System.out.println("You've picked a piece");
                                            fromRow = r;
                                            fromCol = c;
                                            firstTurnFlag = false;
                                            blackTurn = false;
                                        }
                                    }
                                } else {
                                    toRow = r;
                                    toCol = c;
                                    firstTurnFlag = true;
                                    Move m = new Move(fromRow, fromCol, toRow, toCol);
                                    if ((model.isValidMove(m)) == true) {
                                        if (model.pieceAt(fromRow, fromCol) != null) {
                                            if ((toRow == 2) && (toRow == fromRow - 1) &&
                                                    ((toCol == fromCol + 1) ||
                                                            (toCol == fromCol - 1))) {
                                                //if enPassant is legal
                                                if (model.pieceAt(toRow + 1, toCol).type().equals("Pawn"))
                                                    if (((Pawn) model.pieceAt(toRow + 1, toCol)).getFirstMove()) {
                                                        en_passant = true;
                                                        Move enPassant = new Move(fromRow, fromCol, fromRow, toCol);
                                                        model.saveBoard();
                                                        model.move(enPassant);
                                                        m = new Move(fromRow, toCol, toRow, toCol);
                                                    }
                                            }
                                        }
                                        int OgCol = fromCol;
                                        int OgRow = fromRow;
                                        if (!en_passant)
                                            model.saveBoard();
                                        model.move(m);
                                        en_passant = false;

                                        if (model.pieceAt(r, c).type().equals("King")) {
                                            if (currentPlayer == Player.WHITE) {
                                                if (toCol == fromCol - 2) {
                                                    Move leftRookMove = new Move(7, 0, 7, 3);
                                                    model.move(leftRookMove);
                                                }
                                                if (toCol == fromCol + 2) {
                                                    Move rightRookMove = new Move(7, 7, 7, 5);
                                                    model.move(rightRookMove);
                                                }
                                            }
                                        }

                                        if (model.inCheck(currentPlayer)) {
                                            Move newM = new Move(toRow, toCol, OgRow, OgCol);
                                            model.move(newM);
                                            return;
                                        }

                                        displayBoard();
                                        currentPlayer = currentPlayer.next();
                                        if (model.inCheck(currentPlayer)) {
                                            if (model.isComplete()) {
                                                JOptionPane.showMessageDialog(null, currentPlayer + " Has been CHECKMATED!\n" +
                                                        "GAME OVER!");
                                            } else {
                                                JOptionPane.showMessageDialog(null, currentPlayer +
                                                        " Is in Check!\n" + "Next move must get out of check!");
                                            }
                                        }
                                    }
                                    blackTurn = true;
                                }
                            }
                }
                if(blackTurn == true) {
                    //FIXME: Here we will call the AI function in Model which should take care of MOVES
                    model.AI();

                    displayBoard();
                    currentPlayer = currentPlayer.next();
                    if (model.inCheck(currentPlayer)) {
                        if (model.isComplete()) {
                            JOptionPane.showMessageDialog(null, currentPlayer + " Has been CHECKMATED!\n" +
                                    "GAME OVER!");
                        } else {
                            JOptionPane.showMessageDialog(null, currentPlayer +
                                    " Is in Check!\n" + "Next move must get out of check!");
                        }
                    }
                }
            }
        }
    }
}