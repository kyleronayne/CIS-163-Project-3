package ChessPrj;
import java.util.ArrayList;

/**********************************************************************
 * ChessModel Class that represents the overall model of chess.
 * Keeps track of the player and which piece is which players.
 * Determines whether a player is in check or checkmate.
 * Places the pieces on the board.
 * Saves a history of the board that can be undone.
 * and Houses the AI for when playing against the computer.
 *********************************************************************/
public class ChessModel implements IChessModel {


    /** Represents the chess board */
    private IChessPiece[][] board;

    /** Represents the player */
    private Player player;

    /** Holds the current status of the game */
    private GUIcodes gameStatus;

    /** A variable that holds a particular chess piece */
    private ChessPiece newPiece;

    /** ArrayList that holds the board history */
    private ArrayList<IChessPiece[][]> prevBoard;

    /** A saved state of the beginning board */
    private IChessPiece[][] startBoard;

    /** Variable that determines whether Undo was clicked to the first
      turn */
    private boolean hasUndoneToStart = false;

    /** Boolean to determine whether playing against a computer or
     * another human player */
    private boolean usingAI = false;

    /** Represents the row of the white player's king */
    private int rowWKing;

    /** Represents the column of the white player's king */
    private int colWKing;


    /******************************************************************
     * Constructor for ChessModel that initializes the board, its
     * history, and starting state. Places all the chess pieces for
     * each player and assigns that piece to that player.
     * Generates the board and resets the gameStatus.
     *****************************************************************/
    public ChessModel() {
        board = new IChessPiece[8][8];
        prevBoard = new ArrayList<>();
        startBoard = new IChessPiece[8][8];
        prevBoard.clear();
        player = Player.WHITE;
        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight (Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);

        //FIXME: BLACK KING SHOULD BE ON [0][4]
        //FIXME: Black Queen SHOULD BE ON [0][3]
        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight (Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);

        for(int i=0; i<8; i++) {
            board[6][i] = new Pawn(Player.WHITE);
            board[1][i] = new Pawn(Player.BLACK);
        }
        startBoard = generateNewBoard();
        gameStatus = GUIcodes.NoMessage;
    }


