package ChessPrj;

public class Rook extends ChessPiece {

    /******************************************************************
     * The Rook constructor creates a new Rook belonging to the
     * specified player
     *
     * @param player the player to which the Rook belongs to
     *****************************************************************/
    public Rook(Player player) {
        super(player);
    }

    /******************************************************************
     * The type method returns a string represenation of this piece.
     * In this case, type will return "Rook"
     *
     * @return a string of "Rook" for the piece
     *****************************************************************/
    public String type() {
        return "Rook";
    }

    /******************************************************************
     * isValidMove verifies that the rook piece can move to a given
     * spot on the board detailed by move.
     *
     * @param move the move that is being considered
     * @param board the array of ChessPieces that represent where the
     *              pieces are on the board
     * @return true if the move is valid, false if not
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        //First check to make sure that the move is valid for any piece
        boolean superValid = super.isValidMove(move, board);
        boolean valid = false;

        //Check to see if the the Rook is moving straight up or down
        if(move.toColumn == move.fromColumn &&
                move.fromRow != move.toRow){
            valid = true;

            //If the Rook is going up
            if (move.toRow < move.fromRow) {
                for(int i=1;i<Math.abs(move.fromRow-move.toRow);i++) {
                    //if there are any pieces between the move from
                    //and move to spots, the move is invalid
                    if (!(board[move.fromRow - i][move.fromColumn] ==
                            null)) {
                        return false;
                    }
                }
            }

            //if the Rook is going down
            if (move.toRow > move.fromRow) {
                for(int i=1;i<Math.abs(move.fromRow-move.toRow);i++) {
                    //if there are any pieces between the move from
                    //and move to spots, the move is invalid
                    if (!(board[move.fromRow + i][move.fromColumn] ==
                            null)) {
                        return false;
                    }
                }
            }
        }
        //Check to see if the Rook is moving directly side to side
        else if(move.toRow == move.fromRow &&
                move.toColumn != move.fromColumn){
            valid = true;

            //if the Rook is going to the Left
            if (move.toColumn < move.fromColumn) {
                for(int i=1;
                    i<Math.abs(move.fromColumn-move.toColumn);i++) {
                    //if there are any pieces between the move from and
                    //move to spots, the move is invalid
                    if (!(board[move.fromRow][move.fromColumn - 1] ==
                            null))
                        return false;
                }
            }

            //if the Rook is going to the Right
            if (move.toColumn > move.fromColumn) {
                for (int i = 1;
                     i<Math.abs(move.fromColumn-move.toColumn);i++) {
                    //if there are any pieces betweent the move from
                    //and move to spots, the move is invalid
                    if (!(board[move.fromRow][move.fromColumn + 1] == null))
                        return false;
                }
            }
        }

        //if both isValidMoves return true, the move is valid
        return valid&&superValid;

    }

}
