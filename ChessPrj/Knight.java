package ChessPrj;

public class Knight extends ChessPiece {

    public Knight(Player player) {
        super(player);
    }

    public String type() {
        return "Knight";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board){
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

        return superValidMove && validMove;
    }
}