    /******************************************************************
     * Determines whether the king has been checkmated and sets the
     * game status to checkmate
     * @return true if the king has been checkmated, false if the king
     * can survive.
     *****************************************************************/
    public boolean isComplete() {
        //Which player are we looking at?
        if(currentPlayer() == Player.WHITE) {
            // Loop through all the rows and columns
            for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                // Check there is a piece there
                if((pieceAt(r, c) != null))
                    // Check if the piece belongs to the White player
                    if((pieceAt(r, c).player()==Player.WHITE))
                    // Loop through all the rows and columns again
                    for(int x=0; x<numRows(); x++)
                        for(int y=0; y<numColumns(); y++) {
                            // Make a move and a move back
                            Move m = new Move(r, c, x, y);
                            Move m2 = new Move(x, y, r, c);
                            /* Check if moving to X, Y is a valid move
                            for the piece at the specified row and col.
                             */
                            if(pieceAt(r, c).isValidMove(m, board)){
                                newPiece = null;
                                // Is there a piece at x, y?
                                if(pieceAt(x, y) != null)   {
                                    // which piece is it?
                                    if(pieceAt(x, y).type().
                                            equals("King"))
                                        newPiece = new King
                                                (Player.BLACK);
                                    else if(pieceAt(x, y).type().
                                            equals("Queen"))
                                        newPiece = new Queen
                                                (Player.BLACK);
                                    else if(pieceAt(x, y).type().
                                            equals("Bishop"))
                                        newPiece = new Bishop(
                                                Player.BLACK);
                                    else if(pieceAt(x, y).type().
                                            equals("Knight"))
                                        newPiece = new Knight
                                                (Player.BLACK);
                                    else if(pieceAt(x, y).type().
                                            equals("Rook"))
                                        newPiece = new Rook
                                                (Player.BLACK);
                                    else if(pieceAt(x, y).type().
                                            equals("Pawn"))
                                        newPiece = new Pawn
                                                (Player.BLACK);
                                    else
                                        newPiece = null;
                                }
                                // Move to x, y
                                move(m);

                                /* If player is still in check move
                                back */
                                if(inCheck(currentPlayer().next())){
                                    move(m2);
                                    board[x][y] = newPiece;
                                }
                                /* Else move back and return false
                                the player is not checkmated */
                                else    {
                                    move(m2);
                                    board[x][y] = newPiece;
                                    return false;
                                }

                            }
                        }
        }
        // Else the player is Black and the same checks go through
        else {
            for (int r1 = 0; r1 < numRows(); r1++)
            for (int c1 = 0; c1 < numColumns(); c1++)
                if ((pieceAt(r1, c1) != null))
                    if ((pieceAt(r1, c1).player() == Player.BLACK)) {
                        for (int x = 0; x < numRows(); x++)
                            for (int y = 0; y < numColumns(); y++) {
                                Move m = new Move(r1, c1, x, y);
                                Move m2 = new Move(x, y, r1, c1);
                                if(pieceAt(r1, c1).isValidMove
                                        (m, board)){
                                    newPiece = null;
                                    if(pieceAt(x, y) != null){
                                        //Want a new white piece
                                        if(pieceAt(x, y).type().
                                                equals("King"))
                                            newPiece = new King
                                                    (Player.WHITE);
                                        else if(pieceAt(x, y).type().
                                                equals("Queen"))
                                            newPiece = new Queen
                                                    (Player.WHITE);
                                        else if(pieceAt(x, y).type().
                                                equals("Bishop"))
                                            newPiece = new Bishop
                                                    (Player.WHITE);
                                        else if(pieceAt(x, y).type().
                                                equals("Knight"))
                                            newPiece = new Knight
                                                    (Player.WHITE);
                                        else if(pieceAt(x, y).type().
                                                equals("Rook"))
                                            newPiece = new Rook
                                                    (Player.WHITE);
                                        else if(pieceAt(x, y).type().
                                                equals("Pawn"))
                                            newPiece = new Pawn
                                                    (Player.WHITE);
                                        else
                                            newPiece = null;
                                    }
                                    move(m);
                                    if(inCheck(currentPlayer().next())){
                                        move(m2);
                                        board[x][y] = newPiece;
                                    }
                                    else    {
                                        move(m2);
                                        board[x][y] = newPiece;
                                        return false;
                                    }
                                }
                            }
                    }
        }
        /* If the code has gotten to this point the player
        has been Checkmated */
        gameStatus = GUIcodes.Checkmate;
        return true;
    }


    /******************************************************************
     * Checks if the move being attempted is a valid move in chess.
     * @param move a {@link ChessPrj.Move} object describing the move
     * to be made.
     * @return true if the move is valid, false otherwise
     *****************************************************************/
    public boolean isValidMove(Move move) {
        boolean valid = false;

        if (board[move.fromRow][move.fromColumn] != null)
            valid = (board[move.fromRow][move.fromColumn].
                    isValidMove(move, board));

        return valid;
    }


