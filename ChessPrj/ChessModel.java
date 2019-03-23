package ChessPrj;
import java.util.ArrayList;
import java.util.Random;

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

    private Random random;

    private int rowWKing;
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
        random = new Random();
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
        board[0][3] = new King(Player.BLACK);
        board[0][4] = new Queen(Player.BLACK);
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
     * Places a piece at the specified row and column on the board.
     * @param row the row being looked at.
     * @param column the column being looked at.
     * @param piece the piece in question you want to place at that
     * location.
     *****************************************************************/
    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    /******************************************************************
     * Saves the current board and adds it tho the list of
     * board history.
     *****************************************************************/
    public void saveBoard() {
        prevBoard.add(generateNewBoard());
    }

    /******************************************************************
     * Reverts to the previous board that is stored in the list.
     * FIXME: Need a way to save the move made to, so we can undo check and allow castling to be reattempted.
     * FIXME: Currently works in a simple state add functionality if there's time.
     * @return true if there is a board history, false otherwise.
     *****************************************************************/
    public boolean goToLastBoard() {
        if((prevBoard.size() <=1) || ((prevBoard.size() == 2) &&
                (hasUndoneToStart)))   {
            prevBoard.clear();
            prevBoard.add(generateNewBoard(startBoard));
            board = prevBoard.get(0);
            player = Player.WHITE;
            hasUndoneToStart = true;
            return true;
        }

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

        for(int i=0; i<numRows(); i++)
            for(int j=0; j<numColumns(); j++) {
                if(arrayToCopy[i][j] != null)
                    copy[i][j] = arrayToCopy[i][j];
            }
        return copy;
    }

    private boolean AIcanPutWhiteInCheck()  {

        for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if(board[r][c]!=null)
                    if(board[r][c].player() == Player.BLACK)    {
                        for(int i=0; i<numRows(); i++)
                            for(int j=0; j<numColumns(); j++)   {
                                Move m = new Move(r, c, i, j);
                                Move m2 = new Move(i, j, r, c);
                                if(board[r][c].isValidMove(m, board)) {
                                    newPiece = null;
                                    if(pieceAt(i, j) != null)   {
                                        //Want a new white piece
                                        if(pieceAt(i, j).type().equals("King"))
                                            newPiece = new King(Player.WHITE);
                                        else if(pieceAt(i, j).type().equals("Queen"))
                                            newPiece = new Queen(Player.WHITE);
                                        else if(pieceAt(i, j).type().equals("Bishop"))
                                            newPiece = new Bishop(Player.WHITE);
                                        else if(pieceAt(i, j).type().equals("Knight"))
                                            newPiece = new Knight(Player.WHITE);
                                        else if(pieceAt(i, j).type().equals("Rook"))
                                            newPiece = new Rook(Player.WHITE);
                                        else if(pieceAt(i, j).type().equals("Pawn"))
                                            newPiece = new Pawn(Player.WHITE);
                                        else
                                            newPiece = null;
                                    }
                                    move(m);
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


    private boolean AIblackPieceInDanger()  {
        for(int r=0; r<numRows(); r++)  {
            for(int c=0; c<numColumns(); c++)   {
                if(board[r][c] != null) {
                    if(board[r][c].player() == Player.WHITE)    {
                        for(int x=0; x<numRows(); x++)  {
                            for(int y=0; y<numColumns(); y++)   {
                                if(board[x][y] != null) {
                                    if(board[x][y].player() == Player.BLACK) {
                                        Move m = new Move(r, c, x, y);
                                        if (board[r][c].isValidMove(m, board)) {
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

    private boolean AIisThisSpotSafe(int row, int col)  {
        for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if(board[r][c] != null)
                    if(board[r][c].player() == Player.WHITE)    {
                        Move m = new Move(r, c, row, col);
                        if(board[r][c].isValidMove(m, board))
                            return false;
                    }
        return true;
    }

    public boolean noMovesMade()    {
        return board.equals(startBoard);
    }


    public void useAI(boolean ai)  {
        if(ai)
            usingAI = true;
        else
            usingAI = false;

    }

    public boolean AIisUsed()   {
        return usingAI;
    }

    public ChessPiece determineWhatPieceToMake(IChessPiece cp)  {
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

    public void AI() {
        /*
         * Write a simple AI set of rules in the following order.
         * a. Check to see if you are in check.
         * 		i. If so, get out of check by moving the king or placing a piece to block the check
         *
         * b. Attempt to put opponent into check (or checkmate).
         * 		i. Attempt to put opponent into check without losing your piece
         *		ii. Perhaps you have won the game.
         *
         *c. Determine if any of your pieces are in danger,
         *		i. Move them if you can.
         *		ii. Attempt to protect that piece.
         *
         *d. Move a piece (pawns first) forward toward opponent king
         *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
         */
        for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if(board[r][c] != null)
                    if(board[r][c].type().equals("King"))
                        if(board[r][c].player() == Player.WHITE)    {
                            rowWKing = r;
                            colWKing = c;
                        }

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

        if(inCheck(Player.BLACK))   {
                for(int r=0; r<numRows(); r++){
                    for(int c=0; c<numColumns(); c++)   {
                        if(board[r][c] != null) {
                            if(board[r][c].player() == Player.BLACK)    {
                                for(int x=0; x<numRows(); x++)  {
                                    for(int y=0; y<numColumns(); y++)   {
                                        Move m = new Move(r, c, x, y);
                                        if(board[r][c].isValidMove(m, board))   {
                                            newPiece = null;
                                            if(board[x][y] != null) {
                                                newPiece = determineWhatPieceToMake(board[x][y]);
                                            }
                                            move(m);
                                            if(inCheck(Player.BLACK))   {
                                                Move m2 = new Move(x, y, r, c);
                                                move(m2);
                                                board[x][y] = newPiece;
                                            }
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

        else if(AIcanPutWhiteInCheck()) {
            for(int r= 0; r<numRows(); r++) {
                for(int c=0; c<numColumns(); c++)   {
                    if(board[r][c] != null) {
                        if(board[r][c].player() == Player.BLACK)    {
                            for(int x=0; x<numRows(); x++)  {
                                for(int y=0; y<numColumns(); y++)   {
                                    Move m = new Move(r, c, x, y);
                                    if(board[r][c].isValidMove(m, board))   {
                                        newPiece = null;
                                        if(board[x][y] != null) {
                                            newPiece = determineWhatPieceToMake(board[x][y]);
                                        }
                                        move(m);
                                        if(!inCheck(Player.WHITE))   {
                                            Move m2 = new Move(x, y, r, c);
                                            move(m2);
                                            board[x][y] = newPiece;
                                        }
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

        else if(AIblackPieceInDanger()) {
            for(int r=0; r<numRows(); r++)  {
                for(int c=0; c<numColumns(); c++)   {
                    if(board[r][c] != null) {
                        if(board[r][c].player() == Player.BLACK)    {
                            if(!AIisThisSpotSafe(r, c)) {
                                for(int x=0; x<numRows(); x++)  {
                                    for(int y=0; y<numColumns(); y++)   {
                                        if(board[x][y] != null) {
                                            if(board[x][y].player() == Player.WHITE)    {
                                                Move m = new Move(r, c, x, y);
                                                if(board[r][c].isValidMove(m, board))   {
                                                    move(m);
                                                    return;
                                                }
                                            }
                                        }
                                        else    {
                                            Move m = new Move(r, c, x, y);
                                            if(board[r][c].isValidMove(m, board))   {
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

        if((prevBoard.size() % 2) == 0) {
            for(int r=0; r<numRows(); r++)   {
                for(int c=numColumns()-1; c>=0; c--)    {
                    if(board[r][c] != null) {
                        if(board[r][c].player() == Player.BLACK)    {
                            for(int x=numRows()-1; x>=0; x--)   {
                                for(int y=0; y<numColumns(); y++)   {
                                    Move m = new Move(r, c, x, y);
                                    if(board[r][c].isValidMove(m, board))   {
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
            for(int r=0; r<numRows(); r++)  {
                for(int c=0; c<numColumns(); c++)   {
                    if(board[r][c] != null) {
                        if(board[r][c].player() == Player.BLACK)    {
                            for(int x=0; x<numRows(); x++)  {
                                for(int y=numColumns()-1; y>=0; y--)    {
                                    Move m = new Move(r, c, x, y);
                                    if(board[r][c].isValidMove(m, board))   {
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

        System.out.println("YOU HIT BOTTOM");
    }
}
