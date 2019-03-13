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
        if(move.toColumn == move.fromColumn && move.fromRow != move.toRow){
            valid = true;
            if (move.toRow < move.fromRow) {
                for (int i = 1; i < Math.abs(move.fromRow - move.toRow); i++) {
                    if (!(board[move.fromRow - i][move.fromColumn] == null)) {
                        return false;
                    }
                }
            }
            if (move.toRow > move.fromRow) {
                for (int i = 1; i < Math.abs(move.fromRow - move.toRow); i++) {
                    if (!(board[move.fromRow + i][move.fromColumn] == null)) {
                        return false;
                    }
                }
            }
        }
        else if(move.toRow == move.fromRow && move.toColumn != move.fromColumn){
            valid = true;
            if (move.toColumn < move.fromColumn) {
                for (int i = 1; i < Math.abs(move.fromColumn - move.toColumn); i++) {
                    if (!(board[move.fromRow][move.fromColumn - 1] == null))
                        return false;
                }
            }
            if (move.toColumn > move.fromColumn) {
                for (int i = 1; i < Math.abs(move.fromColumn - move.toColumn); i++) {
                    if (!(board[move.fromRow][move.fromColumn + 1] == null))
                        return false;
                }
            }
        }
        // More code is needed
        return valid&&superValid;

    }

}
