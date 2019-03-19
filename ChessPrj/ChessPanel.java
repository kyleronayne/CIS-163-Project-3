package ChessPrj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessPanel extends JPanel {

    private JButton[][] board;
    private JButton undo;
    private ChessModel model;

    private ImageIcon wRook;
    private ImageIcon bRook;

    private ImageIcon wBishop;
    private ImageIcon bBishop;

    private ImageIcon wQueen;
    private ImageIcon bQueen;

    private ImageIcon wKing;
    private ImageIcon bKing;

    private ImageIcon wPawn;
    private ImageIcon bPawn;

    private ImageIcon wKnight;
    private ImageIcon bKnight;

    private boolean firstTurnFlag;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;
    private Player currentPlayer = Player.WHITE;
    private boolean en_passant = false;

    private listener listener;

    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
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

    private void createIcons() {
        // Sets the Image for white player pieces
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
    // method that updates the board
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

    // inner class that represents action listener for buttons
    private class listener implements ActionListener {
        //FIXME: Ask about adding firstMove to IChessPiece
        public void actionPerformed(ActionEvent event) {
            for(int i=0; i<8; i++)
                for(int j=0; j<8; j++)  {
                    if(model.pieceAt(i, j) != null)
                        if(model.pieceAt(i, j).player() == currentPlayer)
                            model.pieceAt(i, j).setFirstMove(false);
                }

            if(undo == event.getSource())   {
                boolean isAtStart = model.goToLastBoard();
                displayBoard();
                if(!isAtStart)
                    currentPlayer = currentPlayer.next();
                else
                    currentPlayer = Player.WHITE;
                return;
            }

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
                                System.out.println("Here I am");
                                if(model.pieceAt(fromRow, fromCol) != null) {
                                    if (model.pieceAt(fromRow, fromCol).player() == Player.WHITE) {
                                        if((toRow == 2) && (toRow == fromRow - 1) &&
                                                ((toCol == fromCol+ 1) ||
                                                        (toCol == fromCol - 1))) {
                                            //if enPassant is legal
                                            if (model.pieceAt(toRow + 1,toCol).getFirstMove()) {
                                                en_passant = true;
                                                Move enPassant = new Move(fromRow, fromCol, fromRow, toCol);
                                                model.saveBoard();
                                                model.move(enPassant);
                                                m = new Move(fromRow, toCol, toRow, toCol);
                                            }
                                        }
                                    }
                                    else if(model.pieceAt(fromRow, fromCol) != null) {
                                        if (model.pieceAt(fromRow, fromCol).player() == Player.BLACK) {
                                            if ((toRow == 5) && (toRow == fromRow + 1) &&
                                                    ((toCol == fromCol + 1) ||
                                                            (toCol == fromCol - 1))) {
                                                //if enPassant is legal
                                                if (model.pieceAt(toRow - 1, toCol).getFirstMove()) {
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
                                if(!en_passant)
                                    model.saveBoard();
                                model.move(m);
                                en_passant = false;
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
    }
}