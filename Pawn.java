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
        if(((move.toRow==(move.fromRow+1)) ||
                (move.toRow==(move.fromRow-1))) &&
                (move.toColumn != move.fromColumn))
            valid = true;
        if(((move.toColumn == (move.fromColumn+1)) ||
                (move.toColumn == (move.fromColumn-1))) &&
                ((move.toRow==(move.fromRow+1)) ||
                (move.toRow==(move.fromRow-1))) &&
                (board[move.toRow][move.toColumn].player().equals(
                        this.player())))
            valid = true;
        return valid&&superValid;
    }
}