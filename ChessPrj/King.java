package ChessPrj;

public class King extends ChessPiece {

    /******************************************************************
     * This is the contructor method for the King class which creates
     * a King piece for the param player.
     *
     * @param player the player (either white or black) that this
     *               piece belongs to.
     *****************************************************************/
    public King(Player player) {
        super(player);
    }

    /******************************************************************
     * This method returns a string representation of what this piece
     * is (in this case, this is a king so "King" is returned.
     *
     * @return the type of this piece
     *****************************************************************/
    public String type() {
        return "King";
    }

    /******************************************************************
     * Is valid move calls the ChessPiece isValidMove() to verify
     * the the attempted move is valid. It next makes sure that the
     * move is valid specifically for a king. A king can move to any
     * of the 8 surrounding spots (as long as those spots exist and
     * are not filled with pieces on its own team, or that the move
     * does not put it into check).
     *
     * @param move the move that is being considered
     * @param board an array of IChessPieces that shows the location of
     *              all of the pieces on the board.
     * @return true if the move is valid, false is the move is invalid
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        //Calls the ChessPiece.isValidMove method to verify
        //that this move can be made in the first place
        boolean superValid = super.isValidMove(move, board);
        boolean valid = false;

        //row and column placeholders in for loop
        int r,c;

        //board size variable
        int boardSize = 8;

        //Checks to see if the move is one space directly down
        if((move.fromRow+1 == move.toRow)&&
                (move.fromColumn == move.toColumn)) {

            //Checks to make sure that the king is not moving directly
            //into check
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        //if the piece at this location is the other
                        //player, see if it can make a valid move onto
                        //the king
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,
                                    move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            //If nothing can move onto the king, the move is valid
            valid = true;
        }

        //Check to see if the king is moving down and to the right
        else if((move.fromRow+1 == move.toRow)&&
                (move.fromColumn+1 == move.toColumn)) {

            //Checks to make sure that the king is not moving directly
            //into check
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        //if the piece at this location is the other
                        //player, see if it can make a valid move onto
                        //the king
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,
                                    move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            //If nothing can move onto the king, the move is valid
            valid = true;
        }

        //Checks to see if king is moving to the right one spot
        else if((move.fromRow == move.toRow)&&
                (move.fromColumn+1 == move.toColumn)) {

            //Checks to make sure that the king is not moving directly
            //into check
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        //if the piece at this location is the other
                        //player, see if it can make a valid move onto
                        //the king
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,
                                    move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            //If nothing can move onto the king, the move is valid
            valid = true;
        }

        //Checks to see if king is mocing up and to the right
        else if((move.fromRow-1 == move.toRow)&&
                (move.fromColumn+1 == move.toColumn)) {

            //Checks to make sure that the king is not moving directly
            //into check
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        //if the piece at this location is the other
                        //player, see if it can make a valid move onto
                        //the king
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,
                                    move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            //If nothing can move onto the king, the move is valid
            valid = true;
        }

        //Checks to see if king is moving up one space
        else if((move.fromRow-1 == move.toRow)&&(move.fromColumn == move.toColumn)) {

            //Checks to make sure that the king is not moving directly
            //into check
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        //if the piece at this location is the other
                        //player, see if it can make a valid move onto
                        //the king
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,
                                    move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            //If nothing can move onto the king, the move is valid
            valid = true;
        }

        //Checks to see if king is moving up and to the left
        else if((move.fromRow-1 == move.toRow)&&
                (move.fromColumn-1 == move.toColumn)) {

            //Checks to make sure that the king is not moving directly
            //into check
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        //if the piece at this location is the other
                        //player, see if it can make a valid move onto
                        //the king
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,
                                    move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            //If nothing can move onto the king, the move is valid
            valid = true;
        }

        //Checks to see if king is moving one space to the left
        else if((move.fromRow == move.toRow)&&
                (move.fromColumn-1 == move.toColumn)) {

            //Checks to make sure that the king is not moving directly
            //into check
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        //if the piece at this location is the other
                        //player, see if it can make a valid move onto
                        //the king
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,
                                    move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            //If nothing can move onto the king, the move is valid
            valid = true;
        }

        //Checks if king is moving down and to the left
        else if((move.fromRow+1 == move.toRow)&&
                (move.fromColumn-1 == move.toColumn)) {

            //Checks to make sure that the king is not moving directly
            //into check
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        //if the piece at this location is the other
                        //player, see if it can make a valid move onto
                        //the king
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,
                                    move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            //If nothing can move onto the king, the move is valid
            valid = true;
        }

        return valid&&superValid;
    }
}
