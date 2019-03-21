package ChessPrj;

/**********************************************************************
 * Class that represents the Rook chess piece. Determines whether it's
 * specified move is valid in terms of how a Rook should be able to
 * move. Also specifies the chess piece as a "Rook"
 *********************************************************************/
public class Rook extends ChessPiece {

    /** Boolean to determine whether the rook has ever moved **/
    private boolean rookMoved;

    /******************************************************************
     * Constructor for Rook which calls chessPiece to obtain who this
     * piece belongs to. Also begins by setting rookMoved to false
     * because the piece has not yet been moved
     * @param player the player who's piece the Rook belongs to.
     *****************************************************************/
    public Rook(Player player) {
        super(player);
        rookMoved = false;
    }

    /******************************************************************
     * Tells the user what this chess piece represent. What piece is
     * this specific piece. In this case "Rook"
     * @return the type that the chess piece represents in this case
     * "Rook"
     *****************************************************************/
    public String type() {
        String type = "Rook";
        return type;
    }

    /******************************************************************
     * Determines whether the move is a valid move that a Rook can
     * take in a game of chess.
     * @param move the move the player is attempting
     * @param board the chess board that is currently being played on
     * @return false if the move is not a fundamentally valid move, or
     * if the move is not a valid move a rook can take. True otherwise.
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        // Was the move fundamentally valid?
        boolean superValid = super.isValidMove(move, board);

        // A variable to check whether the move is good or not
        boolean valid = false;

        /* If the player is moving between rows and the column stays
           the same. */
        if(move.toColumn == move.fromColumn &&
                move.fromRow != move.toRow){
            rookMoved = true;
            valid = true;

            // Rook is moving towards black side
            if (move.toRow < move.fromRow) {
                // Does this move attempt to move through any pieces?
                for (int i = 1; i < Math.abs(move.fromRow -
                        move.toRow); i++) {
                    if (!(board[move.fromRow - i][move.fromColumn] == null)) {
                        rookMoved = false;
                        return false;
                    }
                }
            }

            // Rook is moving towards white side
            if (move.toRow > move.fromRow) {
                // Does this move attempt to move through any pieces?
                for (int i = 1; i < Math.abs(move.fromRow -
                        move.toRow); i++) {
                    if (!(board[move.fromRow + i][move.fromColumn] == null)) {
                        rookMoved = false;
                        return false;
                    }
                }
            }
        }

        /* If the player is moving between columns and the row stays
           the same*/
        else if(move.toRow == move.fromRow &&
                move.toColumn != move.fromColumn){
            rookMoved = true;
            valid = true;

            // Rook is moving to the left
            if (move.toColumn < move.fromColumn) {
                for (int i = 1; i < Math.abs(move.fromColumn -
                        move.toColumn); i++) {
                    // Does it attempt to move through any pieces?
                    if (!(board[move.fromRow][move.fromColumn - i]
                            == null)) {
                        rookMoved = false;
                        return false;
                    }
                }
            }

            // Rook is moving to the right
            if (move.toColumn > move.fromColumn) {
                for (int i = 1; i < Math.abs(move.fromColumn -
                        move.toColumn); i++) {
                    // Does it attempt to move through any pieces?
                    if (!(board[move.fromRow][move.fromColumn + i]
                            == null)) {
                        rookMoved = false;
                        return false;
                    }
                }
            }
        }
        return valid&&superValid;

    }

    /******************************************************************
     * Determines whether the rook has been moved
     * @return rookMoved true if rook has moved, false otherwise.
     *****************************************************************/
    public boolean isRookMoved() {
        return rookMoved;
    }
}

