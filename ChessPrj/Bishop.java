package ChessPrj;
import java.lang.*;

/**********************************************************************
 * Class that represents the Bishop chess piece. Determines whether a
 * given move is valid in terms of how a Bishop should be able to move.
 * assigns the Bishop to a player, and set's its type to "Bishop"
 *********************************************************************/
public class Bishop extends ChessPiece {

    /******************************************************************
     * Constructor calls the ChessPiece classes' constructor
     * @param player A Player object
     */
    public Bishop(Player player) {
        super(player);
    }


    /******************************************************************
     * Returns a String denoting the piece type
     * @return A String denoting the piece type
     */
    public String type() {

        return "Bishop";
    }

    /******************************************************************
     * Returns a boolean representing whether the specified Move is
     * valid
     * @param move A Move object representing a player's desired move
     * @param board
     * @return A boolean representing whether the specified Move is
     * valid
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        //Is the move fundamentally valid?
        boolean superValid = super.isValidMove(move, board);

        // place holder that holds whether the move is valid or not
        boolean valid = false;

        /* Is the attempted move in the 4 cardinal directions
         Up, Down, Left, or Right */
        if((move.fromRow == move.toRow) || (move.fromColumn ==
                move.toColumn))
            valid = false;

        /* Is the attempted move a diagonal movement. Does the piece
         change the same number of rows and columns*/
        if((Math.abs(move.fromRow - move.toRow) == Math.abs(move.fromColumn - move.toColumn))) {
            valid = true;

            // Attempted move is up and to the left
            if((move.fromRow > move.toRow) && (move.fromColumn > move.toColumn)) {
                for(int i=1; i<Math.abs(move.fromRow - move.toRow); i++)    {
                    if(!(board[move.fromRow-i][move.fromColumn-i] == null))
                        return false;
                }
            }

            // Attempted move is down and to the right
            else if((move.fromRow < move.toRow) && (move.fromColumn < move.toColumn)) {
                for(int i=1; i<Math.abs(move.fromRow - move.toRow); i++)    {
                    if(!(board[move.fromRow+i][move.fromColumn+i] == null))
                        return false;
                }
            }

            // Attempted move is up and to the right
            else if((move.fromRow > move.toRow) && (move.fromColumn < move.toColumn)) {
                for(int i=1; i<Math.abs(move.fromRow - move.toRow); i++)    {
                    if(!(board[move.fromRow-i][move.fromColumn+i] == null))
                        return false;
                }
            }

            // Attempted move is down and to the left
            else if((move.fromRow < move.toRow) && (move.fromColumn > move.toColumn)) {
                for(int i=1; i<Math.abs(move.fromRow - move.toRow); i++)    {
                    if(!(board[move.fromRow+i][move.fromColumn-i] == null))
                        return false;
                }
            }
        }


        return valid&&superValid;
    }
}