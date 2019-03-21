package ChessPrj;

/**********************************************************************
 * Class that represents the Knight chess piece. Determines whether a
 * a specified move is valid for a Knight to make in terms of the rules
 * of chess. Also assigns the piece to the specified player, and assigns
 * the chess piece to be of type "Knight".
 *********************************************************************/
public class Knight extends ChessPiece {

    /******************************************************************
     * The Knight constructor creates an instance of the Knight class
     * belonging to the player specified in param player
     *
     * @param player The player that this Knight belongs to
     *****************************************************************/
    public Knight(Player player) {
        super(player);
    }

    /******************************************************************
     * This method returns a string representation of the class. In
     * this case, type() would return the string "Knight"
     *
     * @return a string with "Knight" which tells the user which piece
     * it is
     *****************************************************************/
    public String type() {
        return "Knight";
    }

    /******************************************************************
     * isValidMove verifies first that the move is valid for any piece
     * (i.e. it is not moving on top of its own piece or is not out
     * of bounds) and then verifies that the movement is correct for a
     * Knight
     *
     * @param move the move being checked for validity
     * @param board the array of chesspieces and their relations to one
     *              another
     * @return true if the move in question is valid, false if not
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board){

        //Make sure that the move is acceptable by ChessPiece
        boolean superValidMove = super.isValidMove(move, board);
        boolean validMove = false;

        // Piece moving up or down two spaces and over one space
        // right or left
        if ((move.toRow == move.fromRow - 2) ||
                (move.toRow == move.fromRow + 2)) {
            if ((move.toColumn == move.fromColumn - 1) ||
                    (move.toColumn == move.fromColumn + 1)) {
                validMove = true;
            }
        }

        // Piece moving right or left two spaces and up or down one
        // space
        if ((move.toColumn == move.fromColumn + 2) || (
                move.toColumn == move.fromColumn - 2)) {
            if ((move.toRow == move.fromRow + 1) ||
                    (move.toRow == move.fromRow - 1)) {
                validMove = true;
            }
        }

        //If both isValidMoves are true, then it is valid
        return superValidMove&&validMove;
    }
}