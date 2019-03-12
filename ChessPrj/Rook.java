package ChessPrj;

public class Rook extends ChessPiece {

    public Rook(Player player) {
        super(player);
    }

    public String type() {
        return "Rook";
    }

    // determines if the move is valid for a rook piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        boolean superValid = super.isValidMove(move, board);
        boolean valid = false;
        if((move.toColumn == move.fromColumn) ||
                (move.toRow == move.fromRow)) {
            valid = true;
        }
        // More code is needed
        return valid&&superValid;

    }

}
