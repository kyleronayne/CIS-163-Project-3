package ChessPrj;

/**********************************************************************
 * Class that represents the Queen chess piece. Determines whether
 * the queen is able to make a specified move, sets the piece to a
 * specific player, and make the piece type "Queen"
 *********************************************************************/
public class Queen extends ChessPiece {

    /******************************************************************
     * The Queen constructor creates an object of type queen belonging
     * to the specified param player
     *
     * @param player the player that the queen class belongs to
     *****************************************************************/
    public Queen(Player player) {
        super(player);
    }

    /******************************************************************
     * The type method returns a string representative of what the
     * piece is. Because this is the Queen class, "Queen" would be
     * the output
     * @return a String representation of this piece, "Queen"
     *****************************************************************/
    public String type() {
        return "Queen";

    }

    /******************************************************************
     * This method utilizes the already written Bishop and Rook
     * isValidMove methods because the Queen can move in each of the
     * ways that Bishops and rooks can move in. Therefore, Queen uses
     * the isValidMove from each of those pieces.
     *
     * @param move the move to be made
     * @param board the array of IChessPieces and their locations
     *              relative to one another
     * @return true if the move is valid, false if the move isn't valid
     ******************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        //Create a Bishop and a Rook at the same spot as the Queen
        Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].
                player());
        Rook move2 = new Rook(board[move.fromRow][move.fromColumn].
                player());

        //Check if either the Bishop or Rook can move, if either can,
        //the move is valid for the Queen
        return (move1.isValidMove(move, board) ||
                move2.isValidMove(move, board));
    }
}
