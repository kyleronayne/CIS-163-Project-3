package ChessPrj;

public class Pawn extends ChessPiece {

    public Pawn(Player player) {
        super(player);
    }

    public String type() {
        return "Pawn";
    }

    // determines if the move is valid for a pawn piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        boolean superValid = super.isValidMove(move, board);
        boolean valid = false;
        //FIXME: Add functionality for capturing pieces and moving rows
        //FIXME: Add functionality to determine forward/backward movement
        if(this.player() == Player.WHITE)   {
            if (move.fromRow == 6 && move.toRow == move.fromRow - 2) {
                valid = true;
            }

            if((move.toRow == (move.fromRow - 1)) && (move.toColumn == move.fromColumn))
                valid = true;
            else if((move.toRow == (move.fromRow-1)) && ((move.toColumn == move.fromColumn+1) || (move.toColumn == move.fromColumn-1)))
                try{
                    if(board[move.toRow][move.toColumn].player() == Player.BLACK)
                        valid = true;
                }
                catch(NullPointerException e){}
        }
        if(this.player() == Player.BLACK)   {
            if((move.toRow == (move.fromRow + 1)) && (move.toColumn == move.fromColumn))
                valid = true;
            else if((move.toRow == (move.fromRow+1)) && ((move.toColumn == move.fromColumn+1) || (move.toColumn == move.fromColumn-1)))
                try{
                    if(board[move.toRow][move.toColumn].player() == Player.WHITE)
                        valid = true;
                }
                catch(NullPointerException e){}
        }
        return valid&&superValid;
    }
}