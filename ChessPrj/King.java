package ChessPrj;

/**********************************************************************
 * Class that represents the King Chess Piece. Determines whether the
 * king can move to a specified place. Also keeps track of whether or
 * not the king has move for reference for Castling. Sets the king to
 * a specific player and sets it's type to "King"
 *********************************************************************/
public class King extends ChessPiece {


    /** Variable to state whether the White king has moved */
    private boolean WkingMoved;

    /** Variable to state whether the Black king has moved */
    private boolean BkingMoved;


    /******************************************************************
     * Constructor for the king class. Assigns the king piece to the
     * passed in player making them owner. Also States that the king
     * has not moved.
     * @param player the player that owns the piece
     *****************************************************************/
    public King(Player player) {
        super(player);
        WkingMoved = false;
        BkingMoved = false;
    }


    /******************************************************************
     * Specifies the chess pieces type. In this case the piece is a
     * King assigning the piece to it.
     * @return a string with the name of the piece in this case "King"
     *****************************************************************/
    public String type() {
        return "King";
    }


    /******************************************************************
     * Determines whether the attempted move is valid. Checking that
     * the piece follows in line with the fundamentals as well as
     * specific rules that this piece must follow in terms of movement.
     * @param move the move being attempted
     * @param board the current board that is being played on
     * @return false if the move is not a valid move either
     * fundamentally, or specific to the piece. True otherwise.
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        // Is the move fundamentally valid?
        boolean superValid = super.isValidMove(move, board);

        // Variable to determine whether attempted move is valid
        boolean valid = false;

        // Integers to hold the row and column
        int r,c;

        // Interger to specify the size of the board
        int boardSize = 8;

        /* Trying to move down 1 row on the board with column not
           changing */
        if((move.fromRow+1 == move.toRow)&&
                (move.fromColumn == move.toColumn)) {
            // First check that king is not moving itself into check
            // Loop through all rows and columns
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)

                    // Check if there is a piece here
                    if(board[r][c] != null)

                        // Check if the piece is not your own piece
                        if(board[r][c].player() != super.player()) {

                            // Check if they can take your king
                            Move newMove = new
                                    Move(r,c,move.toRow,move.toColumn);

                            // Can they take the king?
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }

            // Check if the from space is not occupied
            if(board[move.fromRow][move.fromColumn] != null)

                // verify that it is a king
                if(board[move.fromRow][move.fromColumn].type().
                        equals("King"))

                    // Determine which king is moving
                    if(board[move.fromRow][move.fromColumn].player()
                            == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
            valid = true;
        }

        // Piece is moving down 1 and right 1 same check as before
        else if((move.fromRow+1 == move.toRow)&&
                (move.fromColumn+1 == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new
                                    Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().
                        equals("King"))
                    if(board[move.fromRow][move.fromColumn].player()
                            == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
            valid = true;
        }

        // Piece is moving right 1 same checks as first
        else if((move.fromRow == move.toRow)&&
                (move.fromColumn+1 == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new
                                    Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().
                        equals("King"))
                    if(board[move.fromRow][move.fromColumn].player()
                            == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
            valid = true;
        }

        // Piece is moving back 1 and right 1 same check as first
        else if((move.fromRow-1 == move.toRow)&&
                (move.fromColumn+1 == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new
                                    Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().
                        equals("King"))
                    if(board[move.fromRow][move.fromColumn].player()
                            == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
            valid = true;
        }

        // Piece is moving back 1 same checks as first
        else if((move.fromRow-1 == move.toRow)&&
                (move.fromColumn == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new
                                    Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().
                        equals("King"))
                    if(board[move.fromRow][move.fromColumn].player()
                            == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
            valid = true;
        }

        // Piece is moving back 1 and left 1 same checks as first
        else if((move.fromRow-1 == move.toRow)&&
                (move.fromColumn-1 == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new
                                    Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().
                        equals("King"))
                    if(board[move.fromRow][move.fromColumn].player()
                            == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
            valid = true;
        }

        // Piece is moving left 1 same check as first
        else if((move.fromRow == move.toRow)&&
                (move.fromColumn-1 == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new
                                    Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().
                        equals("King"))
                    if(board[move.fromRow][move.fromColumn].player()
                            == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
            valid = true;
        }

        // Piece is moving down 1 and left 1 same check as first
        else if((move.fromRow+1 == move.toRow)&&
                (move.fromColumn-1 == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new
                                    Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().
                        equals("King"))
                    if(board[move.fromRow][move.fromColumn].player()
                            == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
            valid = true;
        }

        // Is he king trying to castle? (Special case)
        else if (move.toColumn == move.fromColumn - 2 ||
                move.toColumn == move.fromColumn + 2) {

            // Is this the white player?
            if (this.player() == Player.WHITE) {
                // Has the king moved yet?
                if (!WkingMoved) {

                    // Are they attempting a castle from the right?
                    if(move.toColumn == move.fromColumn +2) {

                        // Is there a rook on their right?
                        if(board[7][7] != null)
                            if(board[7][7].type().equals("Rook"))
                                // Verify nothing in between
                                if(board[7][5] == null &&
                                        board[7][6] == null)  {
                                    WkingMoved=true;
                                    valid = true;
                                }
                    }
                    // Attempting to castle from the left?
                    else if(move.toColumn == move.fromColumn-2) {

                        // Is there a rook on their left?
                        if(board[7][0] != null)
                            if(board[7][0].type().equals("Rook"))
                                // Verify no other pieces in between
                                if(board[7][1] == null && board[7][2]
                                        == null && board[7][3]==null) {
                                    WkingMoved = true;
                                    valid = true;
                                }
                    }

                }
            }

            // Is this the black player?
            if (this.player() == Player.BLACK) {
                // Has the king moved yet?
                if (!BkingMoved) {

                    // Attempting to castle to the left?
                    if(move.toColumn == move.fromColumn-2)  {

                        // Is there a rook at the valid space?
                        if(board[0][0] != null)
                            if(board[0][0].type().equals("Rook"))
                                // Verify no other pieces in between
                                if(board[0][1] == null &&
                                        board[0][2] == null &&
                                board[0][3] == null) {
                                    BkingMoved = true;
                                    valid = true;
                                }
                    }

                    // Attempting to castle to the right?
                    else if(move.toColumn == move.fromColumn+2) {

                        // Is there a rook at the valid space?
                        if(board[0][7] != null)
                            if(board[0][7].type().equals("Rook"))
                                // Verify no other pieces in between
                                if(board[0][5]
                                        == null && board[0][6]==null) {
                                    BkingMoved = true;
                                    valid = true;
                                }
                    }
                }
            }
        }

        return valid&&superValid;
    }
}