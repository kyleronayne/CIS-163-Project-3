package ChessPrj;

public class Rook extends ChessPiece {

    private boolean rookMoved;

    public Rook(Player player) {
        super(player);
        rookMoved = false;
    }

    public String type() {
        String type = "Rook";
        return type;
    }

    // determines if the move is valid for a rook piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        boolean superValid = super.isValidMove(move, board);
        boolean valid = false;
        if(move.toColumn == move.fromColumn && move.fromRow != move.toRow){
            rookMoved = true;
            valid = true;
            if (move.toRow < move.fromRow) {
                for (int i = 1; i < Math.abs(move.fromRow - move.toRow); i++) {
                    if (!(board[move.fromRow - i][move.fromColumn] == null)) {
                        rookMoved = false;
                        return false;
                    }
                }
            }
            if (move.toRow > move.fromRow) {
                for (int i = 1; i < Math.abs(move.fromRow - move.toRow); i++) {
                    if (!(board[move.fromRow + i][move.fromColumn] == null)) {
                        rookMoved = false;
                        return false;
                    }
                }
            }
        }
        else if(move.toRow == move.fromRow && move.toColumn != move.fromColumn){
            rookMoved = true;
            valid = true;
            if (move.toColumn < move.fromColumn) {
                for (int i = 1; i < Math.abs(move.fromColumn - move.toColumn); i++) {
                    if (!(board[move.fromRow][move.fromColumn - 1] == null))
                        rookMoved = false;
                        return false;
                }
            }
            if (move.toColumn > move.fromColumn) {
                for (int i = 1; i < Math.abs(move.fromColumn - move.toColumn); i++) {
                    if (!(board[move.fromRow][move.fromColumn + 1] == null))
                        rookMoved = false;
                        return false;
                }
            }
        }
        // More code is needed
        return valid&&superValid;

    }

    public boolean isRookMoved() {
        return rookMoved;
    }

}
