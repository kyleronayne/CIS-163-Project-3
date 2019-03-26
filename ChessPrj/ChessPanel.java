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


    /** Represents a board of JButtons */
    private JButton[][] board;

    /** The undo button to undo a turn*/
    private JButton undo;

    /** Radio Button to represent playing against the AI */
    private JRadioButton onePlayer;

    /** Radio Button to represent playing with 2 people */
    private JRadioButton twoPlayer;

    /** The chess game logic */
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

    /** Represents the current row before a move */
    private int fromRow;

    /** Represents the row being moved to */
    private int toRow;

    /** Represents the current column before a move */
    private int fromCol;

    /** Represents the column being moved to */
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
     */
    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        JPanel popup = new JPanel();
        popup.setLayout(new BoxLayout(popup, BoxLayout.Y_AXIS));
        boardpanel.setLayout(new GridLayout(model.numRows(),
                model.numColumns(), 1, 1));

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() ==
                        Player.WHITE)
                    placeWhitePieces(r, c);
                else if(model.pieceAt(r, c).player() ==
                        Player.BLACK)
                    placeBlackPieces(r, c);

                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }
        onePlayer = new JRadioButton("Play against the Computer",
                null);
        onePlayer.setSelected(false);
        onePlayer.addActionListener(listener);
        twoPlayer = new JRadioButton("Play against a Friend", null);
        twoPlayer.setSelected(true);
        twoPlayer.addActionListener(listener);
        JLabel label1 = new JLabel(
                "Please enter your game mode below");
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


    /******************************************************************
     * Sets each square on game board to either light gray or white,
     * depending on its location
     * @param r An integer representing a row from the board
     * @param c An integer representing a column from the board
     */
    private void setBackGroundColor(int r, int c) {

        // If the board square is odd, the JButton's background will
        // be set to light gray
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        }

        // If the board square is even, the JButton's background will
        // be set to white
        else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 &&
                r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /******************************************************************
     * Sets the icons corresponding to each white piece
     * @param r An integer representing a row from the board
     * @param c An integer representing a column from the board
     */
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


    /******************************************************************
     * Sets the icons corresponding to each black piece
     * @param r An integer representing a row from the board
     * @param c An integer representing a column from the board
     */
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

                    // Displays the corresponding image icons for the
                    // white pieces
                    if (model.pieceAt(r, c).player() == Player.WHITE) {
                        if (model.pieceAt(r, c).type().equals("Pawn"))
                            board[r][c].setIcon(wPawn);

                        if (model.pieceAt(r, c).type().equals("Rook"))
                            board[r][c].setIcon(wRook);

                        if (model.pieceAt(r, c).type().
                                equals("Knight"))
                            board[r][c].setIcon(wKnight);

                        if (model.pieceAt(r, c).type().
                                equals("Bishop"))
                            board[r][c].setIcon(wBishop);

                        if (model.pieceAt(r, c).type().
                                equals("Queen"))
                            board[r][c].setIcon(wQueen);

                        if (model.pieceAt(r, c).type().equals("King"))
                            board[r][c].setIcon(wKing);
                    }

                    // Displays the corresponding image icons for the
                    // black pieces
                    else if(model.pieceAt(r, c).player() ==
                            Player.BLACK)   {
                        if(model.pieceAt(r, c).type().equals("Pawn"))
                            board[r][c].setIcon(bPawn);

                        if (model.pieceAt(r, c).type().equals("Rook"))
                            board[r][c].setIcon(bRook);

                        if (model.pieceAt(r, c).type().
                                equals("Knight"))
                            board[r][c].setIcon(bKnight);

                        if (model.pieceAt(r, c).type().
                                equals("Bishop"))
                            board[r][c].setIcon(bBishop);

                        if (model.pieceAt(r, c).type().
                                equals("Queen"))
                            board[r][c].setIcon(bQueen);

                        if (model.pieceAt(r, c).type().equals("King"))
                            board[r][c].setIcon(bKing);
                    }

                }
        }

        // Updates the board
        repaint();
    }

    /******************************************************************
     * Inner class that represents the action listener and determines
     * what is to happen when provided a given event.
     *****************************************************************/
    private class listener implements ActionListener {
        /**************************************************************
         * Action Performed method that gets the current event and
         * determines what is to happen with it.
         * @param event the current action that was attempted.
         *************************************************************/
        public void actionPerformed(ActionEvent event) {
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++) {
                    /*Because en Passant is only available immediately
                    after a pawn does a double jump for its first turn,
                    at the start of the players next turn we clear all
                    of the pawn first turn flags*/
                    if (model.pieceAt(i, j) != null)
                        if (model.pieceAt(i, j).player() ==
                                currentPlayer)
                            if (model.pieceAt(i, j).type().
                                    equals("Pawn"))
                                ((Pawn) model.pieceAt(i, j)).
                                        setFirstMove(false);
                }

            /*Handles the undo functionality of the code. Because
            the WHITE player always goes first, we can assume that if
            the board is in the starting state, it is the WHITE players
            turn,. isAtStart is a boolean returned from goToLastBoard
            the returns if the board is in the starting state or not*/
            if (undo == event.getSource()) {
                boolean isAtStart = model.goToLastBoard();
                displayBoard();
                if (!isAtStart)
                    currentPlayer = currentPlayer.next();
                else
                    currentPlayer = Player.WHITE;
                return;
            }

            /*The AI JRadioButton in the initial popup panel.
            A user cannot select two options, therefore if AI
            is chosen, 2 player must be unselected*/
            if (onePlayer == event.getSource()) {
                if (twoPlayer.isSelected())
                    twoPlayer.setSelected(false);
                if (onePlayer.isSelected())
                    model.useAI(true);
                else
                    model.useAI(false);
                return;
            }

            /*The 2 player JRadioButton in the initial popup panel.
            A user cannot select two options, however no options is
            a valid choice. In the case of no options, the 2 player
            mode is selected*/
            if (twoPlayer == event.getSource()) {
                if (onePlayer.isSelected())
                    onePlayer.setSelected(false);
                if (twoPlayer.isSelected())
                    model.useAI(false);
                else if (!twoPlayer.isSelected() &&
                        !onePlayer.isSelected())
                    model.useAI(false);
                return;
            }

            /*This is the panel logic for what needs to happen if 2
            player mode is engaged*/
            if(!model.AIisUsed())    {
                for (int r = 0; r < model.numRows(); r++)
                    for (int c = 0; c < model.numColumns(); c++)

                        /*loops through all of the JButtons in the array
                        * to determine which one was pressed*/
                        if (board[r][c] == event.getSource()) {
                            /*if the first turn flag is true, it is in
                            "piece select" mode*/
                            if (firstTurnFlag) {
                                /*If the piece the user selected is a
                                piece and is the currentplayer's piece
                                it is selected, otherwise nothing
                                happens*/
                                if (model.pieceAt(r, c) != null) {
                                    if (model.pieceAt(r, c).player() ==
                                            currentPlayer) {
                                        fromRow = r;
                                        fromCol = c;
                                        firstTurnFlag = false;
                                    }
                                }
                            }
                            /*If the player has already selected their
                            piece, the second click will be where they
                            want to move to*/
                            else {
                                toRow = r;
                                toCol = c;
                                /*Once they've selected where they want
                                to go, we know the next tile will be
                                the "piece select" turn again*/
                                firstTurnFlag = true;

                                /*Generate a new move based on the
                                user input*/
                                Move m = new Move(fromRow, fromCol,
                                        toRow, toCol);

                                /*Next, check to make sure the move is
                                valid for the game/piece*/
                                if ((model.isValidMove(m))) {

                                    /*If the piece at the starting loc.
                                    is not a real piece, nothing should
                                    happen*/
                                    if (model.pieceAt(fromRow, fromCol)
                                            != null) {

                                        /*Checks to see if enPassant
                                        is being attempted by WHITE
                                        team*/
                                        if (model.pieceAt(fromRow,
                                                fromCol).player() ==
                                                Player.WHITE) {

                                            /*The movement that would
                                            be enPassant*/
                                            if ((toRow == 2) && (toRow == fromRow - 1) &&
                                                    ((toCol == fromCol + 1) ||
                                                            (toCol == fromCol - 1))) {
                                                /*checks to see if this
                                                movement is legal (this
                                                is also checked in
                                                model)*/
                                                if (model.pieceAt(
                                                        toRow + 1,
                                                        toCol)!=null){

                                                    if (model.pieceAt(
                                                            toRow + 1,
                                                            toCol).
                                                            type().
                                                            equals(
                                                              "Pawn"))
                                                    /*Make sure this
                                                    was the captured
                                                    pawns first move*/
                                                    if (((Pawn)
                                                        model.
                                                        pieceAt(
                                                        toRow+1,
                                                        toCol))
                                                        .getFirstMove(
                                                        )){
                                                        /*This handles
                                                        the sideways
                                                        move for en
                                                        passant*/
                                                            en_passant
                                                                = true;
                                                            Move
                                                            enPassant =
                                                            new Move(
                                                               fromRow,
                                                               fromCol,
                                                               fromRow,
                                                                toCol);
                                                            model.saveBoard();
                                                            model.move(enPassant);
                                                            m = new Move(fromRow, toCol, toRow, toCol);
                                                    }
                                            }
                                            }
                                        }

                                        /*performs the same enPassant
                                        check for the BLACK team*/
                                        else if (model.pieceAt(fromRow,
                                                fromCol) != null) {
                                            if (model.pieceAt(fromRow,
                                                    fromCol).player()
                                                    == Player.BLACK) {
                                                if ((toRow == 5) &&
                                                        (toRow==fromRow
                                                                +1) &&
                                                        ((toCol==
                                                            fromCol+1)
                                                            ||(toCol ==
                                                                fromCol
                                                                -1))) {
                                                    /*if enPassant is
                                                    legal*/
                                                    if(model.pieceAt
                                                        (toRow-1,
                                                            toCol)!=
                                                            null) {
                                                        if (model.
                                                        pieceAt(
                                                        toRow - 1,
                                                        toCol).
                                                        type().
                                                        equals(
                                                         "Pawn"))
                                                        if (((Pawn)
                                                          model.
                                                          pieceAt(
                                                          toRow-1,
                                                          toCol)).
                                                          getFirstMove(
                                                          )) {
                                                            en_passant
                                                                = true;
                                                            Move
                                                              enPassant
                                                              =new Move
                                                              (fromRow,
                                                              fromCol,
                                                              fromRow,
                                                              toCol);
                                                            model.
                                                            saveBoard(
                                                            );
                                                            model.
                                                            move(
                                                            enPassant);
                                                            m = new
                                                            Move(
                                                            fromRow,
                                                            toCol,
                                                            toRow,
                                                            toCol);
                                                            }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    /*Save the previous row and column
                                    in case the move needs to be
                                    reverted*/
                                    int OgCol = fromCol;
                                    int OgRow = fromRow;
                                    /*As long as en passant
                                    isn't being attempted, save the
                                    board*/
                                    if (!en_passant)
                                        model.saveBoard();
                                    /*make the specified move*/
                                    model.move(m);
                                    en_passant = false;
                                    boolean KingCastled = false;

                                    /*next, check to see if castling
                                    is happening*/
                                    if (model.pieceAt(r, c).type().
                                            equals("King")) {
                                        if (currentPlayer ==
                                                Player.WHITE) {
                                            /*Check white castling
                                            to the LEFT*/
                                            if (toCol == fromCol - 2) {
                                                Move leftRookMove =
                                                        new Move(7,0,
                                                                7, 3);
                                                model.move(
                                                    leftRookMove);
                                                KingCastled = true;
                                            }

                                            /*Check white castling
                                            to the RIGHT*/
                                            if (toCol == fromCol + 2) {
                                                Move rightRookMove =
                                                        new Move(7, 7,
                                                                7, 5);
                                                model.move(
                                                    rightRookMove);
                                                KingCastled = true;
                                            }
                                        }

                                        if (currentPlayer ==
                                                Player.BLACK) {
                                            /*Check black castling to
                                             the LEFT*/
                                            if (toCol == fromCol - 2) {
                                                Move leftRookMove =
                                                        new Move(0, 0,
                                                                0, 3);
                                                model.move(leftRookMove);
                                                KingCastled = true;
                                            }

                                            /*Check black castling to
                                            the RIGHT*/
                                            if (toCol == fromCol + 2) {
                                                Move rightRookMove =
                                                        new Move(0, 7, 0, 5);
                                                model.move(rightRookMove);
                                                KingCastled = true;
                                            }
                                        }
                                    }

                                    /*This undoes a move that puts the
                                    king into check*/
                                    if (model.inCheck(currentPlayer)
                                            && !KingCastled) {
                                        Move newM = new Move(toRow,
                                                toCol, OgRow, OgCol);
                                        model.move(newM);
                                        return;
                                    }

                                    /*After the moves have been made,
                                    display the board*/
                                    displayBoard();

                                    /*Switch to the next players turn*/
                                    currentPlayer = currentPlayer.next();

                                    /*If the next player is in check or
                                    is checkmated, send a popup to the
                                    user*/
                                    if (model.inCheck(currentPlayer)) {
                                        if (model.isComplete()) {
                                            JOptionPane.
                                                showMessageDialog(null,
                                                    currentPlayer +
                                                        " Has been " +
                                                        "CHECKMATED!"+
                                                        "\nGAME OVER");
                                        } else {
                                            JOptionPane.
                                                showMessageDialog(null,
                                                currentPlayer +
                                                    " Is In Check!\n" +
                                                    "Next move must " +
                                                    "get out of " +
                                                        "check!");
                                        }
                                    }

                                }
                            }
                        }
            }

            /*This handles the panel class for the AI being used*/
            else if(model.AIisUsed())   {
                /*Black turn flag allows the computer to know when
                it is the AIs turn*/
                boolean blackTurn = false;
                if(currentPlayer == Player.WHITE) {
                    for (int r = 0; r < model.numRows(); r++)
                        for (int c = 0; c < model.numColumns(); c++)
                            if (board[r][c] == event.getSource()) {
                                if (firstTurnFlag) {
                                    /*If the player doesn't click on
                                    a valid piece, nothing
                                    will happen*/
                                    if (model.pieceAt(r, c) != null) {
                                        if (model.pieceAt(r,c).player()
                                                == currentPlayer) {
                                            fromRow = r;
                                            fromCol = c;
                                            firstTurnFlag = false;
                                            blackTurn = false;
                                        }
                                        else return;
                                    }
                                    else return;
                                }
                                /*If the player has already chosen a
                                piece*/
                                else {
                                    toRow = r;
                                    toCol = c;
                                    firstTurnFlag = true;

                                    /*Create a new move*/
                                    Move m = new Move(fromRow, fromCol,
                                            toRow, toCol);

                                    /*Check if the move is valid*/
                                    if ((model.isValidMove(m))) {
                                        if (model.pieceAt(fromRow,
                                                fromCol) != null) {
                                            /*Check to see if enPassant
                                            is being attempted*/
                                            if ((toRow == 2) &&
                                                    (toRow==fromRow-1)
                                                    &&((toCol==fromCol
                                                    + 1)||(toCol ==
                                                    fromCol - 1))) {
                                                /*Check if enPassant
                                                 is legal*/
                                                if (model.pieceAt(toRow
                                                        +1,toCol) !=
                                                        null) {
                                                    if (model.pieceAt(
                                                            toRow + 1,
                                                            toCol).
                                                            type().
                                                            equals(
                                                            "Pawn")){
                                                    if (((Pawn)
                                                        model.
                                                        pieceAt(
                                                        toRow+1,
                                                        toCol))
                                                        .getFirstMove(
                                                        )){
                                                        en_passant =
                                                                true;
                                                            Move
                                                            enPassant=
                                                            new Move(
                                                            fromRow,
                                                            fromCol,
                                                            fromRow,
                                                            toCol);
                                                            model.
                                                            saveBoard(
                                                            );
                                                            model.
                                                            move(
                                                            enPassant);
                                                            m = new
                                                            Move(
                                                            fromRow,
                                                            toCol,
                                                            toRow,
                                                            toCol);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        /*Save the starting location
                                        in case the move needs to be
                                        reverted*/
                                        int OgCol = fromCol;
                                        int OgRow = fromRow;
                                        if (!en_passant)
                                            model.saveBoard();
                                        model.move(m);
                                        en_passant = false;

                                        /*Check to see if castling is
                                        being attempted*/
                                        if (model.pieceAt(r, c).type().
                                                equals("King")) {
                                            if (currentPlayer ==
                                                    Player.WHITE) {
                                                /*Check for LEFT
                                                castling*/
                                                if (toCol==fromCol-2) {
                                                    Move leftRookMove=
                                                        new Move(7, 0,
                                                                7, 3);
                                                    model.move(
                                                        leftRookMove);
                                                }
                                                /*Check for RIGHT
                                                castling*/
                                                if (toCol==fromCol+2){
                                                    Move rightRookMove
                                                        =new Move(7, 7,
                                                            7, 5);
                                                    model.move(
                                                        rightRookMove);
                                                }
                                            }
                                        }

                                        /*If the move puts the player
                                        into check, it must be
                                        undone*/
                                        if (model.inCheck(currentPlayer)) {
                                            Move newM = new Move(toRow, toCol, OgRow, OgCol);
                                            model.move(newM);
                                            return;
                                        }

                                        /*Display the new board*/
                                        displayBoard();

                                        /*Make it the next players
                                        turn*/
                                        currentPlayer = currentPlayer.
                                                next();

                                        /*If the new player is in check
                                        or in checkmate, a popup will
                                        appear with the message*/
                                        if (model.inCheck(
                                                currentPlayer)) {
                                            if (model.isComplete()) {
                                                JOptionPane.
                                                    showMessageDialog(
                                                        null,
                                                        currentPlayer +
                                                        " Has been " +
                                                        "CHECKMATED\n"+
                                                        "GAME OVER!");
                                            }
                                            /*If the user is not
                                            checkmated, but they're
                                            in check*/
                                            else {
                                                JOptionPane.
                                                    showMessageDialog(
                                                        null,
                                                        currentPlayer +
                                                        " Is in Check!\n"
                                                        + "Next move " +
                                                        "must get "+
                                                        "out of "+
                                                        "check!");
                                            }
                                        }
                                    }
                                    /*If the move that the user made
                                    is not valid, make it so they must
                                    pick another pice to move*/
                                    else    {
                                        firstTurnFlag = true;
                                        return;
                                    }

                                    /*If the white team successfully
                                    made a move, set this flag so the
                                    computer knows that it can move*/
                                    blackTurn = true;
                                }
                            }
                }

                /*If it's the AIs turn*/
                if(blackTurn == true) {

                    /*This calls the AI method, which takes care of all
                    AI movement*/
                    model.AI();

                    /*Once the AI has made its move, display the board
                    and send out any required messages*/
                    displayBoard();

                    /*Set the next player*/
                    currentPlayer = currentPlayer.next();

                    /*If in check or checkmated, output the correct
                    message*/
                    if (model.inCheck(currentPlayer)) {
                        if (model.isComplete()) {
                            JOptionPane.showMessageDialog(null,
                                    currentPlayer + " Has been "+
                                    "CHECKMATED!\n" +
                                    "GAME OVER!");
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    currentPlayer +
                                    " Is in Check!\n" +
                                    "Next move must get out " +
                                    "of check!");
                        }
                    }
                }
            }
        }
    }
}