    /******************************************************************
     * Set's a players toRow and toColumn equal to the fromRow
     * fromColumn, and sets their from to Null. Then sets the player
     * to next.
     * @param move a {@link ChessPrj.Move} object describing the move
     * to be made.
     *****************************************************************/
    public void move(Move move) {
        board[move.toRow][move.toColumn] =  board[move.fromRow]
                [move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;
        player = player.next();
    }


    /******************************************************************
     * Determines whether the specific player has been put in check
     * from the kings starting position.
     * @param  p {@link ChessPrj.Move} the Player being checked
     * @return true if the player is in check, false otherwise.
     */
    public boolean inCheck(Player p) {
        // Place holder values for the kings position
        int kingRow = -3;
        int kingCol = -1;

        /* Loops through the board to find where the king is for the
         specific player and set the kings row and column */
        for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if(pieceAt(r, c) != null)
                    if(pieceAt(r, c).player() == p)
                        if(pieceAt(r, c).type().equals("King")){
                            kingRow = r;
                            kingCol = c;

                        }

        /* Loop through all the rows and columns and determine if
         the other player is allowed to move to the current players
         king position if they can they are in check */
        for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if(pieceAt(r, c) != null) {
                    if (pieceAt(r, c).player() == p.next()) {
                            Move move = new Move(r, c, kingRow, kingCol);
                            if (pieceAt(r, c).isValidMove(move, board)) {
                                gameStatus = GUIcodes.inCheck;
                               return true;
                            }
                    }
                }


        return false;
    }


    /******************************************************************
     * Determine whether the king is in check in at the specified row
     * and col that they are now in.
     * @param p the current player
     * @param row the row being looked at
     * @param col the column being looked at
     * @return true if the player is in check, false otherwise.
     *****************************************************************/
    public boolean inCheck(Player p, int row, int col) {
        // The kings row and column
        int kingRow = row;
        int kingCol = col;

        /* Loop through rows and columns and check if you can take the
         king */
        for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if(pieceAt(r, c) != null) {
                    if (pieceAt(r, c).player() == p.next()) {
                            Move move = new
                                    Move(r, c, kingRow, kingCol);
                            if (pieceAt(r, c).isValidMove
                                    (move, board)){
                                gameStatus = GUIcodes.inCheck;
                                return true;
                            }
                    }
                }
        return false;
    }

    //FIXME: Are we going to use this?
    public boolean canCastle() {
        Move kingMove = new Move(7, 4, 7, 2);
        return isValidMove(kingMove);
    }


    /*****************************************************************
     * Gets the current player
     * @return the current player
     *****************************************************************/
    public Player currentPlayer() {
        return player;
    }


    /******************************************************************
     * The number of rows in a game of chess.
     * @return the amount of rows that exist in chess.
     *****************************************************************/
    public int numRows() {
        return 8;
    }


    /*****************************************************************
     * The number of columns in a game of chess.
     * @return the amount of columns that exist in chess.
     *****************************************************************/
    public int numColumns() {
        return 8;
    }


    /******************************************************************
     * Gets the piece at the current row and column on the board.
     * @param row the row in question.
     * @param column the column in question.
     * @return what is at that row and column on the board.
     *****************************************************************/
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }


    /******************************************************************
     * Changes the player to the next player.
     *****************************************************************/
    public void setNextPlayer() {
        player = player.next();
    }

    /******************************************************************
     * Saves the current board and adds it tho the list of
     * board history.
     *****************************************************************/
    public void saveBoard() {
        prevBoard.add(generateNewBoard());
    }


    /******************************************************************
     * Reverts to the previous board that is stored in the list
     * @return true if there is a board history, false otherwise.
     *****************************************************************/
    public boolean goToLastBoard() {
        /*If the board is in the starting state, set the player to
        WHITE, and reset the board to start*/
        if((prevBoard.size() <=1) || ((prevBoard.size() == 2) &&
                (hasUndoneToStart)))   {
            prevBoard.clear();
            prevBoard.add(generateNewBoard(startBoard));
            board = prevBoard.get(0);
            player = Player.WHITE;
            hasUndoneToStart = true;
            return true;
        }

        /*If the board is not in the starting state, set the player to
        next, and set the board to the previous state*/
        else  {
            board = prevBoard.get(prevBoard.size()-1);
            prevBoard.remove(prevBoard.size()-1);
            setNextPlayer();
            return false;
        }
    }

    /******************************************************************
     * Generates a new board and places the pieces at the same place
     * as the current board.
     * @return the copy of the board.
     *****************************************************************/
    private IChessPiece[][] generateNewBoard()  {
        IChessPiece[][] copy = new IChessPiece[8][8];

        /*Copy all of the pieces at location (i, j) ON THE CURRENT
         GAME BOARD into the new copy of the array*/
        for(int i=0; i<numRows(); i++)
            for(int j=0; j<numColumns(); j++) {
                if(board[i][j] != null)
                    copy[i][j] = board[i][j];
            }
        return copy;
    }


    /******************************************************************
     * Copies a specific board to a copy that places everything where
     * the copy has stuff placed.
     * @param arrayToCopy the board to be copied.
     * @return the copy of that board.
     *****************************************************************/
    private IChessPiece[][] generateNewBoard(IChessPiece[][] arrayToCopy)  {
        IChessPiece[][] copy = new IChessPiece[8][8];

        /*Copy all of the pieces at location (i, j) on a SPECIFIED BOARD
        into the new copy of the array*/
        for(int i=0; i<numRows(); i++)
            for(int j=0; j<numColumns(); j++) {
                if(arrayToCopy[i][j] != null)
                    copy[i][j] = arrayToCopy[i][j];
            }
        return copy;
    }


