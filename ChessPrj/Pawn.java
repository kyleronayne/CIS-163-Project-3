package ChessPrj;

/**********************************************************************
 * Class that represents the Pawn chess piece. Determines whether the
 * pawn can move to a specified space. Also determines whether the
 * pawn has made their first move, which player they belong to, and
 * returns a string that represents the chess piece in this case
 * "Pawn"
 *********************************************************************/
public class Pawn extends ChessPiece {


    /** A boolean to represent whether it is the pawns first move */
    public boolean firstMove = false;


    /******************************************************************
     * Constructor for Pawn that specifies which player owns the pawn.
     * @param player the player who owns this pawn.
     *****************************************************************/
    public Pawn(Player player) {
        super(player);
    }


    /******************************************************************
     * Method to assign the chess piece a specific type in this case
     * "Pawn"
     * @return a string that represents the specific piece in this case
     * a "Pawn"
     *****************************************************************/
    public String type() {
        return "Pawn";
    }


    /******************************************************************
     * Determines whether the specified move is valid for a pawn to
     * make. Also determines whether it is the pawns first move
     * allowing them to move two spaces.
     * @param move the attempted move the player is attempting to make.
     * @param board the current board being played on.
     * @return false if the move is not fundamentally valid, or if the
     * move is not specifically valid for a pawn to make either.
     * True otherwise.
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        /* Variable to determine whether the pawn can be enPassant
        claimed */
        boolean enPassant = false;

        // Is the move being attempted fundamentally valid?
        boolean superValid = super.isValidMove(move, board);

        // Variable to determine whether the move is valid
        boolean validMove = false;

        // Does this pawn belong to the White player?
        if (this.player() == Player.WHITE) {
            /* Is the pawn attempting to move two spaces from its
             initial position*/
            if (move.fromRow == 6 && move.toRow == move.fromRow - 2 &&
                    move.toColumn == move.fromColumn) {
                /* Verify that the there is no piece in front of the
                 pawn either 1 or 2 spaces ahead of it */
                if (board[move.fromRow - 1][move.toColumn] == null
                        && board[move.fromRow - 2][move.toColumn]
                        == null) {
                    validMove = true;

                    if (super.isValidMove(move, board)) ;

                    // Move was made as a first move
                    setFirstMove(true);
                }
            }

            // Is the attempted move one space forward?
            if ((move.toRow == move.fromRow - 1) && (move.toColumn ==
                    move.fromColumn)) {
                // Verify no piece is at this location
                if (board[move.toRow][move.toColumn] == null) {
                    validMove = true;
                }
            }

            // Check if the pawn is trying to move diagonally
            else if ((move.toRow == move.fromRow - 1) &&
                    ((move.toColumn == move.fromColumn + 1) ||
                            (move.toColumn == move.fromColumn - 1))) {

                /* Check if there is a piece of the opposite player
                 at that place*/
                try {
                    if (board[move.toRow][move.toColumn].player() ==
                            Player.BLACK) {
                        validMove = true;
                    }
                } catch (NullPointerException e) {
                }
            }

            // In range for an enPassant maneuver?
            if ((move.toRow == 2) &&
                    (move.toRow == move.fromRow - 1) &&
                    ((move.toColumn == move.fromColumn + 1) ||
                            (move.toColumn == move.fromColumn - 1))) {

                // Verify if there is a piece next to this pawn
                if (board[move.toRow + 1][move.toColumn] != null)

                    // if this piece is the other player and a pawn
                    if (board[move.toRow + 1][move.toColumn].player()
                            == Player.BLACK)
                        if (board[move.toRow + 1][move.toColumn].
                                type().equals("Pawn"))

                            /* Check if that pawn has made its first
                             move */
                            if (((Pawn) board[move.toRow + 1]
                                    [move.toColumn]).getFirstMove()) {
                                validMove = true;
                            }
            }

        }

        // Does the pawn belong to the Black player?
        if (this.player() == Player.BLACK) {

            // Is the move attempted a move forward two spaces?
            if ((move.fromRow == 1) && (move.toRow == move.fromRow + 2)
                    && (move.toColumn == move.fromColumn)) {
                /* Verify that there is no piece between both the
                 attempted move spaces */
                if ((board[move.fromRow + 1][move.toColumn] == null) &&
                        board[move.fromRow + 2][move.fromColumn]
                                == null) {
                    validMove = true;

                    if(super.isValidMove(move, board));

                    // Make sure this is noted as the first move
                    setFirstMove(true);
                }
            }

            // Attempted move is down 1 space
            if ((move.toRow == move.fromRow + 1) && (move.toColumn ==
                    move.fromColumn)) {
                // The space is not occupied by any other pieces?
                if (board[move.toRow][move.toColumn] == null) {
                    validMove = true;
                }
            }

            // Check if the player is trying to move diagonally
            else if ((move.toRow == move.fromRow + 1) &&
                    ((move.toColumn == move.fromColumn + 1)
                    || (move.toColumn == move.fromColumn - 1))) {

                /* Check if there is a piece of the opposite player
                at that place */
                try {
                    if (board[move.toRow][move.toColumn].player() ==
                            Player.WHITE)
                        validMove = true;
                }
                catch (NullPointerException e) {}
            }

            // Attempt an enPassant maneuver
            if((move.toRow == 5) && (move.toRow == move.fromRow+1)
                    && ((move.toColumn == move.fromColumn + 1) ||
                    (move.toColumn == move.fromColumn - 1))) {
                /* verify that there is a piece next to the current
                pawn and of the opposite player and is a pawn*/
                if (board[move.toRow - 1][move.toColumn] != null)
                    if (board[move.toRow - 1][move.toColumn].player()
                            == Player.WHITE)
                        if (board[move.toRow - 1][move.toColumn]
                                .type().equals("Pawn"))
                            /* Check if that pawn just made their
                            first move */
                            if (((Pawn) board[move.toRow - 1]
                                    [move.toColumn]).getFirstMove()) {
                                validMove = true;
                            }
            }
        }
        return validMove&&superValid;
    }


    /******************************************************************
     * Method that holds whether the move made was the pawns first move
     * @return firstMove true if the pawn just made a double move,
     * false otherwise.
     *****************************************************************/
    public boolean getFirstMove()   {
        return firstMove;
    }


    /******************************************************************
     * Method that sets the pawns first move variable
     * @param wasThisFirstMove a check made in the pawn class that
     * determines whether the pawn has just made its first move
     *****************************************************************/
    public void setFirstMove(boolean wasThisFirstMove) {
        firstMove = wasThisFirstMove;
    }
}