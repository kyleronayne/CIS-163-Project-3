package ChessPrj;

/**********************************************************************
 * Class that hold the information for a chess piece. Determines
 * whether the move is one that any piece can fundamentally make, and
 * assigns the piece to an owner.
 *********************************************************************/
public abstract class ChessPiece implements IChessPiece {

    /** The player that owns the piece */
    private Player owner;

    /** The size of a chess board */
    private int boardSize = 8;

    //FIXME: Do we still need these??
    private int wKingRow;
    private int wKingCol;
    private int bKingRow;
    private int bKingCol;

    /******************************************************************
     * Constructor that assigns the owner to the chess piece.
     * @param player the player to whom this piece belongs.
     *****************************************************************/
    protected ChessPiece(Player player) {
        this.owner = player;
    }

    /******************************************************************
     * Abstract method that holds which chess piece this is.
     * @return the name of that chess piece.
     *****************************************************************/
    public abstract String type();

    /******************************************************************
     * Gets the owner of the this piece.
     * @return the owner of this piece.
     *****************************************************************/
    public Player player() {
        return owner;
    }

    /******************************************************************
     * Determines whether the specified move is fundamentally possible.
     * Whether the move is attempting to move on top of ones own piece,
     * not at all, or somewhere off the board.
     * @param move  a {@link ChessPrj.Move} object describing the move
     * to be made.
     * @param board the {@link } in which this piece resides.
     * @return false if the move is not fundamentally valid, true
     * otherwise.
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        //Verifies that starting and ending locations are different
        if (((move.fromRow == move.toRow) &&
                (move.fromColumn == move.toColumn)))
            return false;
        //FIXME: NEED TO DOUBLE CHECK FOR INVALID ARGUMENT

        // Verifies that the move is being made on the board.
        else if((move.fromRow > boardSize -1) || (move.fromRow < 0) ||
                (move.fromColumn > boardSize -1) ||
                (move.fromColumn < 0) ||
                (move.toRow > boardSize-1) || (move.toRow < 0) ||
                (move.toColumn > boardSize-1) || (move.toColumn < 0)) {

            throw new IndexOutOfBoundsException();

        }

        /* Verifies that the player is not trying to put their piece
        on top of one of their other pieces */
        try {
             if ((board[move.toRow][move.toColumn].player().equals(
                    this.player())))
                return false;
        }
        catch(NullPointerException e){

        }



        //If none of the above are true, the move is valid
        return true;
    }

}