    /******************************************************************
     * Returns true when a black piece can attack a white piece
     * @param attack A boolean specifying whether a black piece
     * should attack a white piece
     * @return True when a black piece can attack a white piece
     *****************************************************************/
    private boolean AIattack(boolean attack) {

        // True when a black piece can attack a white piece
        boolean canAttack = false;
        for (int row = 0; row < numRows(); row++) {
            for (int column = 0; column < numColumns(); column++) {
                if (board[row][column] != null) {

                    // Gets the row and column of a black piece
                    if (board[row][column].player() == Player.BLACK) {
                        for (int toRow = 0; toRow < numRows();
                             toRow++) {
                            for (int toColumn = 0; toColumn <
                                    numColumns(); toColumn++) {
                                if (board[toRow][toColumn] != null) {

                                    // Gets the row and column of a
                                    // white piece
                                    if (board[toRow][toColumn].player()
                                            == Player.WHITE) {
                                        Move attackMove = new Move(row,
                                                column, toRow,
                                                toColumn);
                                        Move undoAttack =
                                                new Move(toRow,
                                                        toColumn, row
                                                        , column);

                                        // Checks to see if the
                                        // attack move is valid
                                        if (board[row][column].
                                                isValidMove(attackMove,
                                                        board)) {
                                            canAttack = true;

                                            // If the boolean
                                            // parameter is true,
                                            // then attack
                                            if (attack) {
                                                move(attackMove);

                                                if (inCheck(Player.
                                                        BLACK)) {
                                                    move(undoAttack);
                                                }
                                                return true;
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (canAttack) {
            return true;
        }
        else {
            return false;
        }
    }


    /******************************************************************
     * Returns true if a black piece can put a white piece in check
     * @return True if a black piece can put a white piece in check
     *****************************************************************/
    private boolean AIcanPutWhiteInCheck()  {

        for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if(board[r][c]!=null)
                    if(board[r][c].player() == Player.BLACK)    {
                        for(int i=0; i<numRows(); i++)
                            for(int j=0; j<numColumns(); j++)   {

                                // Represents a black piece attack move
                                // to a white piece
                                Move m = new Move(r, c, i, j);

                                // A move to undo the attack move
                                Move m2 = new Move(i, j, r, c);
                                if(board[r][c].isValidMove(m, board)) {
                                    newPiece = null;
                                    if(pieceAt(i, j) != null)   {
                                        //Want a new white piece
                                        if(pieceAt(i, j).type()
                                                .equals("King"))
                                            newPiece = new King(
                                                    Player.WHITE);
                                        else if(pieceAt(i, j).type()
                                                .equals("Queen"))
                                            newPiece = new Queen(
                                                    Player.WHITE);
                                        else if(pieceAt(i, j).type()
                                                .equals("Bishop"))
                                            newPiece = new Bishop(
                                                    Player.WHITE);
                                        else if(pieceAt(i, j).type()
                                                .equals("Knight"))
                                            newPiece = new Knight(
                                                    Player.WHITE);
                                        else if(pieceAt(i, j).type()
                                                .equals("Rook"))
                                            newPiece = new Rook(
                                                    Player.WHITE);
                                        else if(pieceAt(i, j).type()
                                                .equals("Pawn"))
                                            newPiece = new Pawn(
                                                    Player.WHITE);
                                        else
                                            newPiece = null;
                                    }
                                    move(m);

                                    // Checks to see if the black
                                    // attack move puts the white
                                    // player in check
                                    if(inCheck(Player.WHITE))   {
                                        move(m2);
                                        board[i][j] = newPiece;
                                        return true;
                                    }
                                    else    {
                                        move(m2);
                                        board[i][j] = newPiece;
                                    }

                                }
                            }
                    }

        return false;
    }


    /******************************************************************
     * Returns true if a black piece is currently in danger of being
     * attacked
     * @return True if a black piece is currently in danger of being
     * attacked
     *****************************************************************/
    private boolean AIblackPieceInDanger()  {
        for(int r=0; r<numRows(); r++)  {
            for(int c=0; c<numColumns(); c++)   {
                if(board[r][c] != null) {
                    if(board[r][c].player() == Player.WHITE)    {
                        for(int x=0; x<numRows(); x++)  {
                            for(int y=0; y<numColumns(); y++)   {
                                if(board[x][y] != null) {
                                    if(board[x][y].player() ==
                                            Player.BLACK) {
                                        Move m = new Move(r, c, x, y);

                                        // If the move from a white
                                        // piece to a black piece is
                                        // valid
                                        if (board[r][c].isValidMove(
                                                m, board)) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    /******************************************************************
     * Returns true if a white piece cannot move to a specified board
     * square
     * @param row An integer representing a row from the board
     * @param col An integer representing a column from the board
     * @return True if a white piece cannot move to a specified board
     * square
     *****************************************************************/
    private boolean AIisThisSpotSafe(int row, int col)  {
        for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if(board[r][c] != null)
                    if(board[r][c].player() == Player.WHITE) {

                        // Represents a white piece move
                        Move m = new Move(r, c, row, col);
                        if(board[r][c].isValidMove(m, board))
                            return false;
                    }
        return true;
    }


    /******************************************************************
     * Sets usingAI to true if the user specified whether or not to
     * use the AI
     * @param ai Boolean denoting whether or not to use the AI
     *****************************************************************/
    public void useAI(boolean ai)  {
        if(ai)
            usingAI = true;
        else
            usingAI = false;
    }


    /******************************************************************
     * Returns true if the user specified to play against the AI
     * @return True if the user specified to play against the AI
     *****************************************************************/
    public boolean AIisUsed()   {
        return usingAI;
    }


    /******************************************************************
     * Saves a copy of a specified chess piece that could be
     * potentially taken by the AI
     * @param cp A ChessPiece object that could be potentially taken
     * by the AI
     * @return A ChessPiece object representing a chess piece that
     * could potentially be taken by the AI
     *****************************************************************/
    public ChessPiece newPieceGen(IChessPiece cp)  {
        newPiece = null;
        if(cp != null)   {
            //Want a new white piece
            if(cp.type().equals("King"))
                newPiece = new King(Player.WHITE);
            else if(cp.type().equals("Queen"))
                newPiece = new Queen(Player.WHITE);
            else if(cp.type().equals("Bishop"))
                newPiece = new Bishop(Player.WHITE);
            else if(cp.type().equals("Knight"))
                newPiece = new Knight(Player.WHITE);
            else if(cp.type().equals("Rook"))
                newPiece = new Rook(Player.WHITE);
            else if(cp.type().equals("Pawn"))
                newPiece = new Pawn(Player.WHITE);
            else
                newPiece = null;
        }
        return newPiece;
    }

    /******************************************************************
     * Handles the execution of the AI helper methods, and determines
     * the best move for the AI to make depending on the current
     * game board
     *****************************************************************/
    public void AI() {

        /*First the AI determines the location the white players King*/
        for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if(board[r][c] != null)
                    if(board[r][c].type().equals("King"))
                        if(board[r][c].player() == Player.WHITE)    {
                            rowWKing = r;
                            colWKing = c;
                        }

        /*Once the White king location has been determined,
         the AI checks to see if it can take that king*/
        for(int r=0; r<numRows(); r++)  {
            for(int c=0; c<numColumns(); c++)   {
                if(board[r][c] != null)
                    if(board[r][c].player() == Player.BLACK)    {
                        Move m = new Move(r, c, rowWKing, colWKing);
                        if(board[r][c].isValidMove(m, board))   {
                            move(m);
                            return;
                        }
                    }
            }
        }

        /*If the AI cannot attack the WHITE king, it checks to see if
        the
        AI is in check */
        if(inCheck(Player.BLACK))   {
                for(int r=0; r<numRows(); r++){
                for(int c=0; c<numColumns(); c++)   {
                    if(board[r][c] != null) {
                        if (board[r][c].player() == Player.BLACK) {
                            for (int x = 0; x < numRows(); x++) {
                                for (int y = 0; y < numColumns(); y++){
                                    Move m = new Move(r, c, x, y);
                                /*If the AI is in check, it sees if
                                any of it's pieces can make a move
                                to get out of check*/
                                    if (board[r][c].
                                            isValidMove(m, board)){
                                        newPiece = null;
                                        if (board[x][y] != null) {
                                            newPiece = newPieceGen(
                                                    board[x][y]);
                                        }
                                        move(m);
                                        /*If after the move, BLACK is
                                        still in check, revert the move
                                        and replace any piece that may
                                        have been there*/
                                        if (inCheck(Player.BLACK)) {
                                            Move m2 = new Move(x, y, r, c);
                                            move(m2);
                                            board[x][y] = newPiece;
                                        }
                                        /*If the move resulted in
                                        getting out of check, return*/
                                        else
                                            return;
                                    }
                                }
                            }
                        }
                    }
                }
                }
        }

        /*If the AI is not in check, see if it can put WHITE in check*/
        else if(AIcanPutWhiteInCheck()) {
            for(int r= 0; r<numRows(); r++) {
                for(int c=0; c<numColumns(); c++)   {
                    if(board[r][c] != null) {
                        if(board[r][c].player() == Player.BLACK)    {
                            for(int x=0; x<numRows(); x++)  {
                                for(int y=0; y<numColumns(); y++)   {
                                    /*See if the AI can make any move
                                    that will put WHITE in check*/
                                    Move m = new Move(r, c, x, y);

                                    /*If the move is valid, move*/
                                    if(board[r][c].
                                            isValidMove(m, board)){
                                        newPiece = null;
                                        if(board[x][y] != null) {
                                            newPiece = newPieceGen(
                                                    board[x][y]);
                                        }
                                        move(m);
                                        /*If the WHITE player is not in
                                        check, revert the move and
                                        replace the piece that was
                                        there*/
                                        if(!inCheck(Player.WHITE))   {
                                            Move m2 = new Move(
                                                    x,y,r,c);
                                            move(m2);
                                            board[x][y] = newPiece;
                                        }

                                        /*If the move sets WHITE into
                                        check, return*/
                                        else
                                            return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Checks to see if a black piece can attack a white piece
        if (AIattack(false)) {

            // Attacks a white piece
            AIattack(true);
            return;
        }

        //Checks to see if any of the Black Pieces are in Danger
        else if(AIblackPieceInDanger()) {
            for(int r=0; r<numRows(); r++)  {
            for(int c=0; c<numColumns(); c++)   {
                if(board[r][c] != null) {
                    if(board[r][c].player() ==
                            Player.BLACK)    {
                        /*If the AI is not in a safe spot, it needs to
                        be moved elsewhere*/
                        if(!AIisThisSpotSafe(r, c)) {
                            for(int x=0; x<numRows(); x++)  {
                            for(int y=0; y<numColumns(); y++) {
                                /*if the piece can move to take the
                                piece that puts it in danger, do that*/
                                if(board[x][y] != null) {
                                    if(board[x][y].player() ==
                                            Player.WHITE)    {
                                        Move m=new Move(r, c, x, y);
                                        if(board[r][c].isValidMove(m,
                                                board)){
                                            move(m);
                                            return;
                                        }
                                    }
                                }
                                /*If the piece that is in danger can't
                                go elsewhere, just move it*/
                                else    {
                                    Move m = new Move(r, c, x, y);
                                    if(board[r][c].
                                            isValidMove(m, board)){
                                        move(m);
                                        return;
                                    }
                                }
                            }
                            }
                        }
                    }
                }
            }
            }
        }

        /*If no AI moves have been made up to this point, a random move
        must be made. This is done by combing through the board spots
        and finding a AI piece to move, and then combing through and
        finding a spot that the piece can validly move to*/
        if((prevBoard.size() % 2) == 0) {
            /*If the move is an even move, it will check from right
            to left for a valid piece to move*/
            for(int r=0; r<numRows(); r++)   {
                for(int c=numColumns()-1; c>=0; c--)    {
                    if(board[r][c] != null) {
                        if(board[r][c].player() == Player.BLACK)    {
                            for(int x=numRows()-1; x>=0; x--)   {
                                for(int y=0; y<numColumns(); y++)   {
                                    /*Move the piece at (r, c) to the
                                    location (x, y)*/
                                    Move m = new Move(r, c, x, y);
                                    if(board[r][c].
                                            isValidMove(m, board))  {
                                        move(m);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else    {
            /*If the move is an odd move, it will check from left to
            right for a valid piece to move*/
            for(int r=0; r<numRows(); r++)  {
                for(int c=0; c<numColumns(); c++)   {
                    if(board[r][c] != null) {
                        if(board[r][c].player() == Player.BLACK)    {
                            for(int x=0; x<numRows(); x++)  {
                                for(int y=numColumns()-1; y>=0; y--){
                                    /*Move the piece at (r, c) to the
                                    location (x, y)*/
                                    Move m = new Move(r, c, x, y);
                                    if(board[r][c].
                                            isValidMove(m, board))  {
                                        move(m);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
